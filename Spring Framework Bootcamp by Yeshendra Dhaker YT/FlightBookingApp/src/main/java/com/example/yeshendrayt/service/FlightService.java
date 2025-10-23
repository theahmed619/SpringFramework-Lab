package com.example.yeshendrayt.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yeshendrayt.entity.Flight;
import com.example.yeshendrayt.repository.FlightRepo;

@Service
public class FlightService {
	
	@Autowired
	FlightRepo flightRepo;
	
	public List<Flight> getAllFlightsFromSourceToDestination(String source, String destination){
		
	return	flightRepo.getAllList().stream().filter(item->item.getSource().equalsIgnoreCase(source) && 
				item.getDestination().equalsIgnoreCase(destination)).collect(Collectors.toList());
		
	}

}
