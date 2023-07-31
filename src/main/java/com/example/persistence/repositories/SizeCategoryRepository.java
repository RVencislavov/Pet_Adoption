package com.example.persistence.repositories;

import com.example.persistence.entities.SizeCategoryEntity;
import com.example.persistence.enums.SizeCategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeCategoryRepository extends JpaRepository<SizeCategoryEntity, Long> {
    SizeCategoryEntity findByCategory(SizeCategoryEnum category);
}
