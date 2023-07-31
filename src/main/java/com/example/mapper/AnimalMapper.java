package com.example.mapper;

import com.example.persistence.binding.AnimalAddBindingModel;
import com.example.persistence.binding.UpdateAnimalBindingModel;
import com.example.persistence.entities.AnimalsEntity;
import com.example.persistence.entities.SheltersEntity;
import com.example.persistence.entities.SizeCategoryEntity;
import com.example.persistence.view.AnimalViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", uses = MapperUtil.class)
@Component
public interface AnimalMapper {
    @Mapping(target = "animalName", source = "model.animalName")
    @Mapping(target = "animalSpecies", source = "model.animalSpecies")
    @Mapping(target = "gender", source = "model.animalGender")
    @Mapping(target = "animalAge", source = "model.animalAge")
    @Mapping(target = "sizeCategory", source = "sizeCategory")
    @Mapping(target = "animalCharacteristics", source = "model.animalCharacteristics")
    @Mapping(target = "shelter", source = "shelters")
    @Mapping(target = "animalPhotos", ignore = true)
    AnimalsEntity toAnimalEntity(AnimalAddBindingModel model, SizeCategoryEntity sizeCategory, SheltersEntity shelters);

    @Mapping(target = "animalName", source = "updateModel.animalName")
    @Mapping(target = "animalAge", source = "updateModel.animalAge")
    @Mapping(target = "animalCharacteristics", source = "updateModel.animalCharacteristics")
    @Mapping(target = "adopted", source = "updateModel.adopted")
    @Mapping(target = "sizeCategory", source = "sizeCategoryEntity")
    AnimalsEntity updateAnimalEntity(
            UpdateAnimalBindingModel updateModel, AnimalsEntity existingAnimal, SizeCategoryEntity sizeCategoryEntity
    );


    List<AnimalsEntity> toAnimalEntityList(List<AnimalAddBindingModel> models);

    List<AnimalAddBindingModel> toAnimalBindingModelList(List<AnimalsEntity> entities);

    @Mapping(target = "sizeCategory", source = "existingAnimal.sizeCategory.category")
    @Mapping(target = "animalId", source = "existingAnimal.animalID")
    AnimalViewModel toAnimalViewModel(AnimalsEntity existingAnimal);
}

