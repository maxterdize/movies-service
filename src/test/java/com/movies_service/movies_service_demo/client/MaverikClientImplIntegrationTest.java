package com.movies_service.movies_service_demo.client;

import com.google.gson.Gson;
import com.movies_service.movies_service_demo.model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


@SpringBootTest
public class MaverikClientImplIntegrationTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private String GET_MOVIE_BY_TITLE_PATH = "/movie/api/movie/title/";
    private String GET_MOVIE_BY_IMDB_ID_PATH = "/movie/api/movie/";


    private MaverikClientImpl maverikClientImpl;
    private MockRestServiceServer server;

    private String MOVIE_TITLE = "The Godfather";
    private String MOVIE_IMDBID = "tt0068646";

    private final Gson gson = new Gson();

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @BeforeEach
    public void setUp() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        maverikClientImpl = new MaverikClientImpl(restTemplateBuilder);
        server = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testGetMovieByTitle() {
        Movie mockMovie = Movie.builder()
                .imdbID("tt0068646")
                .title("The Godfather")
                .year("1972")
                .build();

        List<Movie> mockMovies = new ArrayList<>();
        mockMovies.add(mockMovie);

        String uri = UriComponentsBuilder.fromPath(GET_MOVIE_BY_TITLE_PATH + MOVIE_TITLE)
                .queryParam("source", "web")
                .buildAndExpand(MOVIE_TITLE)
                .encode()
                .toUriString();

        server.expect(requestTo(uri))
                .andRespond(withSuccess(gson.toJson(mockMovies), MediaType.APPLICATION_JSON));

        List<Movie> returnedMovies = maverikClientImpl.getMoviesByTitle(MOVIE_TITLE);

        logger.info(returnedMovies.toString());

        assertEquals(mockMovie.getImdbID(), returnedMovies.get(0).getImdbID());
        assertEquals(mockMovie.getTitle(), returnedMovies.get(0).getTitle());
    }

}
