package com.example.persistence.repositories;

import com.example.persistence.entities.LoginProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginProviderRepository extends JpaRepository <LoginProviderEntity, Long> {
}
