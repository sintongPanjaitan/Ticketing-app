package com.test.ticketing.service;

import com.test.ticketing.model.Events;
import com.test.ticketing.model.UserTicket;
import com.test.ticketing.model.Users;
import com.test.ticketing.schema.BookingSchema;
import com.test.ticketing.schema.ResponseBooking;

import javax.swing.plaf.ListUI;
import java.util.List;

public interface UserTicketService {
    List<UserTicket> findAll();
    List<ResponseBooking> generateTicket(BookingSchema BookingSchema);
    List<UserTicket> findAllByUsers(Users users);
    List<UserTicket> findAllByEvents(Events events);
}
