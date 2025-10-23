package com.example.yeshendrayt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.yeshendrayt.dto.HotelDTO;
import com.example.yeshendrayt.dto.UpdateHotelAddressDTO;
import com.example.yeshendrayt.entity.Hotel;
import com.example.yeshendrayt.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	HotelService hotelService;

	@PostMapping("/createhotel")
	public ResponseEntity<?> createHotel(@RequestBody HotelDTO hotelDTO) {
		return hotelService.saveHotel(hotelDTO);

	}

	@GetMapping("/getallhotels")
	public List<Hotel> getAllHotels() {

		return hotelService.getAllHotels();
	}

	@GetMapping("/gethotel/{id}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
		return hotelService.getHotelById(id);

	}

	@PutMapping("/updatehotel/{id}")
	public ResponseEntity<Hotel> updateHotel(@RequestBody HotelDTO hotelDTO, @PathVariable Long id) {
		return hotelService.updateHotel(hotelDTO, id);
	}

	@DeleteMapping("/deletehotel/{id}")
	public void deleteHotel(@PathVariable Long id) {
		hotelService.deleteHotel(id);
	}

	@PutMapping("/updateaddress/{id}")
	public Hotel updateHotelAddress(@RequestBody UpdateHotelAddressDTO addressDTO, @PathVariable Long id) {
		return hotelService.updateHotelAddress(addressDTO, id);
	}
}
