package com.example.yeshendrayt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.yeshendrayt.entity.DateAndTime;
import com.example.yeshendrayt.repository.DateAndTimeRepo;

@Service
public class DateAndTimeService {

	@Autowired
	private DateAndTimeRepo dateAndTimeRepo;
	
	public ResponseEntity<?> saveDate(DateAndTime dateAndTime){
		dateAndTimeRepo.save(dateAndTime);
		
		return ResponseEntity.ok().build();
		
	}
}
