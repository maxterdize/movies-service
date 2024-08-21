package com.movies_service.movies_service_demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "movie")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String imdbID;
    @Column
    private String title;
    @Column(name = "movie_year")
    private String year;
    @Column
    private String rated;
    @Column
    private String released;
    @Column
    private String runtime;
    @Column
    private String genre;
    @Column
    private String director;
    @Column
    private String actors;
    @Column
    private String plot;
    @Column
    private String language;
    @Column
    private String country;
    @Column
    private String poster;
    @Column
    private String imdbRating;
    @Column
    private String owner;
}
