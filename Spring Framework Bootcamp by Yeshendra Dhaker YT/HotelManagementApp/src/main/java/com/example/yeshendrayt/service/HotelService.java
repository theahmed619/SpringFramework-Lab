package com.example.yeshendrayt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.yeshendrayt.communicator.RestTemplateCommunicator;
import com.example.yeshendrayt.dto.HotelDTO;
import com.example.yeshendrayt.dto.UpdateHotelAddressDTO;
import com.example.yeshendrayt.entity.Hotel;
import com.example.yeshendrayt.repository.HotelRepo;

@Service
public class HotelService {

	@Autowired
	HotelRepo hotelRepo;
	
	@Autowired
	RestTemplateCommunicator restTemplateCommunicator;

	public ResponseEntity<Hotel> saveHotel(HotelDTO hotelDTO) {
		Hotel hotel = new Hotel();
		hotel.setName(hotelDTO.getName());
		hotel.setAddress(hotelDTO.getAddress());
		hotel.setCity(hotelDTO.getCity());
		hotel.setPostalCode(hotelDTO.getPostalCode());
		hotel.setRating(hotelDTO.getRating());
		hotel.setAvailable(hotelDTO.isAvailable());

		hotelRepo.save(hotel);
		return new ResponseEntity<>(hotel, HttpStatus.CREATED);
	}

	public List<Hotel> getAllHotels() {
		return hotelRepo.findAll();
	}

	public ResponseEntity<Hotel> getHotelById(Long id) {

		Optional<Hotel> hotelBox = hotelRepo.findById(id);
		Float hotelActualRating=restTemplateCommunicator.getActualHotelRating(id);
		Hotel hotel=hotelBox.get();
		hotel.setRating(hotelActualRating);
		if (hotelBox.isPresent()) {
			return new ResponseEntity<>(hotel, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	public ResponseEntity<Hotel> updateHotel(HotelDTO hotelDTO, Long id) {
		ResponseEntity<Hotel> response = getHotelById(id);
		if (response != null && response.getBody() !=null) {
			Hotel hotel=response.getBody();
			hotel.setName(hotelDTO.getName());
			hotel.setAddress(hotelDTO.getAddress());
			hotel.setCity(hotelDTO.getCity());
			hotel.setPostalCode(hotelDTO.getPostalCode());
			hotel.setRating(hotelDTO.getRating());
			hotel.setAvailable(hotelDTO.isAvailable());

		hotelRepo.save(hotel);
		return new ResponseEntity<>(hotel, HttpStatus.OK);
		}

		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	public void deleteHotel(Long id) {
		ResponseEntity<Hotel> hotel = (ResponseEntity<Hotel>) getHotelById(id);
		if (hotel != null) {
			hotelRepo.deleteById(id);
		}
	}

	public Hotel updateHotelAddress(UpdateHotelAddressDTO addressDTO, Long id) {
		ResponseEntity<Hotel> res = (ResponseEntity<Hotel>) getHotelById(id);
		if (res != null) {
			Hotel hotel=res.getBody();
			
			hotel.setAddress(addressDTO.getAddress());
			hotel.setCity(addressDTO.getCity());
			hotel.setPostalCode(addressDTO.getPostalCode());

			return hotelRepo.save(hotel);
		}
		
		else return null;
	}

}
