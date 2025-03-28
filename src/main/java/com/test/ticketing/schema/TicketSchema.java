package com.test.ticketing.schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.ticketing.model.Tickets;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
public class TicketSchema {

    private Long event_id;

    private Integer maximum_tickets;

    private Long price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start_at;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end_at;

    public TicketSchema() {
    }

    public TicketSchema(Tickets tickets) {
        this.event_id = tickets.getEvents().getId();
        this.maximum_tickets = tickets.getMaximum_tickets();
        this.price = tickets.getPrice();
        this.start_at = tickets.getStart_at();
        this.end_at = tickets.getEnd_at();
    }
}
