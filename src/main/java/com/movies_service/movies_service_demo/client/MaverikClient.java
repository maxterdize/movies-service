package com.movies_service.movies_service_demo.client;

import com.movies_service.movies_service_demo.model.Movie;

import java.util.List;

public interface MaverikClient {

    List<Movie> getMoviesByTitle(String title);
    Movie getMoviesByImdbId(String imdbId);
}
