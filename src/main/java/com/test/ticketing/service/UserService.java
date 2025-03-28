package com.test.ticketing.service;

import com.test.ticketing.model.Users;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<Users> findByNameContaining(String name);
    List<Users> findAll();
    Users saveUser(Users user);
    Optional<Users> findById(Long id);

}
