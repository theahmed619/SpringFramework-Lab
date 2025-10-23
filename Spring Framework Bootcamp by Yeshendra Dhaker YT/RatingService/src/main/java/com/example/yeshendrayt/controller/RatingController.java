package com.example.yeshendrayt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.yeshendrayt.entity.Rating;
import com.example.yeshendrayt.service.RatingServices;

@RestController
@RequestMapping("/rating")
public class RatingController {
	
	@Autowired
	RatingServices ratingServices;
	
	@PostMapping("/addrating")
	public ResponseEntity<Rating> addRating(@RequestBody Rating rating){
		Rating ratingNew=ratingServices.addRating(rating);
		return new ResponseEntity<>(ratingNew,HttpStatus.CREATED);
	}

	@GetMapping("/getratingbyhotelid/{hotelid}")
	public ResponseEntity<Float> getRatingByHotelId(@PathVariable Long hotelid){
		Rating rating=ratingServices.getRatingByHotelId(hotelid);
		return new ResponseEntity<>(rating.getHotelActualRating(), HttpStatus.OK);
	}
}
