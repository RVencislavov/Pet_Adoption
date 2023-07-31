package com.example.persistence.repositories;

import com.example.persistence.entities.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    @Query(value = """
            select t from TokenEntity t inner join t.userEntity u
            where u.userID = :id and (t.expired = false or t.revoked = false)
            """)
    List<TokenEntity> findAllValidTokenByUser(Long id);


    Optional<TokenEntity> findByToken(String token);
}


