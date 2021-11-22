package com.findyourpitch.repository;

import com.findyourpitch.entities.Pitch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PitchRepository extends JpaRepository<Pitch, Integer> {

}
