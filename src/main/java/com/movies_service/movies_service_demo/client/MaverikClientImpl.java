package com.movies_service.movies_service_demo.client;

import com.movies_service.movies_service_demo.model.Movie;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MaverikClientImpl implements MaverikClient {

    private final RestTemplateBuilder restTemplateBuilder;

    private String GET_MOVIE_BY_TITLE_PATH = "/movie/api/movie/title/";
    private String GET_MOVIE_BY_IMDB_ID_PATH = "/movie/api/movie/";


    @Override
    public List<Movie> getMoviesByTitle(String title) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String uri = UriComponentsBuilder.fromPath(GET_MOVIE_BY_TITLE_PATH + title)
                .queryParam("source", "web")
                .buildAndExpand(title)
                .encode()
                .toUriString();
        return Optional.ofNullable(restTemplate.getForEntity(uri, List.class).getBody()).orElseThrow();
    }

    @Override
    public Movie getMoviesByImdbId(String imdbId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String uri = UriComponentsBuilder.fromPath(GET_MOVIE_BY_IMDB_ID_PATH + imdbId)
                .queryParam("source", "web")
                .buildAndExpand(imdbId)
                .encode()
                .toUriString();
        return Optional.ofNullable(restTemplate.getForEntity(uri, Movie.class).getBody()).orElseThrow();
    }
}