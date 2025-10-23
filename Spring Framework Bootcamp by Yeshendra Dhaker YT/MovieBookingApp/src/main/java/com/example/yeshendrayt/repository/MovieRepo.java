package com.example.yeshendrayt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.yeshendrayt.entity.Movie;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {
	
	Optional<List<Movie>> findByGenre(String genre);
	
	Optional<List<Movie>> findByLanguage(String language);
	
	Optional<Movie> findByName(String title);

}
