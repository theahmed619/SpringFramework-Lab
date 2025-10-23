package com.example.yeshendrayt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yeshendrayt.dto.ShowDTO;
import com.example.yeshendrayt.entity.Booking;
import com.example.yeshendrayt.entity.Movie;
import com.example.yeshendrayt.entity.Show;
import com.example.yeshendrayt.entity.Theater;
import com.example.yeshendrayt.repository.MovieRepo;
import com.example.yeshendrayt.repository.ShowRepo;
import com.example.yeshendrayt.repository.TheaterRepo;

@Service
public class ShowService {

	@Autowired
	private ShowRepo showRepo;

	@Autowired
	private MovieRepo movieRepo;

	@Autowired
	private TheaterRepo theaterRepo;

	public Show createShow(ShowDTO showDTO) {

		Movie movie = movieRepo.findById(showDTO.getMovieId())
				.orElseThrow(() -> new RuntimeException("No Movie Found for id " + showDTO.getMovieId()));

		Theater theater = theaterRepo.findById(showDTO.getTheaterId())
				.orElseThrow(() -> new RuntimeException("No Theater Found for id " + showDTO.getTheaterId()));

		Show show = new Show();
		show.setShowTime(showDTO.getShowTime());
		show.setPrice(showDTO.getPrice());
		show.setMovie(movie);
		show.setTheater(theater);

		return showRepo.save(show);

	}

	public List<Show> getAllShows() {

		return showRepo.findAll();
	}

	public List<Show> getShowsByMovie(Long movieid) {
		Optional<List<Show>> showListBox = showRepo.findByMovieId(movieid);
		if (showListBox.isPresent()) {
			return showListBox.get();
		} else
			throw new RuntimeException("No show available for the movie");
	}

	public List<Show> getShowsByTheater(Long theaterid) {
		Optional<List<Show>> showListBox = showRepo.findByTheaterId(theaterid);
		if (showListBox.isPresent()) {
			return showListBox.get();
		} else
			throw new RuntimeException("No show available for the theater");
	}

	public Show updateShow(Long id, ShowDTO showDTO) {
		Show show = showRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("No show available  for id " + id));
				
				Movie movie = movieRepo.findById(showDTO.getMovieId())
						.orElseThrow(() -> new RuntimeException("No Movie Found for id " + showDTO.getMovieId()));

				Theater theater = theaterRepo.findById(showDTO.getTheaterId())
						.orElseThrow(() -> new RuntimeException("No Theater Found for id " + showDTO.getTheaterId()));

			
				show.setShowTime(showDTO.getShowTime());
				show.setPrice(showDTO.getPrice());
				show.setMovie(movie);
				show.setTheater(theater);

				return showRepo.save(show);
	}

	public void deleteShow(Long id) {
		
		if(showRepo.existsById(id)) {
			throw new RuntimeException("No show available for the id"+id);
		}
		
		List<Booking> bookings=showRepo.findById(id).get().getBookings();
		if(!bookings.isEmpty()) {
			throw new RuntimeException("Cant delete show with existing booking");
		}
		showRepo.deleteById(id);
	}
}
