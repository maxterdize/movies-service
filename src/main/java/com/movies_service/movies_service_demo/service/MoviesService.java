package com.movies_service.movies_service_demo.service;

import com.movies_service.movies_service_demo.client.MaverikClient;
import com.movies_service.movies_service_demo.exceptions.MovieNotFoundException;
import com.movies_service.movies_service_demo.model.Movie;
import com.movies_service.movies_service_demo.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MoviesService {

    private final MaverikClient maverikClient;
    private final MovieRepository movieRepository;


    @Transactional
    public List<Movie> getMoviesByTitle(String title) {
        List<Movie> movies = maverikClient.getMoviesByTitle(title);
        if (movies == null) {
            throw new MovieNotFoundException("No movies found for title: " + title);
        }
        try {
            movieRepository.saveAll(movies);
            movieRepository.flush();
        } catch (Exception e) {
            throw new RuntimeException("Error saving movies with title: " + title, e);
        }
        return movies;
    }

}
