package com.example.persistence.repositories;

import com.example.persistence.entities.AnimalPhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalPhotoRepository extends JpaRepository<AnimalPhotoEntity, Long> {

}
