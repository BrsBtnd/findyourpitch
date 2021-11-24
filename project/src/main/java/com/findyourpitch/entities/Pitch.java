package com.findyourpitch.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

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
