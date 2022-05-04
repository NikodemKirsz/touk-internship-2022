package com.touk.ticketbooker.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "movie")
@Table(name = "movie")
@Getter
@Setter
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    @Column(
            name = "id",
            updatable = false
    )
    private Long Id;

    @Column(
            name = "title",
            nullable = false
    )
    private String title;

    @Column(
            name = "duration",
            nullable = false
    )
    private int duration;


    @OneToMany(
            mappedBy = "movie",
            orphanRemoval = true,
            cascade = { PERSIST, REMOVE },
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private Set<Screening> screenings = new HashSet<>();

    public Movie(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Id.equals(movie.Id)
                && Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, title);
    }
}
