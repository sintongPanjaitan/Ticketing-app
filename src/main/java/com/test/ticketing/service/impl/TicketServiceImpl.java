package com.test.ticketing.service.impl;

import com.test.ticketing.model.Events;
import com.test.ticketing.model.Tickets;
import com.test.ticketing.repository.TicketRepository;
import com.test.ticketing.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Optional<Tickets> findById(Long id){
        return ticketRepository.findById(id);
    }
}
