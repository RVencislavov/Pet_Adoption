package com.example.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "login_providers")
public class LoginProviderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id")
    private long providerId;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserEntity userId;
    @Column(name = "provider_name")
    private String providerName;
    @Column(name = "access_token")
    private String accessToken;
}