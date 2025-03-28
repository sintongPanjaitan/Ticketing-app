package com.test.ticketing.service.impl;

import com.test.ticketing.model.Users;
import com.test.ticketing.repository.UserRepository;
import com.test.ticketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Users> findByNameContaining(String name) {
        return userRepository.findByNameContaining(name);
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users saveUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<Users> findById(Long id){
        return userRepository.findById(id);
    }

}
