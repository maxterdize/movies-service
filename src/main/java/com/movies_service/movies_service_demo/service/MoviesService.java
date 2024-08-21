package com.movies_service.movies_service_demo.service;

import com.movies_service.movies_service_demo.client.MaverikClient;
import com.movies_service.movies_service_demo.exceptions.MovieNotFoundException;
import com.movies_service.movies_service_demo.exceptions.RepositoryTransactionException;
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
            throw new RepositoryTransactionException("Error saving movies with title: " + title, e);
        }
        return movies;
    }

    @Transactional
    public void saveMovies(Set<Movie> movies) {
        try {
            movieRepository.saveAll(movies);
        } catch (Exception e) {
            throw new RepositoryTransactionException("Error saving list of movies", e);
        }
    }

    @Transactional
    public void saveMovie(Movie movie) {
        try {
            movieRepository.save(movie);
        } catch (Exception e) {
            throw new RepositoryTransactionException("Error saving movie with title: " + movie.getTitle(), e);
        }
    }

    public Movie getMovieByIbmdId(String imdbId) {
        Movie movie = movieRepository.findByImdbId(imdbId);
        if (movie == null) {
            throw new MovieNotFoundException("No movie found for imdbId: " + imdbId);
        }
        return movie;
    }

}
