package com.example.persistence.view;

import com.example.persistence.entities.ShelterPhoneEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AddShelterViewModel {
    private String shelterName;
    private String shelterCity;
    private String shelterAddress;
    private String shelterEmail;
    private List<String> shelterPhones;
}
