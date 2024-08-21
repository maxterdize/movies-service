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

    @GetMapping("/movies/title/{title}")
    public ResponseEntity<List<Movie>> getMoviesByTitle(@PathVariable String title) {
        return new ResponseEntity<>(moviesService.getMoviesByTitle(title), HttpStatus.OK);
    }
}
