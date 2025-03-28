package com.test.ticketing.schema;

import com.test.ticketing.model.UserTicket;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ResponseBookingUser {
    private String event_name;
    private Long event_id;
    private String ticket_code;
    private LocalDate event_date;

    public ResponseBookingUser(UserTicket userTicket) {
        this.event_name = userTicket.getEvents().getName();
        this.event_id = userTicket.getEvents().getId();
        this.ticket_code = userTicket.getTicket_code();
        this.event_date = userTicket.getEvents().getEvent_date();
    }
}
