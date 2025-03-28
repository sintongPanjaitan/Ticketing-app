package com.test.ticketing.controller;

import com.test.ticketing.model.Events;
import com.test.ticketing.repository.EventRepository;
import com.test.ticketing.schema.CustomException;
import com.test.ticketing.schema.EventSchema;
import com.test.ticketing.service.EventService;
import com.test.ticketing.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {  // Constructor injection
        this.eventService = eventService;
    }

    @GetMapping("/event")
    public ResponseEntity<List<EventSchema>> getAllEvents(@RequestParam(required = false) String name) {
        try {
            List<Events> events = new ArrayList<Events>();
            List<EventSchema> eventSchemas = new ArrayList<EventSchema>();
            if (name == null) {
                eventService.findAll().forEach(events::add);
            }
            else {
                eventService.findByNameContaining(name).forEach(events::add);
            }
            for (Events  event : events) {
                eventSchemas.add(new EventSchema(event));
            }
            if (events.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(eventSchemas, HttpStatus.OK);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            throw new CustomException("Failed to get data", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/event")
    public ResponseEntity<Events> addUser(@RequestBody EventSchema eventSchema) {
        try {
            Events events = eventService.saveEvents(new Events(
                    eventSchema.getName(),
                    eventSchema.getPlace(),
                    eventSchema.getMaximum_tickets(),
                    eventSchema.getEvent_date(),
                    eventSchema.getDescription(),
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    false
            ));
            return new ResponseEntity<>(events, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new CustomException("Failed to save data", HttpStatus.BAD_REQUEST);
        }

    }

}
