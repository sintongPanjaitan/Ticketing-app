package com.test.ticketing.service.impl;

import com.test.ticketing.model.Events;
import com.test.ticketing.model.Users;
import com.test.ticketing.repository.EventRepository;
import com.test.ticketing.repository.UserRepository;
import com.test.ticketing.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Optional<Events> findById(Long id){
        return eventRepository.findById(id);
    }

    public List<Events> findByNameContaining(String name) {
        return eventRepository.findByNameContaining(name);
    }

    @Override
    public Events saveEvents(Events events) {
        return eventRepository.save(events);
    }

    @Override
    public List<Events> findAll() {
        return eventRepository.findAll();
    }
}
