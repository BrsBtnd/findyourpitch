package com.findyourpitch.controller;

import com.findyourpitch.entities.Pitch;
import com.findyourpitch.entities.CalendarEvent;
import com.findyourpitch.entities.User;
import com.findyourpitch.repository.CalendarEventRepository;
import com.findyourpitch.repository.PitchRepository;
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
public class CalendarEventController {

    @Autowired
    private CalendarEventRepository calendarEventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PitchRepository pitchRepository;

    /*
     *
     * @param userID
     * @return the list of Calendar Events, when de user with userID have/has events at all pitches.
     * @throws ResourceNotFoundException
     */
    //egy felhasznalonak az osszes palyara vonatkozo foglalasat adja meg
    @GetMapping("/users/{userID}/pitches")
    public List<CalendarEvent> getAllPitchesByUser(@PathVariable(value = "userID") Integer userID)
        throws ResourceNotFoundException {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User " + userID + " not found"));

        try {
            return calendarEventRepository.findPitchByUser(user);
        } catch (ResourceNotFoundException exception) {
            throw new ResourceNotFoundException("Pitches from user " + userID + " not found");
        }

    }

    /*
     *
     * @param userID
     * @param pitchID
     * @return the list of Calendar Event, when a user with userID have/has events at a pitch with pitchID
     * @throws ResourceNotFoundException
     */
    //egy felhasznalonak egy adott palyara vonatkozo osszes foglalasat adja meg
    @GetMapping("users/{userID}/pitches/{pitchID}")
    public List<CalendarEvent> getPitchByUser(
        @PathVariable(value = "userID") Integer userID, @PathVariable(value = "pitchID") Integer pitchID
    ) throws ResourceNotFoundException {
        try {
            User user = userRepository.findById(userID)
                    .orElseThrow(() -> new ResourceNotFoundException("User " + userID + " not found"));

            Pitch pitch = pitchRepository.findById(pitchID)
                    .orElseThrow(() -> new ResourceNotFoundException("Pitch " + pitchID + " not found"));

            return calendarEventRepository.findByUserAndPitch(user, pitch);
        } catch (ResourceNotFoundException exception) {
            throw new ResourceNotFoundException("Pitch " + pitchID + " and user " + userID + "not found");
        }
    }
}
