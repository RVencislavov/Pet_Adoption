package com.example.persistence.repositories;

import com.example.persistence.entities.ShelterPhoneEntity;
import com.example.persistence.entities.SheltersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelterPhonesRepository extends JpaRepository<ShelterPhoneEntity, Long> {
    List<ShelterPhoneEntity> findByShelter(SheltersEntity shelters);

}
