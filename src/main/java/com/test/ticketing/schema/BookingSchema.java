package com.test.ticketing.schema;


import com.test.ticketing.model.UserTicket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingSchema {
    private Long user_id;
    private Long ticket_id;
    private Integer quantity;

}
