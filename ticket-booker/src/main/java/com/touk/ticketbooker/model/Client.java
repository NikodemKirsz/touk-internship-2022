package com.touk.ticketbooker.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.GenerationType.*;

@Entity(name = "Client")
@Table(name = "client")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "surname",
            nullable = false
    )
    private String surname;

    @OneToMany(
            mappedBy = "client",
            orphanRemoval = true,
            cascade = { PERSIST, REMOVE },
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private Set<Ticket> tickets = new HashSet<>();

    public Client(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id.equals(client.id)
                && Objects.equals(name, client.name)
                && Objects.equals(surname, client.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }
}
