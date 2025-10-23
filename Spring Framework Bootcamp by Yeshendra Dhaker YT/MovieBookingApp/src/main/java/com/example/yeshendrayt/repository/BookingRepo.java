package com.example.yeshendrayt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.yeshendrayt.entity.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long>{

	List<Booking> findByUserId(long userId);
	List<Booking> findByShowId(long showId);
}
