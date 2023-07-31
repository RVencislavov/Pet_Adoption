package com.example.service;

import com.example.persistence.binding.ShelterAddBindingModel;
import com.example.persistence.entities.SheltersEntity;
import com.example.persistence.view.AddShelterViewModel;

import java.util.List;

public interface ShelterService {

    String addShelter(ShelterAddBindingModel shelterViewModel);

    List<SheltersEntity> getAllShelters();

    AddShelterViewModel getShelterById(long shelterId);

    String updateShelter(long id, ShelterAddBindingModel shelterAddBindingModel);
}