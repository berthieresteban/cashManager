package com.cashmanager.products.web.controller;

import com.cashmanager.products.exception.ResourceNotFoundException;
import com.cashmanager.products.model.User;
import com.cashmanager.products.repository.UserRepository;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors; 
import java.util.stream.Stream; 
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@RestController
public class UserController {
    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping(value="/Users")
    public List<User>getUsers() {
        return UserRepository.findAll()
        .stream()
        .map(User -> {
            // User.setPassword("");
            return User;
        })
        .collect(Collectors.toList()); 
    }

    @GetMapping(value="/Users/{username:.*}")
    public ResponseEntity<?> getUserById(@PathVariable String username) {
        Optional<User> user = UserRepository.getUserByUsername(username);
        if (user.isPresent()) {
            return ResponseEntity.ok()
            .body(user.map(User -> {
                User.setPassword("");
                return User;
            }));
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/Users/{role:.*}")
    public User createUser(@Valid @RequestBody User userRequest, @PathVariable String role) {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User user = UserRepository.save(userRequest);
        String request = "INSERT INTO user_roles (username, role) VALUES ('"+userRequest.getUsername()+"', '"+role+"');";
        jdbcTemplate.update(request);
        return user;
    }

    @PostMapping("/Users/updateRole/{username:.*}/{role:.*}")
    public ResponseEntity<?> updateUserRole(@PathVariable String username, @PathVariable String role) {
        String request = "UPDATE user_roles SET role='"+role+"' WHERE username='"+username+"';";
        jdbcTemplate.update(request);
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @PutMapping("/Users/setEnabled/{username:.*}")
    public ResponseEntity<?> updateUser(@PathVariable String username, @Valid @RequestBody User userRequest) {
        Optional<User> user =  UserRepository.getUserByUsername(username);
        if (user.isPresent()) {
            return new ResponseEntity<>(
            user.map(User -> {
                User.setEnabled(userRequest.getEnabled());
                UserRepository.save(User);
                User.setPassword("");
                return User;
            }),
            HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/Users/{username:.*}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        Optional<User> user =  UserRepository.getUserByUsername(username);
        if (user.isPresent()) {
            return new ResponseEntity<>(
                user.map(User -> {
                    UserRepository.delete(User);
                    return ResponseEntity.ok().build();
                }),
                HttpStatus.OK);    
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }
}