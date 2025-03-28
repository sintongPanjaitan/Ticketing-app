package com.test.ticketing.repository;

import com.test.ticketing.model.Events;
import com.test.ticketing.model.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Tickets, Long> {

    Optional<Tickets> findByEvents(Events events);
}
