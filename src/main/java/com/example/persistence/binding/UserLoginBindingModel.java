package com.example.persistence.binding;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter

public class UserLoginBindingModel {
    //equal AuthenticationRequest

    @NotBlank(message = "Email cannot be empty")
    private String userEmail;

    @NotBlank(message = "Password cannot be empty")
    private String userPassword;
}
