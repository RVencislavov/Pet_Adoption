package com.example.persistence.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class AddressViewModel {

    private String postCode;
    private String city;
    private String country;
    private String address1;

}
