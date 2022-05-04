package com.touk.ticketbooker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Seat")
@Table(name = "seat")
@Getter
@Setter
@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "row",
            nullable = false
    )
    private int row;

    @Column(
            name = "number",
            nullable = false
    )
    private int number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "room_id",
            foreignKey = @ForeignKey(
                    name = "room_seat_fk"
            )
    )
    @JsonBackReference
    private Room room;

    @OneToMany(
            mappedBy = "seat",
            orphanRemoval = true,
            cascade = { PERSIST, REMOVE },
            fetch = FetchType.LAZY
    )
    @JsonBackReference
    private Set<Ticket> tickets = new HashSet<>();

    public Seat(int row, int number) {
        this.row = row;
        this.number = number;
    }

    public Seat(Long id, int row, int number) {
        this.id = id;
        this.row = row;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return id.equals(seat.id)
                && number == seat.number
                && row == seat.row
                && Objects.equals(room, seat.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, row, number, room);
    }
}
