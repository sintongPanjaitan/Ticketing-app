package com.test.ticketing.controller;

import com.test.ticketing.model.Events;
import com.test.ticketing.model.Tickets;
import com.test.ticketing.model.UserTicket;
import com.test.ticketing.model.Users;
import com.test.ticketing.schema.*;
import com.test.ticketing.service.EventService;
import com.test.ticketing.service.TicketService;
import com.test.ticketing.service.UserService;
import com.test.ticketing.service.UserTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserTicketController {

    private static final Logger logger = LoggerFactory.getLogger(UserTicketController.class);

    private final UserTicketService userTicketService;

    private final UserService userService;

    private final EventService eventService;

    private final TicketService ticketService;

    @Autowired
    public UserTicketController(UserTicketService userTicketService, UserService userService, EventService eventService, TicketService ticketService) {
        this.userTicketService = userTicketService;
        this.userService = userService;
        this.eventService = eventService;
        this.ticketService = ticketService;
    }

    @PostMapping("/user_ticket/booking")
    public ResponseEntity<List<ResponseBooking>> bookingTicket(@RequestBody BookingSchema bookingSchema) {
        String errorMessage = null ;
        try {
            if (bookingSchema.getQuantity()< 0 || bookingSchema.getQuantity() > 5) {
                errorMessage = "Quantity should be between 0 and 5";
                throw new CustomException(errorMessage, HttpStatus.BAD_REQUEST);
            }
            logger.info("Booking ticket", bookingSchema.getUser_id());
            System.out.println(bookingSchema.getUser_id());
            Optional<Users> users = userService.findById(bookingSchema.getUser_id());

            if (users.isEmpty()) {
                errorMessage = "User id not found";
                throw new CustomException(errorMessage, HttpStatus.BAD_REQUEST);
            }

            Optional<Tickets> tickets = ticketService.findById(bookingSchema.getTicket_id());
            if (tickets.isEmpty()) {
                errorMessage = "Ticket id not found";
                throw new CustomException(errorMessage, HttpStatus.BAD_REQUEST);
            }

            if (tickets.get().getMaximum_tickets() > bookingSchema.getQuantity() && tickets.get().getStart_at().isBefore(LocalDateTime.now()) && tickets.get().getEnd_at().isAfter(LocalDateTime.now())) {

            }else {
                errorMessage = "Ticket not available";
                throw new CustomException(errorMessage, HttpStatus.BAD_REQUEST);
            }

            List<ResponseBooking> responseBookings  =userTicketService.generateTicket(bookingSchema);
            System.out.println(responseBookings);
            return new ResponseEntity<>(responseBookings, HttpStatus.CREATED);
        } catch (Exception e) {
            if (errorMessage == null) {
                errorMessage = "Failed to book ticket";
            }
            logger.error(e.getMessage());
            throw new CustomException(errorMessage, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/user_ticket/user")
    public ResponseEntity<List<ResponseBookingUser>> getAllUsersBooking(@RequestParam(required = false) Long user_id) {
        String errorMessage = null ;
        try {
            List<UserTicket> usersTickets = new ArrayList<UserTicket>();
            List<ResponseBookingUser> responseBookingUsers = new ArrayList<>();
            Optional<Users> users = userService.findById(user_id);

            if (users.isEmpty()) {
                errorMessage = "User id not found";
                throw new CustomException(errorMessage, HttpStatus.BAD_REQUEST);
            }

            userTicketService.findAllByUsers(users.get()).forEach(usersTickets::add);

            for (UserTicket userTicket : usersTickets) {
                responseBookingUsers.add(new ResponseBookingUser(userTicket));
            }
            if (usersTickets.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(responseBookingUsers, HttpStatus.OK);
        }
        catch (Exception e) {
            if (errorMessage == null) {
                errorMessage = "Failed to get data";
            }
            logger.error(e.getMessage());
            throw new CustomException(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user_ticket/event")
    public ResponseEntity<List<ResponseBookingEvent>> getAllEventBooking(@RequestParam(required = false) Long event_id) {
        String errorMessage = null ;
        try {
            if (event_id == null || event_id < 0) {
                errorMessage = "Event id can not be null";
                throw new CustomException(errorMessage, HttpStatus.BAD_REQUEST);
            }
            List<UserTicket> usersTickets = new ArrayList<UserTicket>();
            List<ResponseBookingEvent> responseBookingEvents = new ArrayList<>();
            Optional<Events> events = eventService.findById(event_id);

            if (events.isEmpty()) {
                errorMessage = "Event id not found";
                throw new CustomException(errorMessage, HttpStatus.BAD_REQUEST);
            }

            userTicketService.findAllByEvents(events.get()).forEach(usersTickets::add);

            for (UserTicket userTicket : usersTickets) {
                responseBookingEvents.add(new ResponseBookingEvent(userTicket));
            }
            if (usersTickets.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(responseBookingEvents, HttpStatus.OK);
        }
        catch (Exception e) {
            if (errorMessage == null) {
                errorMessage = "Failed to get data";
            }
            logger.error(e.getMessage());
            throw new CustomException(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }
}
