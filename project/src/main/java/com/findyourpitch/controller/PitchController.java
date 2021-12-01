package com.findyourpitch.controller;

import com.findyourpitch.entities.Pitch;
import com.findyourpitch.repository.PitchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PitchController {

    @Autowired
    private PitchRepository pitchRepository;

    @GetMapping("/pitches")
    public List<Pitch> getAllPitches() {
        return pitchRepository.findAll();
    }

    @GetMapping("/pitches/{id}")
    public ResponseEntity<Pitch> getPhoneByID(@PathVariable(value = "id") Integer pitchID)
        throws ResourceNotFoundException {
        Pitch pitch = pitchRepository.findById(pitchID)
                .orElseThrow(() -> new ResourceNotFoundException("Pitch " + pitchID + " not found"));
        return ResponseEntity.ok().body(pitch);
    }

    @PostMapping("/pitches")
    public Pitch createPitch(@RequestBody Pitch pitch) {

        return pitchRepository.save(pitch);
    }

    @PutMapping("/pitches/{id}")
    public ResponseEntity<Pitch> updatePitch(
            @PathVariable(value = "id") Integer pitchID, @Valid @RequestBody Pitch pitchDetails
    ) throws ResourceNotFoundException {
        Pitch pitch = pitchRepository.findById(pitchID)
                .orElseThrow(() -> new ResourceNotFoundException("Pitch " + pitchID + " not found"));

        pitch.setPitchName(pitchDetails.getPitchName());
        pitch.setPitchType(pitchDetails.getPitchType());

        final Pitch updatedPitch = pitchRepository.save(pitch);
        return ResponseEntity.ok(updatedPitch);
    }

    @DeleteMapping("/pitches/{id}")
    public Map<String, Boolean> deletePitch(@PathVariable(value = "id") int pitchID)
        throws ResourceNotFoundException {

        Pitch pitch = pitchRepository.findById(pitchID)
                .orElseThrow(() -> new ResourceNotFoundException("Pitch " + pitchID + " not found"));

        pitchRepository.delete(pitch);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);

        return response;
    }
}
