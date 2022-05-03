package com.touk.ticketbooker.model.dto;

import com.touk.ticketbooker.Multiplex;
import com.touk.ticketbooker.model.Movie;
import com.touk.ticketbooker.model.Screening;
import com.touk.ticketbooker.model.Ticket;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

import static com.touk.ticketbooker.Multiplex.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public final class ReceiptDto {
    private final String movieTitle;
    private final ZonedDateTime startTime;
    private final ZonedDateTime expirationTime;
    private final double total;
    private final Set<TicketDto> tickets;

    public ReceiptDto(Screening screening, Set<TicketDto> tickets) {
        ZonedDateTime startTime = screening.getTime();
        this.movieTitle = screening.getMovie().getTitle();
        this.startTime = startTime;
        this.expirationTime = startTime.plusMinutes(TICKET_EXPIRATION_TIME);
        double sum = 0;
        for(var ticket : tickets) {
           sum += ticket.ticketType().getPrice();
        }
        this.total = sum;
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ReceiptDto) obj;
        return Objects.equals(this.movieTitle, that.movieTitle) &&
                Objects.equals(this.startTime, that.startTime) &&
                Objects.equals(this.expirationTime, that.expirationTime) &&
                Double.doubleToLongBits(this.total) == Double.doubleToLongBits(that.total) &&
                Objects.equals(this.tickets, that.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieTitle, startTime, expirationTime, total, tickets);
    }

    @Override
    public String toString() {
        return "ReceiptDto[" +
                "movieTitle=" + movieTitle + ", " +
                "startTime=" + startTime + ", " +
                "expirationTime=" + expirationTime + ", " +
                "total=" + total + ", " +
                "tickets=" + tickets + ']';
    }

}
