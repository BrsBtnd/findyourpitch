package com.findyourpitch.controller;

import com.findyourpitch.entities.Pitch;
import com.findyourpitch.entities.User;
import com.findyourpitch.repository.PitchRepository;
import com.findyourpitch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class PitchOwnerController {

    @Autowired
    private PitchRepository pitchRepository;

    @Autowired
    private UserRepository userRepository;

    //egy felhasznalonak a hozza tartozo palyakat adja meg
    @GetMapping("/owners/{ownerID}/pitches")
    public List<Pitch> getAllPitchesByOwner(@PathVariable(value = "ownerID") Integer ownerID)
            throws ResourceNotFoundException {
        User user = userRepository.findById(ownerID)
                .orElseThrow(() -> new ResourceNotFoundException("User " + ownerID + " not found"));

        try {
            return pitchRepository.findPitchByUser(user);
        } catch (ResourceNotFoundException exception) {
            throw new ResourceNotFoundException("Pitches from user " + ownerID + " not found");
        }
    }
    //egy felhasznalonak a hozza tartozo paplyat adja meg
    @GetMapping("owners/{ownerID}/pitches/{pitchID}")
    public List<Pitch> getPitchByOwner(
            @PathVariable(value = "ownerID") Integer ownerID, @PathVariable(value = "pitchID") Integer pitchID
    ) throws ResourceNotFoundException {
        try {
            User user = userRepository.findById(ownerID)
                    .orElseThrow(() -> new ResourceNotFoundException("User " + ownerID + " not found"));

            Pitch pitch = pitchRepository.findById(pitchID)
                    .orElseThrow(() -> new ResourceNotFoundException("Pitch " + pitchID + " not found"));

            return pitchRepository.findPitchByUserAndPitchID(user, pitchID);
        } catch (ResourceNotFoundException exception) {
            throw new ResourceNotFoundException("Pitch " + pitchID + " and user " + ownerID + "not found");
        }
    }

}
