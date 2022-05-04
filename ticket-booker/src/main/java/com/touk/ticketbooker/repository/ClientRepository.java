package com.touk.ticketbooker.repository;

import com.touk.ticketbooker.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.name = ?1 and c.surname = ?2")
    Optional<Client> findByNameAndSurname(String name, String surname);
}
