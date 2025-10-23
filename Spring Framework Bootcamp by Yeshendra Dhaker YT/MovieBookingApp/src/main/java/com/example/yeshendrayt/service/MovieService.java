package com.example.yeshendrayt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.yeshendrayt.dto.MovieDTO;
import com.example.yeshendrayt.entity.Movie;
import com.example.yeshendrayt.repository.MovieRepo;

@Service
public class MovieService {

	@Autowired
	private MovieRepo movieRepo;

	public Movie addMovie(MovieDTO movieDTO) {

		Movie movie = new Movie();
		movie.setName(movieDTO.getName());
		movie.setDescription(movieDTO.getDescription());
		movie.setGenre(movieDTO.getGenre());
		movie.setReleaseDate(movieDTO.getReleaseDate());
		movie.setDuration(movieDTO.getDuration());
		movie.setLanguage(movieDTO.getLanguage());

		return movieRepo.save(movie);
	}

	public List<Movie> getAllMovies() {

		return movieRepo.findAll();
	}

	public List<Movie> getMoviesByGenre(String genre) {
		Optional<List<Movie>> listOfMovieBox = movieRepo.findByGenre(genre);
		if (listOfMovieBox.isPresent()) {
			return listOfMovieBox.get();
		} else {
			throw new RuntimeException("No movies found for genre" + genre);
		}
	}

	public List<Movie> getMoviesByLanguage(String language) {
		Optional<List<Movie>> listOfMovieBox = movieRepo.findByLanguage(language);
		if (listOfMovieBox.isPresent()) {
			return listOfMovieBox.get();
		} else {
			throw new RuntimeException("No movies found for language" + language);
		}
	}

	public Movie getMovieByTitle(String title) {
		Optional<Movie> movieBox = movieRepo.findByName(title);
		if (movieBox.isPresent()) {
			return movieBox.get();
		} else {
			throw new RuntimeException("No movies found for title" + title);
		}
	}

	public Movie updateMovie(Long id, MovieDTO movieDTO) {
		Movie movie = movieRepo.findById(id).orElseThrow(() -> new RuntimeException("No movie found for the id " + id));
		
		movie.setName(movieDTO.getName());
		movie.setDescription(movieDTO.getDescription());
		movie.setGenre(movieDTO.getGenre());
		movie.setReleaseDate(movieDTO.getReleaseDate());
		movie.setDuration(movieDTO.getDuration());
		movie.setLanguage(movieDTO.getLanguage());
		
		return movieRepo.save(movie);
	}
	
	public void deleteMovie(Long id) {
		movieRepo.deleteById(id);
	}

}
