package com.touk.ticketbooker.repository;

import com.touk.ticketbooker.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
