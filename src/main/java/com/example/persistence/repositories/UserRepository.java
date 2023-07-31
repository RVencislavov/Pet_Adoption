package com.example.persistence.repositories;

import com.example.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findUserByUserEmail(String email);

    @Query("SELECT u FROM UserEntity u WHERE u.userEmail = ?1")
    public UserEntity findByEmail(String email);

    @Query("SELECT u FROM UserEntity u WHERE u.verificationCode = ?1")
    public UserEntity findByVerificationCode(String code);

    public UserEntity findByUserResetPasswordToken(String reset_password_token);

    Optional<UserEntity> findByUserEmail(String userEmail);

    boolean existsByUserEmail(String email);
}

