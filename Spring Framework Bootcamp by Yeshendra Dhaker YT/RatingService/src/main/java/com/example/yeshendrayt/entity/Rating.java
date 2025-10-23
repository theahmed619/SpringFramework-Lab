package com.example.yeshendrayt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rating {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long hotelId;
	private Float hotelActualRating;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getHotelId() {
		return hotelId;
	}
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}
	public Float getHotelActualRating() {
		return hotelActualRating;
	}
	public void setHotelActualRating(Float hotelActualRating) {
		this.hotelActualRating = hotelActualRating;
	}
	
	

}
