package com.test.ticketing.service;

import com.test.ticketing.model.Tickets;

import java.util.Optional;

public interface TicketService {
    Optional<Tickets> findById(Long id);
}
