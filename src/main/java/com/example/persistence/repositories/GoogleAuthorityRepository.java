package com.example.persistence.repositories;

import com.example.persistence.entities.AuthorityEntity;
import com.example.persistence.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface GoogleAuthorityRepository extends JpaRepository<AuthorityEntity , Long> {
    Set<AuthorityEntity> findAllByAuthority(RoleEnum user_role);
}
