package com.example.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "animal_photos")
public class AnimalPhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_photo_id")
    private long animalPhotoId;

    @Column(name = "animal_photo_url", nullable = false)
    private String animalPhotoURL;

    @JoinColumn(name = "animal_id")
    @ManyToOne
    private AnimalsEntity animal;

    public AnimalPhotoEntity(String animalPhotoURL) {
        this.animalPhotoURL = animalPhotoURL;
    }

    public AnimalPhotoEntity() {

    }

}
