package com.touk.ticketbooker.repository;

import com.touk.ticketbooker.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {}
