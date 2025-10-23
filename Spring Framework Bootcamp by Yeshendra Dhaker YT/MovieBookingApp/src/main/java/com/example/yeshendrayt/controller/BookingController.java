package com.example.yeshendrayt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.yeshendrayt.dto.BookingDTO;
import com.example.yeshendrayt.entity.Booking;
import com.example.yeshendrayt.entity.BookingStatus;
import com.example.yeshendrayt.service.BookingService;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/createbooking")
	public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO){
		
		return ResponseEntity.ok(bookingService.createBooking(bookingDTO));
	}
	
	@GetMapping("/getuserbookings/{id}")
	public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long id){
		return ResponseEntity.ok(bookingService.getUserBookings(id));
	}
	
	@GetMapping("/getshowbookings/{id}")
	public ResponseEntity<List<Booking>> getShowBookings(@PathVariable Long id){
		return ResponseEntity.ok(bookingService.getShowBookings(id));
	}
	
	@PutMapping("{id}/confirmbooking")
	public ResponseEntity<Booking> confirmBooking(@PathVariable Long id){
		return ResponseEntity.ok(bookingService.confirmBooking(id));
	}
	
	
	@PutMapping("{id}/cancelbooking")
	public ResponseEntity<Booking> cancelBooking(@PathVariable Long id){
		return ResponseEntity.ok(bookingService.cancelBooking(id));
	}
	
//
//	@GetMapping("/bookingstatus/{bookingStatus}")
//	public ResponseEntity<List<Booking>> getBookingsByStatus(@PathVariable BookingStatus  bookingStatus){
//		return ResponseEntity.ok(bookingService.getBookingsByStatus(bookingStatus));
//	}

}
