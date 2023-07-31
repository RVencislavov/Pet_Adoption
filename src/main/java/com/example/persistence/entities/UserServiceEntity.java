package com.example.persistence.entities;

import com.example.persistence.entities.AuthorityServiceEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserServiceEntity {
    private Long id;

    private String email;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String phone;
    private MultipartFile picture;
    private String profilePicture;

    private Set<AuthorityServiceEntity> authorities;
}
