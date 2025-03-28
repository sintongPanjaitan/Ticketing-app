package com.test.ticketing.repository;

import com.test.ticketing.model.Events;
import com.test.ticketing.model.UserTicket;
import com.test.ticketing.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTicketRespository extends JpaRepository<UserTicket, Long> {
    List<UserTicket> findAllByUsers(Users users);
    List<UserTicket> findAllByEvents(Events events);
}
