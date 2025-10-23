package com.example.yeshendrayt.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchduledTask {
	
	private int counter=0;
//	
//	@Scheduled(fixedRate = 2000) //every 2 seconds
//	private void nightlyCycle() {
//		
//		counter++;
//		System.out.println("Nightly Process Executed"+counter);
//	}
	
	
	@Scheduled(cron = "1/5 * * * * ?") 
	private void nightlyCycle() {
		
		counter++;
		System.out.println("Nightly Process Executed"+counter);
	}


}
