package com.findyourpitch.controller;

import com.findyourpitch.entities.Pitch;
import com.findyourpitch.entities.CalendarEvent;
import com.findyourpitch.entities.User;
import com.findyourpitch.payload.request.CalendarEventRequest;
import com.findyourpitch.repository.CalendarEventRepository;
import com.findyourpitch.repository.PitchRepository;
import com.findyourpitch.repository.UserRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/users/{userID}/pitches")
    public List<CalendarEvent> getAllPitchesByUser(@PathVariable(value = "userID") Integer userID)
        throws ResourceNotFoundException {
        System.out.println(userID);
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

    @PostMapping("/users/{userID}/pitches")
    public CalendarEvent createCalendarEvent(@RequestBody CalendarEventRequest calendarEvent) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(calendarEvent.getStartDate(), formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(calendarEvent.getEndDate(), formatter);

        User user = userRepository.findById(calendarEvent.getUser())
                .orElseThrow(() -> new ResourceNotFoundException("User with userID " + calendarEvent.getUser() + " not found"));

        Pitch pitch = pitchRepository.findById(calendarEvent.getPitch())
                .orElseThrow(() -> new ResourceNotFoundException("Pitch with pitchID " + calendarEvent.getPitch() + " not found"));

        CalendarEvent createCalendarEvent = new CalendarEvent();
        createCalendarEvent.setStartDate(startDateTime);
        createCalendarEvent.setEndDate(endDateTime);
        createCalendarEvent.setUser(user);
        createCalendarEvent.setPitch(pitch);

        return calendarEventRepository.save(createCalendarEvent);
    }

}
