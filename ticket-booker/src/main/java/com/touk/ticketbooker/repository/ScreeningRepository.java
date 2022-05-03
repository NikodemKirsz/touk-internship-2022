package com.touk.ticketbooker.repository;

import com.touk.ticketbooker.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {
}
