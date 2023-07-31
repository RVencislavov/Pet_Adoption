package com.example.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shelters_phones")
@Getter
@Setter
@NoArgsConstructor
public class ShelterPhoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelter_phone_id")
    private long shelterPhoneID;

    @Column(name = "shelter_phone")
    private String shelterPhones;

    @JoinColumn(name = "shelter_id")
    @ManyToOne
    private SheltersEntity shelter;

    public ShelterPhoneEntity(String shelterPhone) {

        this.shelterPhones = shelterPhone;
    }

}
