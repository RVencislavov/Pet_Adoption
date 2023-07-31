package com.example.persistence.binding;

import com.example.util.annotation.UniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter

public class UserRegisterBindingModel {
    //equals RegisterRequest

    @NotBlank
    @Length(min = 3, max = 20, message = "First name must be between 3 and 20 characters")
    private String userFirstName;

    @NotBlank
    @Length(min = 3, max = 20, message = "Last name must be between 3 and 20 characters")
    private String userLastName;

    @NotBlank
    @UniqueEmail
    @Length(min = 5, max = 35, message = "Email must be at least 5 characters")
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?" +
            "(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$", message = "Email is not valid")
    private String userEmail;

    @NotBlank
    @Length(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).*$",
            message = "Password must contain at least 1 special symbol, 1 uppercase letter, 1 lowercase letter, and 1 digit")
    private String userPassword;

    @NotBlank
    @Length(min = 6, max = 30, message = "Password must be between 6 and 30")
    private String confirmPassword;

    @NotBlank
    @Pattern(regexp = "^\\+?359\\d{12}$", message = "Phone number must be in the format +359xxxxxxxxx")
    private String userPhone;

    public UserRegisterBindingModel() {
        this.userPhone = "+359";
    }
}
