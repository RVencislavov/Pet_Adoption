package com.example.persistence.binding;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ShelterAddBindingModel {
    @NotBlank
    @Length(min = 3, max = 20)
    private String shelterName;
    @NotBlank
    @Length(min = 3, max = 20)
    private String shelterCity;
    @NotBlank
    @Length(min = 3, max = 20)
    private String shelterAddress;
    @NotBlank
    @Length(min = 3, max = 20)
    private String shelterEmail;
    @NotBlank
    private List<String> shelterPhones;
}
