package com.example.mapper;

import com.example.persistence.entities.ShelterPhoneEntity;
import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperUtil {

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface MapPhoneList {
    }

    @MapPhoneList
    public List<String> mapPhonesToString(List<ShelterPhoneEntity> phones) {
        return phones.stream().map(ShelterPhoneEntity::getShelterPhones)
                .collect(Collectors.toList());
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface MapPhoneEntity {
    }

    @MapPhoneEntity
    public List<ShelterPhoneEntity> mapToShelterPhoneEntities(List<String> phones) {
        return phones.stream().map(ShelterPhoneEntity::new)
                .collect(Collectors.toList());
    }
}
