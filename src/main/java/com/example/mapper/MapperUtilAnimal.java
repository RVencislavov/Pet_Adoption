package com.example.mapper;

import com.example.persistence.entities.AnimalPhotoEntity;
import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperUtilAnimal {

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface MapPhotoList {
    }

    @MapPhotoList
    public List<String> photoListt(List<AnimalPhotoEntity> photos) {
        return photos.stream().map(AnimalPhotoEntity::getAnimalPhotoURL)
                .collect(Collectors.toList());
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface MapPhotoListt {
    }

    @MapPhotoListt
    public List<AnimalPhotoEntity> animalPhotoEntitiess(List<String> photos) {
        return photos.stream().map(AnimalPhotoEntity::new)
                .collect(Collectors.toList());
    }
}
