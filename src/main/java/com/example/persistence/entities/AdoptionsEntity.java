package com.example.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "adoptions")
@Getter
@Setter
public class AdoptionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adoption_id")
    private long adoptionId;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private AnimalsEntity animal;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "adopter_first_name", nullable = false)
    private String adopterFirstName;

    @Column(name = "adopter_last_name", nullable = false)
    private String adopterLastName;

    @Column(name = "adopter_phone", nullable = false)
    private String adopterPhone;

    @Column(name = "adopter_email", nullable = false)
    private String adopterEmail;

    @Column(name = "adopter_address", nullable = false)
    private String adopterAddress;

    @Column(name = "adoption_date", nullable = false)
    private LocalDate adoptionDate;

    public AdoptionsEntity() {
    }
}