package com.touk.ticketbooker.repository;

import com.touk.ticketbooker.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {}
