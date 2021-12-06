package com.findyourpitch.controller;

import com.findyourpitch.entities.User;
import com.findyourpitch.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OwnerController {

    @Autowired
    private OwnerRepository ownerRepository;

    //az osszes felhasznalot adja vissza, akiknek van palyalyuk
    @GetMapping("/owners")
    public List<User> getAllOwners() throws ResourceNotFoundException {
        try {
            return ownerRepository.findOwners();
        }catch (ResourceNotFoundException exception) {
            throw new ResourceNotFoundException("Owners not found");
        }
    }
    //az ownerID-val rendelkezo felhasznalot adja vissza
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
