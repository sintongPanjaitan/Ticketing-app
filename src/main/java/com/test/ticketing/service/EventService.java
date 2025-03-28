package com.test.ticketing.service;

import com.test.ticketing.model.Events;
import com.test.ticketing.model.Users;

import java.util.List;
import java.util.Optional;

public interface EventService {
    Optional<Events> findById(Long id);
    List<Events> findAll();
    Events saveEvents(Events events);
    List<Events> findByNameContaining(String name);
}
