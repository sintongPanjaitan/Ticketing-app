package com.test.ticketing.schema;

import com.test.ticketing.model.UserTicket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBooking {
    private Long event_id;
    private Long user_id;
    private String ticket_code;

    public ResponseBooking(UserTicket userTicket) {
        this.event_id = userTicket.getEvents().getId();
        this.user_id = userTicket.getUsers().getId();
        this.ticket_code = userTicket.getTicket_code();
    }
}
