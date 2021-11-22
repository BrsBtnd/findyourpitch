package com.findyourpitch.controller;

import com.findyourpitch.entities.Pitch;
import com.findyourpitch.repository.PitchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PitchController {

    @Autowired
    private PitchRepository pitchRepository;

    @GetMapping("/pitches")
    public List<Pitch> getAllPitches() {
        return pitchRepository.findAll();
    }
}
