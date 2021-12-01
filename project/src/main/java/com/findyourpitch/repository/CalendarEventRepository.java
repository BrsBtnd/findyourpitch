package com.findyourpitch.repository;

import com.findyourpitch.entities.CalendarEvent;
import com.findyourpitch.entities.Pitch;
import com.findyourpitch.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Integer> {

    //find all pitches by a userID
    List<CalendarEvent> findPitchByUser(User user);

    //find pitches  by their ID-s
    List<CalendarEvent> findByUserAndPitch(User user, Pitch pitch);

    //find all users by pitchID
    List<CalendarEvent> findUserByPitch(CalendarEvent pitch);
}
