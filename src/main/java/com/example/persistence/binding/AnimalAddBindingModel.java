package com.example.persistence.binding;


import com.example.persistence.enums.AnimalSexEnum;
import com.example.persistence.enums.AnimalSpeciesEnum;
import com.example.persistence.enums.SizeCategoryEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AnimalAddBindingModel {
    @NotBlank(message = "Please upload at least one photo")
    private List<MultipartFile> animalPhoto;
    @NotBlank(message = "Cannot be empty")
    @Length(min = 3, max = 20)
    private String animalName;
    private AnimalSpeciesEnum animalSpecies;
    private AnimalSexEnum animalGender;
    @Min(value = 1, message = "Age must be greater than 0")
    private int animalAge;
    private SizeCategoryEnum AnimalSize;
    @NotBlank(message = "Cannot be empty")
    @Length(min = 3, max = 55)
    private String animalCharacteristics;
    @NotBlank(message = "Shelter name cannot be empty")
    private String shelterName;
}


