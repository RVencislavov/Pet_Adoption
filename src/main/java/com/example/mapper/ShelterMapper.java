package com.example.mapper;

import com.example.persistence.binding.ShelterAddBindingModel;
import com.example.persistence.entities.ShelterPhoneEntity;
import com.example.persistence.entities.SheltersEntity;
import com.example.persistence.view.AddShelterViewModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@Mapper(componentModel = "spring", uses = MapperUtil.class)
public interface ShelterMapper {

    @Mapping(target = "shelterName", source = "model.shelterName")
    @Mapping(target = "shelterCity", source = "model.shelterCity")
    @Mapping(target = "shelterAddress", source = "model.shelterAddress")
    @Mapping(target = "shelterEmail", source = "model.shelterEmail")
    @Mapping(target = "shelterPhones", ignore = true)
    SheltersEntity toShelterEntity(ShelterAddBindingModel model);

    @Mapping(target = "shelterName", source = "entity.shelterName")
    @Mapping(target = "shelterCity", source = "entity.shelterCity")
    @Mapping(target = "shelterAddress", source = "entity.shelterAddress")
    @Mapping(target = "shelterEmail", source = "entity.shelterEmail")
    //@Mapping(target = "shelterPhones", ignore = true)
    @Mapping(target = "shelterPhones", source = "entity.shelterPhones", qualifiedBy = MapperUtil.MapPhoneList.class)
    AddShelterViewModel toShelterViewModel(SheltersEntity entity);


    @Mapping(target = "shelterName", source = "bindingModel.shelterName")
    @Mapping(target = "shelterCity", source = "bindingModel.shelterCity")
    @Mapping(target = "shelterAddress", source = "bindingModel.shelterAddress")
    @Mapping(target = "shelterEmail", source = "bindingModel.shelterEmail")
    @Mapping(target = "shelterPhones", ignore = true)
        //@Mapping(target = "shelterPhones", source = "bindingModel.shelterPhones", qualifiedBy = MapperUtil.MapPhoneEntity.class)
    SheltersEntity updateEntity(ShelterAddBindingModel bindingModel,
                                SheltersEntity existingShelter);

}
