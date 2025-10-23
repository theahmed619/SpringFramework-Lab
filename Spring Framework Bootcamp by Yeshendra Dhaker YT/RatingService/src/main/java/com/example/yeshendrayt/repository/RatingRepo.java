package com.example.yeshendrayt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.yeshendrayt.entity.Rating;

@Repository
public interface RatingRepo extends JpaRepository<Rating, Long>{
	
	public Rating findByHotelId(Long hotelid);

}
