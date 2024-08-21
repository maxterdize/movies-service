package com.movies_service.movies_service_demo.controller;

import com.movies_service.movies_service_demo.model.Movie;
import com.movies_service.movies_service_demo.service.MoviesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController("/codechallenge")
public class CodeChallengeController {

    private final MoviesService moviesService;

    @PostMapping(path = "/movies", consumes = "application/json")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        moviesService.saveMovie(movie);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @PostMapping(path = "/movies/all", consumes = "application/json")
    public ResponseEntity<List<Movie>> saveMovies(@RequestBody List<Movie> movies) {
        moviesService.saveMovies(movies);
        return new ResponseEntity<>(movies, HttpStatus.CREATED);
    }

    @GetMapping(path = "/movies/title/{title}", produces = "application/json")
    public ResponseEntity<List<Movie>> getMoviesByTitle(@PathVariable String title) {
        return new ResponseEntity<>(moviesService.getMoviesByTitle(title), HttpStatus.OK);
    }

    @GetMapping(path = "/movies/imdbId/{imdbId}", produces = "application/json")
    public ResponseEntity<Movie> getMovieByImdbId(@PathVariable String imdbId) {
        return new ResponseEntity<>(moviesService.getMovieByIbmdId(imdbId), HttpStatus.OK);
    }
}
