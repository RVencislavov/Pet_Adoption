package com.example.persistence.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserGoogleLoginModel {
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
    private String userPhotoURL;
    private String userAccessToken;
    private String userRefreshToken;

}
