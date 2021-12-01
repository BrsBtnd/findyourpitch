package com.findyourpitch.controller;

import com.findyourpitch.entities.Pitch;
import com.findyourpitch.entities.User;
import com.findyourpitch.repository.OwnerRepository;
import com.findyourpitch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OwnerController {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/owners")
    public List<User> getAllOwners() throws ResourceNotFoundException {
        try {
            return ownerRepository.findOwners();
        }catch (ResourceNotFoundException exception) {
            throw new ResourceNotFoundException("Owners not found");
        }
    }

    @GetMapping("/owners/{ownerID}")
    public List<User> getOwnerByID(@PathVariable(value = "ownerID") Integer ownerID)
        throws ResourceNotFoundException {
        try {
            return ownerRepository.findOwnerByID(ownerID);
        } catch (ResourceNotFoundException exception) {
            throw new ResourceNotFoundException("Owner " + ownerID + " not found");
        }
    }

}
