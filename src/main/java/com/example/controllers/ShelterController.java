package com.example.controllers;

import com.example.persistence.binding.ShelterAddBindingModel;
import com.example.persistence.entities.AnimalsEntity;
import com.example.persistence.entities.SheltersEntity;
import com.example.persistence.view.AddShelterViewModel;
import com.example.service.AnimalService;
import com.example.service.ShelterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/shelters")
public class ShelterController {
    private final ShelterService shelterService;
    private final AnimalService animalService;

    public ShelterController(ShelterService shelterService, AnimalService animalService) {
        this.shelterService = shelterService;
        this.animalService = animalService;
    }

    @GetMapping
    public String showShelterList(Model model) {
        List<SheltersEntity> shelters = shelterService.getAllShelters();
        model.addAttribute("shelters", shelters);
        return "shelter-list";
    }

    @GetMapping("/add")
    public String showAddShelterForm(Model model) {
        model.addAttribute("shelter", new AddShelterViewModel());
        return "shelter-add";
    }

    @PostMapping("/add")
    public String addShelter(@ModelAttribute("shelter") ShelterAddBindingModel shelterAddBindingModel) {
        return shelterService.addShelter(shelterAddBindingModel);
    }

    @GetMapping("/{id}/edit")
    public String showUpdateShelterForm(@PathVariable("id") long id, Model model) {
        AddShelterViewModel existingShelter = shelterService.getShelterById(id);
        model.addAttribute("shelter", existingShelter);
        model.addAttribute("shelterId", id);
        return "shelter-edit";
    }


    @PostMapping("/{id}/edit")
    public String updateShelter(@PathVariable("id") long id,
                                @ModelAttribute("shelter") ShelterAddBindingModel shelterViewModel) {
        return shelterService.updateShelter(id, shelterViewModel);
    }

    @GetMapping("/list")
    public String showShelters(Model model) {
        List<SheltersEntity> shelters = shelterService.getAllShelters();
        model.addAttribute("shelters", shelters);
        return "shelters";
    }

    @GetMapping("/{id}/animals")
    public String showShelterAnimals(@PathVariable("id") long id, Model model) {
        List<AnimalsEntity> animals = animalService.getAnimalsByShelterId(id);
        model.addAttribute("animals", animals);
        return "shelter-animals";
    }


}
