package com.example.yeshendrayt.database;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.yeshendrayt.entity.Flight;

@Component
public class FlightListImpl implements FlightList{
	List<Flight> listOfFlight=new ArrayList<>();
	
	public FlightListImpl() {
		listOfFlight.add(new Flight("FLOO1","LA","NYC"));
		listOfFlight.add(new Flight("FLOO2","LA","NYC"));
		listOfFlight.add(new Flight("FLOO3","DUBAI","DELHI"));
		listOfFlight.add(new Flight("FLOO4","LA","BOMBAY"));
		listOfFlight.add(new Flight("FLOO5","NYC","BOMBAY"));
		listOfFlight.add(new Flight("FLOO6","NYC","NZ"));
		listOfFlight.add(new Flight("FLOO7","LONDON","MOSCOW"));
		listOfFlight.add(new Flight("FLOO8","KOLKATA","GERMANY"));
		listOfFlight.add(new Flight("FLOO9","TEXAS","CHINA"));
	}

	@Override
	public List<Flight> getAllFlights() {
		return this.listOfFlight;
	}

}
