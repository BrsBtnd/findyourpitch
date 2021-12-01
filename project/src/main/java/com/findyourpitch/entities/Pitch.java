package com.findyourpitch.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pitches")
@EntityListeners(AuditingEntityListener.class)
public class Pitch {
    @Id
    @Column(name = "pitch_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pitchID;

    @Column(name = "pitch_name")
    private String pitchName;

    @Column(name = "pitch_type")
    private String pitchType;

    @Column(name = "country")
    private String country;

    @Column(name = "region")
    private String region;

    @Column(name = "city")
    private String city;

    public String getCountry() {
        return country;
    }

    @OneToMany(mappedBy = "pitch")
    Set<CalendarEvent> calendarEvents;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPitchID() {
        return pitchID;
    }

    public String getPitchName() {
        return pitchName;
    }

    public void setPitchName(String pitchName) {
        this.pitchName = pitchName;
    }

    public String getPitchType() {
        return pitchType;
    }

    public void setPitchType(String pitchType) {
        this.pitchType = pitchType;
    }
}
