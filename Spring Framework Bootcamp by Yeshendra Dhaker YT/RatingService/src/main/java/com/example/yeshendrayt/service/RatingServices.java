package com.example.yeshendrayt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.yeshendrayt.entity.Rating;
import com.example.yeshendrayt.repository.RatingRepo;

@Service
public class RatingServices {
	
	@Autowired
	RatingRepo ratingRepo;
	
	public Rating addRating(Rating rating){
		
		return ratingRepo.save(rating);
	}

	public Rating getRatingByHotelId(Long hotelid) {
		
		return ratingRepo.findByHotelId(hotelid);
	}
}
