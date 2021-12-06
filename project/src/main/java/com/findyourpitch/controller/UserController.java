package com.findyourpitch.controller;

import com.findyourpitch.entities.User;
import com.findyourpitch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable(value = "id") Integer userID)
        throws ResourceNotFoundException {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User " + userID + " not found"));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {

        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable(value = "id") Integer userID, @Valid @RequestBody User userDetails)
            throws ResourceNotFoundException{
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User " + userID + " not found") );

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setUserAge(userDetails.getUserAge());
        user.setUserRole(userDetails.getUserRole());
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());

        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
}
