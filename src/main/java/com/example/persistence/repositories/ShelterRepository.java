package com.example.persistence.repositories;

import com.example.persistence.entities.SheltersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShelterRepository extends JpaRepository<SheltersEntity, Long> {
    Optional<SheltersEntity> findByShelterName(String shelterName);
}
