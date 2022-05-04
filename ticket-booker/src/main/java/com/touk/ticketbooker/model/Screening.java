package com.touk.ticketbooker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Screening")
@Table(name = "screening")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Screening {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "time",
            nullable = false
    )
    private ZonedDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "movie_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "movie_screening_fk"
            )
    )
    @JsonBackReference
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "room_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "room_screening_fk"
            )
    )
    @JsonBackReference
    private Room room;

    @OneToMany(
            mappedBy = "screening",
            orphanRemoval = true,
            cascade = { PERSIST, REMOVE },
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private Set<Ticket> tickets = new HashSet<>();

    public Screening(ZonedDateTime time, Movie movie, Room room) {
        this.time = time;
        this.movie = movie;
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Screening screening = (Screening) o;
        return id.equals(screening.id)
                && Objects.equals(time, screening.time)
                && Objects.equals(movie, screening.movie)
                && Objects.equals(room, screening.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, movie, room);
    }
}
