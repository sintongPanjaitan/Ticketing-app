package com.test.ticketing.schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.ticketing.model.Events;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.events.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class EventSchema {

    private String name;

    private String place;

    private Integer maximum_tickets;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate event_date;

    private String description;

    public EventSchema() {
    }

    public EventSchema(Events events) {
        this.name = events.getName();
        this.place = events.getPlace();
        this.maximum_tickets = events.getMaximum_tickets();
        this.event_date = events.getEvent_date();
        this.description = events.getDescription();
    }

}
