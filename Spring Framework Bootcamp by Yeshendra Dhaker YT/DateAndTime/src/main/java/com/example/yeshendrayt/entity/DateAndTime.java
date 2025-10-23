package com.example.yeshendrayt.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dateandtime")
public class DateAndTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate localDate;

	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime localTime;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime localDateTime;

	// @JsonProperty("OffsetDateTime") // Maps JSON's "OffsetDateTime" to this field
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private OffsetDateTime offsetDateTime;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX'['VV']'")
	private ZonedDateTime zonedDateTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date legacyDate;

}
