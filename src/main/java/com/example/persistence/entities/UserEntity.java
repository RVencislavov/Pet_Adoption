package com.example.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity {
    @Column(name = "user_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userID;

    @Column(name = "user_first_name", nullable = false)
    private String userFirstName;

    @Column(name = "user_last_name", nullable = false)
    private String userLastName;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "user_photo_url")
    private String userPhotoURL;

    //questionMark
    @Column(name = "user_access_token")
    private String userAccessToken;

    //questionMark
    @Column(name = "user_refresh_token")
    private String userRefreshToken;

    @OneToMany(mappedBy = "userEntity")
    private List<TokenEntity> tokens;

    @Column(name = "user_reset_password_token")
    private String userResetPasswordToken;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Set<AuthorityEntity> authorities = new HashSet<>();

    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    @Column(name = "enabled" ,nullable = false, columnDefinition = "boolean default true")
    private boolean enabled;

    public UserEntity() {
    }


}

