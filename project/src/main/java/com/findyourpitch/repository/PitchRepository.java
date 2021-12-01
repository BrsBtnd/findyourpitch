package com.findyourpitch.repository;

import com.findyourpitch.entities.Pitch;
import com.findyourpitch.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PitchRepository extends JpaRepository<Pitch, Integer> {

    List<Pitch> findPitchByUser(User userID);

    List<Pitch> findPitchByUserAndPitchID(User user, Integer pitchID);
}
