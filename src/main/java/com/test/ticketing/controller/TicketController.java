package com.test.ticketing.controller;

import com.test.ticketing.model.Events;
import com.test.ticketing.model.Tickets;
import com.test.ticketing.repository.EventRepository;
import com.test.ticketing.repository.TicketRepository;
import com.test.ticketing.schema.CustomException;
import com.test.ticketing.schema.TicketSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TicketController {

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    EventRepository eventRepository;

    @Autowired
    TicketRepository ticketRepository;

    @PostMapping("/ticket")
    public ResponseEntity<TicketSchema> addUser(@RequestBody TicketSchema ticketSchema) {
        String errorMessage = null ;
        try {
            Optional<Events> events = eventRepository.findById(ticketSchema.getEvent_id());
            if (!events.isPresent()) {
                errorMessage = "Event id not found";
                throw new CustomException(errorMessage, HttpStatus.BAD_REQUEST);
            }
            Tickets tickets = ticketRepository.save(new Tickets(
                    events.get(),
                    ticketSchema.getMaximum_tickets(),
                    ticketSchema.getPrice(),
                    ticketSchema.getStart_at(),
                    ticketSchema.getEnd_at(),
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    false
            ));
            return new ResponseEntity<>(new TicketSchema(tickets), HttpStatus.CREATED);
        } catch (Exception e) {
            if (errorMessage == null) {
                errorMessage = "Failed to save data";
            }
            logger.error(e.getMessage());
            throw new CustomException(errorMessage, HttpStatus.BAD_REQUEST);
        }

    }
}
