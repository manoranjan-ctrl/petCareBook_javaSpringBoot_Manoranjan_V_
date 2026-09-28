package com.PetCareBook.system.controller;

import com.PetCareBook.system.model.Pet;
import com.PetCareBook.system.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetController {
    @Autowired
    PetService petService;

    @PostMapping("/addPet")
    Pet addPet(@RequestBody Pet pet){
        return petService.addPet(pet);
    }
    @GetMapping("/pets")
    List<Pet> getAllPets() {
        return petService.getAllPets();
    }
    @GetMapping("/pets/{id}")
    Pet getPetById(@PathVariable Long id) {
        return petService.getPetById(id);
    }
    @PutMapping("/pets/{id}")
    Pet updatePet(
            @PathVariable Long id,
            @RequestBody Pet pet) {

        return petService.updatePet(id, pet);
    }

    @DeleteMapping("/pets/{id}")
    String deletePet(@PathVariable Long id) {

        petService.deletePet(id);

        return "Pet deleted successfully";
    }
}
