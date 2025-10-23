package com.example.yeshendrayt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.yeshendrayt.entity.Theater;

@Repository
public interface TheaterRepo extends JpaRepository<Theater, Long>{
	
	Optional<List<Theater>> findByTheatreLocation(String location);

}
