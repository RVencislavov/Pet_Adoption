package com.example.persistence.entities;

import com.example.persistence.enums.AnimalSexEnum;
import com.example.persistence.enums.TokenTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "token")
public class TokenEntity {
    @Column(name = "token_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tokenID;

    @Column(name = "token", unique = true)
    private String token;

    @Column(name = "token_type")
    @Enumerated(EnumType.STRING)
    private TokenTypeEnum token_type = TokenTypeEnum.BEARER;

    @Column(name = "revoked")
    private boolean revoked;

    @Column(name = "expired")
    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(name = "refresh_token")
    private String refreshToken;

}
