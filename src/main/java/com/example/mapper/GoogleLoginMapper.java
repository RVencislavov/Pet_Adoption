package com.example.mapper;

import com.example.persistence.binding.AnimalAddBindingModel;
import com.example.persistence.entities.AnimalsEntity;
import com.example.persistence.entities.SheltersEntity;
import com.example.persistence.entities.SizeCategoryEntity;
import com.example.persistence.entities.UserEntity;
import com.example.persistence.view.UserGoogleLoginModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = MapperUtil.class)
@Component
public interface GoogleLoginMapper {
    @Mapping(target = "userFirstName", source = "userFirstName")
    @Mapping(target = "userLastName", source = "userLastName")
    @Mapping(target = "userEmail", source = "userEmail")
    @Mapping(target = "userPhotoURL", source = "userPhotoURL")
    @Mapping(target = "userAccessToken", source = "userAccessToken")
    UserEntity toUserEntity(UserGoogleLoginModel userGoogleLoginModel);

}
