package com.example.persistence.repositories;

import com.example.persistence.entities.AnimalsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalsEntity, Long> {
    List<AnimalsEntity> findByShelter_ShelterID(long shelterId);
}