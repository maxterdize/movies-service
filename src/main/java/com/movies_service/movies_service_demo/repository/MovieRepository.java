package com.movies_service.movies_service_demo.repository;

import com.movies_service.movies_service_demo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
