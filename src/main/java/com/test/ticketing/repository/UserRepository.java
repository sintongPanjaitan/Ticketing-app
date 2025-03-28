package com.test.ticketing.repository;

import com.test.ticketing.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    List<Users> findByNameContaining(String name);
    Optional<Users> findByName(String name);
}
