package com.example.yeshendrayt.entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class SimpleDateTime {

	public static void main(String[] args) {

		// Input Format : "yyyy-mm-dd"
//		LocalDate today=LocalDate.now();
//		System.out.println(today);
//		
//		
//		LocalDate specific =LocalDate.of(2025, 12, 10);
//		System.out.println(specific);
//		
//		LocalDate futureDate=today.plusYears(30);
//		System.out.println(futureDate);
//		
//		LocalDate pastDate=today.minusYears(30);
//		System.out.println(pastDate);
//		
//		boolean isAfter =today.isAfter(pastDate);
//		System.out.println(isAfter);

		// input format : HH:mm:ss
//		LocalTime localTime=LocalTime.now();
//		System.out.println(localTime);
//		
//		LocalTime specific =LocalTime.of(13,12,54);
//		System.out.println(specific);

		// Input Format localDateTime : "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
//		LocalDateTime localDateTime=LocalDateTime.now();
//		System.out.println(localDateTime);

		// Input Format offsetDateTime: "yyyy-MM-dd'T'HH:mm:ss.NNNNNN+05:30"
		// OffsetDateTime offsetDateTime=OffsetDateTime.now();
		// System.out.println(offsetDateTime);

		// Input Format zonedDateTime: "yyyy-MM-dd'T'HH:mm:ss.SSS+05:30[Asia/culcutta]"
//		ZonedDateTime zonedDateTime=ZonedDateTime.now();
//		System.out.println(zonedDateTime);
//		
//		ZoneId zoneId=ZoneId.of("America/New_York");
//		System.out.println(zoneId);
//		
//		OffsetDateTime offsetDateTime=OffsetDateTime.now(zoneId);
//		System.out.println(offsetDateTime);

		// Input Format Date: "yyyy-MM-dd" OR "yyyy-MM-ddTHH:mm:ss"

		Date date = new Date();
		System.out.println(date);

		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));

	}
}
