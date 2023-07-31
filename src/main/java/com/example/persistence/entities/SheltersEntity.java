package com.example.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shelters")
public class SheltersEntity {

    @Column(name = "shelter_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long shelterID;

    @Column(name = "shelter_name", nullable = false)
    private String shelterName;

    @Column(name = "shelter_city")
    private String shelterCity;

    @Column(name = "shelter_address")
    private String shelterAddress;

    @Column(name = "shelter_email", unique = true)
    private String shelterEmail;

    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    private List<ShelterPhoneEntity> shelterPhones;


    public SheltersEntity() {
    }

}
