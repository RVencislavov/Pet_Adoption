package com.example.persistence.binding;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class UserAddBindingModel {
    @NotBlank
    @Length(min = 3, max = 20)
    private String userFirstName;
    @NotBlank
    @Length(min = 3, max = 20)
    private String userLastName;
    @NotBlank
    @Length(min = 3, max = 20)
    private String userEmail;
    @NotBlank
    @Length(min = 3, max = 20)
    private String userPhone;
}
