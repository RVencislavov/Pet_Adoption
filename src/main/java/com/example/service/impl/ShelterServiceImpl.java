package com.example.service.impl;

import com.example.persistence.binding.ShelterAddBindingModel;
import com.example.persistence.entities.ShelterPhoneEntity;
import com.example.persistence.entities.SheltersEntity;
import com.example.persistence.repositories.ShelterPhonesRepository;
import com.example.persistence.repositories.ShelterRepository;
import com.example.persistence.view.AddShelterViewModel;

import com.example.service.ShelterService;
import com.example.mapper.ShelterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShelterServiceImpl implements ShelterService {

    private final ShelterRepository shelterRepository;
    private final ShelterPhonesRepository shelterPhonesRepository;
    private final ShelterMapper shelterMapper;

    @Autowired
    public ShelterServiceImpl(ShelterRepository shelterRepository, ShelterPhonesRepository shelterPhonesRepository, ShelterMapper shelterMapper) {
        this.shelterRepository = shelterRepository;
        this.shelterPhonesRepository = shelterPhonesRepository;
        this.shelterMapper = shelterMapper;
    }


    @Override
    public String addShelter(ShelterAddBindingModel shelterAddBindingModel) {
        SheltersEntity shelter = shelterMapper.toShelterEntity(shelterAddBindingModel);
        SheltersEntity savedShelter = shelterRepository.save(shelter);

        List<ShelterPhoneEntity> shelterPhoneEntities = new ArrayList<>();
        for (String phone : shelterAddBindingModel.getShelterPhones()) {
            ShelterPhoneEntity shelterPhoneEntity = new ShelterPhoneEntity();
            shelterPhoneEntity.setShelterPhones(phone);
            shelterPhoneEntity.setShelter(savedShelter);
            shelterPhoneEntities.add(shelterPhoneEntity);
        }

        shelterPhonesRepository.saveAll(shelterPhoneEntities);
        return "redirect:/shelters";
    }


    @Override
    public List<SheltersEntity> getAllShelters() {

        return shelterRepository.findAll();
    }

    @Override
    public AddShelterViewModel getShelterById(long id) {
        SheltersEntity existingShelter = shelterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No shelter found with id " + id));
        AddShelterViewModel shelterViewModel = shelterMapper.toShelterViewModel(existingShelter);

        return shelterViewModel;
    }


    @Override
    public String updateShelter(long id, ShelterAddBindingModel shelterAddBindingModel) {
        SheltersEntity existingShelter = shelterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No shelter find with id" + id));
        List<ShelterPhoneEntity> phoneEntities = shelterPhonesRepository.findByShelter(existingShelter);
        SheltersEntity updatedShelter = shelterMapper.updateEntity(shelterAddBindingModel, existingShelter);
        for (int i = 0; i < shelterAddBindingModel.getShelterPhones().size(); i++) {
            phoneEntities.get(i).setShelterPhones(shelterAddBindingModel.getShelterPhones().get(i));
        }

        updatedShelter.setShelterPhones(phoneEntities);
        shelterRepository.save(updatedShelter);
        return "redirect:/shelters";
    }
}