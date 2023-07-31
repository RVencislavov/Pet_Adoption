package com.example.persistence.repositories;

import com.example.persistence.entities.AuthorityEntity;
import com.example.persistence.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
    Set<AuthorityEntity> findAllByAuthority(RoleEnum user_role);

    //for future change role
    AuthorityEntity findByAuthority(RoleEnum roleEnum);
}
