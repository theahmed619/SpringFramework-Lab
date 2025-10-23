package com.example.yeshendrayt.entity;

public class Flight {

	private String flightNo;
	private String source;
	private String destination;
	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Flight(String flightNo, String source, String destination) {
		super();
		this.flightNo = flightNo;
		this.source = source;
		this.destination = destination;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
}
