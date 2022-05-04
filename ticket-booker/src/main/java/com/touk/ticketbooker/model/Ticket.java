package com.touk.ticketbooker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.*;

@Entity(name = "Ticket")
@Table(name = "ticket")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    @Column(
            name = "id",
            updatable = false
    )
    private Long Id;

    @Column(
            name = "type",
            nullable = false
    )
    private TicketType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "client_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "client_ticket_fk"
            )
    )
    @JsonBackReference
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "screening_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "screening_ticket_fk"
            )
    )
    @JsonBackReference
    private Screening screening;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "seat_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "seat_ticket_fk"
            )
    )
    @JsonManagedReference
    private Seat seat;

    public Ticket(TicketType type) {
        this.type = type;
    }

    public Ticket(TicketType type, Client client, Screening screening, Seat seat) {
        this.type = type;
        this.client = client;
        this.screening = screening;
        this.seat = seat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Id.equals(ticket.Id)
                && type == ticket.type
                && Objects.equals(client, ticket.client)
                && Objects.equals(screening, ticket.screening)
                && Objects.equals(seat, ticket.seat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, type, client, screening, seat);
    }
}
