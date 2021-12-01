package com.findyourpitch.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.*;

@Entity
@Table(name = "calendar_events")
@EntityListeners(AuditingEntityListener.class)
public class CalendarEvent {

    @Id
    @Column(name = "calendar_event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int calendarEventID;

    @Column(name = "start_date")
    LocalDateTime startDate;

    @Column(name = "end_date")
    LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "pitch_id")
    Pitch pitch;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    public int getCalendarEventID() {
        return calendarEventID;
    }

    public void setCalendarEventID(int calendarEventID) {
        this.calendarEventID = calendarEventID;
    }

    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
