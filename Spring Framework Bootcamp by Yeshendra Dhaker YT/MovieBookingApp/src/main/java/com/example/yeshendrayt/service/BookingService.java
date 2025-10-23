package com.example.yeshendrayt.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yeshendrayt.dto.BookingDTO;
import com.example.yeshendrayt.entity.Booking;
import com.example.yeshendrayt.entity.BookingStatus;
import com.example.yeshendrayt.entity.Show;
import com.example.yeshendrayt.entity.User;
import com.example.yeshendrayt.repository.BookingRepo;
import com.example.yeshendrayt.repository.ShowRepo;
import com.example.yeshendrayt.repository.UserRepo;

@Service
public class BookingService {

	@Autowired
	private BookingRepo bookingRepo;

	@Autowired
	private ShowRepo showRepo;
	
	@Autowired
	private UserRepo userRepo; 

	public Booking createBooking(BookingDTO bookingDTO) {

		Show show = showRepo.findById(bookingDTO.getShowId()).orElseThrow(() -> new RuntimeException("Show not found"));

		if (!isSeatsAvailable(show.getId(), bookingDTO.getNumberOfSeats())) {
			throw new RuntimeException("Not enough saet are avilable");
		}
		
		if(bookingDTO.getSeatNumbers().size() != bookingDTO.getNumberOfSeats()) {
			throw new RuntimeException("Seat Numbers and Number of Seats must be equal");

		}
		
		validateDuplicateSeats(show.getId(), bookingDTO.getSeatNumbers());
		
		User user=userRepo.findById(bookingDTO.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		Booking booking=new Booking();
		booking.setUser(user);
		booking.setShow(show);
		booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
		booking.setSeatNumbers(bookingDTO.getSeatNumbers());
		booking.setPrice(calculateTotalAmount(show.getPrice(), bookingDTO.getNumberOfSeats()));
		booking.setBookingTime(LocalDateTime.now());
		booking.setBookingStatus(BookingStatus.PENDING);
		
		return bookingRepo.save(booking);

	}
	
	public List<Booking> getUserBookings(Long userid){
		return bookingRepo.findByUserId(userid);
	}
	
	public List<Booking> getShowBookings(Long showid){
		return bookingRepo.findByShowId(showid);
	}
	
	public Booking  confirmBooking(Long bookingid) {
		Booking booking=bookingRepo.findById(bookingid)
				.orElseThrow(() -> new RuntimeException("Booking not found"));
		if(booking.getBookingStatus() != BookingStatus.PENDING) {
			throw new RuntimeException("Booking is not pending state");
		}
		
		//  Payment Process
		booking.setBookingStatus(BookingStatus.CONFIRMED);
		return bookingRepo.save(booking);
	}

	
	public Booking cancelBooking(Long bookingid) {
		Booking booking=bookingRepo.findById(bookingid)
				.orElseThrow(() -> new RuntimeException("Booking not found"));
		validateCancellation(booking);
		
		booking.setBookingStatus(BookingStatus.CANCELLED);
		return bookingRepo.save(booking);
		
	}
	
	public void validateCancellation(Booking  booking) {
		LocalDateTime showTime=booking.getShow().getShowTime();
		LocalDateTime deadLineTime=showTime.minusHours(2);
		
		if(LocalDateTime.now().isAfter(deadLineTime)) {
			throw new RuntimeException("Cannot Canvel the booking");
		}
		
		if(booking.getBookingStatus()== BookingStatus.CANCELLED) {
			throw new RuntimeException("Booking Already been cancelled");
		}
		
	}
	
	public boolean isSeatsAvailable(Long showid, Integer numberOfSeats) {

		Show show = showRepo.findById(showid).orElseThrow(() -> new RuntimeException("Show not found"));

		int bookedSeats=show.getBookings().stream()
							.filter(booking -> booking.getBookingStatus()!= BookingStatus.CANCELLED)
							.mapToInt(Booking::getNumberOfSeats)
							.sum();
		return (show.getTheater().getTheatreCapacity() - bookedSeats) >= numberOfSeats;
							
							
	}
	
	
	public void validateDuplicateSeats(Long showId, List<String> seatNumber) {
		Show show = showRepo.findById(showId).orElseThrow(() -> new RuntimeException("Show not found"));

		Set<String> occupiedSeats=show.getBookings().stream()
										.filter(b -> b.getBookingStatus()!=BookingStatus.CANCELLED)
										.flatMap(b -> b.getSeatNumbers().stream())
										.collect(Collectors.toSet());
		
		List<String> duplicateSeats=seatNumber.stream()
												.filter(occupiedSeats :: contains)
												.collect(Collectors.toList());
		
		if(!duplicateSeats.isEmpty()) {
			throw new RuntimeException("Seats are Already booked");
		}
		
		
		
		
	}
	
	
	public Double calculateTotalAmount(Double price, Integer numberOfSeats) {
		
		return price * numberOfSeats;
	}
	
	
	
	
	
	
	
	
	
	
	

}
