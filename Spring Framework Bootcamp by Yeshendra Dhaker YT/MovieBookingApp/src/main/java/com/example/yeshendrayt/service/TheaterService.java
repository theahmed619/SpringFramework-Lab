package com.example.yeshendrayt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yeshendrayt.dto.TheaterDTO;
import com.example.yeshendrayt.entity.Theater;
import com.example.yeshendrayt.repository.TheaterRepo;

@Service
public class TheaterService {

	@Autowired
	private TheaterRepo theaterRepo;

	public Theater addTheater(TheaterDTO theaterDTO) {
		Theater theater = new Theater();
		theater.setTheatreName(theaterDTO.getTheatreName());
		theater.setTheatreCapacity(theaterDTO.getTheatreCapacity());
		theater.setTheatreLocation(theaterDTO.getTheatreLocation());
		theater.setTheatreScreenType(theaterDTO.getTheatreScreenType());

		return theaterRepo.save(theater);
	}

	public List<Theater> getTheaterByLocation(String location) {
		Optional<List<Theater>> listOfTheaterBox = theaterRepo.findByTheatreLocation(location);

		if (listOfTheaterBox.isPresent()) {
			return listOfTheaterBox.get();
		} else
			throw new RuntimeException("no theater found for the location" + location);
	}

	public Theater updateTheater(Long id, TheaterDTO theaterDTO) {
		Theater theater = theaterRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("No theater found for id" + id));
		
		theater.setTheatreName(theaterDTO.getTheatreName());
		theater.setTheatreCapacity(theaterDTO.getTheatreCapacity());
		theater.setTheatreLocation(theaterDTO.getTheatreLocation());
		theater.setTheatreScreenType(theaterDTO.getTheatreScreenType());
		
		return theaterRepo.save(theater);
		
	}
	
	public void deleteTheater(Long id) {
		theaterRepo.deleteById(id);
	}
}
