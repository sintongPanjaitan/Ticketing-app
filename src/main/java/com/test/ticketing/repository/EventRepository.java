package com.test.ticketing.repository;

import com.test.ticketing.model.Events;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Events, Long> {

    List<Events> findByNameContaining(String name);

    Optional<Events> findById(long id);
}
