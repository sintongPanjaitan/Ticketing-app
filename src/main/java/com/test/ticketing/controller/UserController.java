package com.test.ticketing.controller;

import com.test.ticketing.model.Users;
import com.test.ticketing.repository.UserRepository;
import com.test.ticketing.schema.CustomException;
import com.test.ticketing.schema.UserSchema;
import com.test.ticketing.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {  // Constructor injection
        this.userService = userService;
    }

//    @GetMapping("/user")
//    public ResponseEntity<List<Users>> getAllUsers(@RequestParam(required = false) String name) {
//        return ResponseEntity.ok(userService.findByNameContaining(name));
//    }

    @GetMapping("/user")
    public ResponseEntity<List<UserSchema>> getAllUsers(@RequestParam(required = false) String name) {
        try {
            List<Users> users = new ArrayList<Users>();
            List<UserSchema> usersSchema = new ArrayList<UserSchema>();
            if (name == null) {
                userService.findAll().forEach(users::add);
            }
            else {
                userService.findByNameContaining(name).forEach(users::add);
            }
            for (Users user : users) {
                usersSchema.add(new UserSchema(user));
            }
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }



            return new ResponseEntity<>(usersSchema, HttpStatus.OK);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            throw new CustomException("Failed to get data", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<Users> addUser(@RequestBody UserSchema userSchema) {
        try {
            Users users = userService.saveUser(new Users(
                    userSchema.getName(),
                    userSchema.getIdentity_number(),
                    userSchema.getPhone_number(),
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    false
            ));
            return new ResponseEntity<>(users, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new CustomException("Failed to save data", HttpStatus.BAD_REQUEST);
        }

    }
}
