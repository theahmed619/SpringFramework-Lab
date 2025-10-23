package com.example.yeshendrayt.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.yeshendrayt.database.FlightList;
import com.example.yeshendrayt.entity.Flight;

@Component
public class FlightRepo {
	
	@Autowired
	FlightList flightList;
	
	public List<Flight> getAllList(){
		return flightList.getAllFlights();
	}
	
}
