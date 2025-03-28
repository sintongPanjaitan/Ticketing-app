package com.test.ticketing.service.impl;

import com.test.ticketing.model.Events;
import com.test.ticketing.model.Tickets;
import com.test.ticketing.model.UserTicket;
import com.test.ticketing.model.Users;
import com.test.ticketing.repository.TicketRepository;
import com.test.ticketing.repository.UserRepository;
import com.test.ticketing.schema.ResponseBooking;
import jakarta.transaction.Transactional;
import com.test.ticketing.repository.UserTicketRespository;
import com.test.ticketing.schema.BookingSchema;
import com.test.ticketing.service.UserTicketService;
import jakarta.persistence.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserTicketServiceImpl implements UserTicketService {

   @Autowired
    private UserTicketRespository userTicketRespository;

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private TicketRepository ticketRepository;

    @Override
    public List<UserTicket> findAll() {
        return userTicketRespository.findAll();
    }

    @Override
    public List<UserTicket> findAllByUsers(Users users) {
        return userTicketRespository.findAllByUsers(users);
    }

    @Override
    public List<UserTicket> findAllByEvents(Events events){
        return userTicketRespository.findAllByEvents(events);
    }

    @Transactional
    public List<ResponseBooking> generateTicket(BookingSchema bookingSchema){
        int maxRetries = 3;
        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                return processTransaction(bookingSchema);
            } catch (OptimisticLockException e) {
                attempt++;
                if (attempt == maxRetries) {
                    System.out.println("Failed after multiple retries, please try again later.");
                    throw new RuntimeException("Failed after multiple retries, please try again later.");
                }
            }
        }
        throw new RuntimeException("Unexpected error");
    }

    private List<ResponseBooking> processTransaction(BookingSchema bookingSchema) {
        Optional<Tickets> tickets = ticketRepository.findById(bookingSchema.getTicket_id());
        List<ResponseBooking> responseBookings = new ArrayList<>();
        if (tickets.isPresent() && tickets.get().getMaximum_tickets() > bookingSchema.getQuantity()) {
            Tickets ticketsUpdate = tickets.get();
            ticketsUpdate.setMaximum_tickets(ticketsUpdate.getMaximum_tickets() - bookingSchema.getQuantity());
            ticketsUpdate.setModifed_at(LocalDateTime.now());
            System.out.println(ticketsUpdate.toString());
            Tickets updatedTickets = ticketRepository.save(ticketsUpdate);

            //Create User Ticket
            Optional<Users> users = userRepository.findById(bookingSchema.getUser_id());
            for (int i = 0; i < bookingSchema.getQuantity(); i++) {
                UserTicket userTicket = new UserTicket();
                userTicket.setUsers(users.get());
                userTicket.setEvents(ticketsUpdate.getEvents());
                userTicket.setTicket_code(UUID.randomUUID().toString());
                userTicket.setCreated_at(LocalDateTime.now());
                userTicket.setModifed_at(LocalDateTime.now());
                userTicket.setIs_deleted(false);
                userTicketRespository.save(userTicket);
                responseBookings.add(new ResponseBooking(userTicket));
            }

        }else {
            System.out.println("Unexpected error");
            throw new RuntimeException("Unexpected error");
        }

        return responseBookings;

    }
}
