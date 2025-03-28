package com.test.ticketing.schema;

import com.test.ticketing.model.UserTicket;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ResponseBookingEvent {
    private String user_name;
    private Long event_id;
    private String ticket_code;
    private LocalDate event_date;

    public ResponseBookingEvent(UserTicket userTicket) {
        this.user_name = userTicket.getUsers().getName();
        this.event_id = userTicket.getEvents().getId();
        this.ticket_code = userTicket.getTicket_code();
        this.event_date = userTicket.getEvents().getEvent_date();
    }
}
