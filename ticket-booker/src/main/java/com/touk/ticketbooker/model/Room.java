package com.touk.ticketbooker.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Room")
@Table(name = "room")
@Getter
@Setter
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @OneToMany(
            mappedBy = "room",
            orphanRemoval = true,
            cascade = { PERSIST, REMOVE },
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private Set<Screening> screenings = new HashSet<>();

    @OneToMany(
            mappedBy = "room",
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private Set<Seat> seats = new HashSet<>();

    public Room(Long id, Set<Seat> seats) {
        this.id = id;
        this.seats = seats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id.equals(room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
