package com.PetCareBook.system.controller;

import com.PetCareBook.system.model.Pet;
import com.PetCareBook.system.repository.PetRepository;
import com.PetCareBook.system.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetController {
    @Autowired
    PetService petService;

    @PostMapping("/addPet")
    Pet addPet(@RequestBody Pet pet){
        return petService.addPet(pet);
    }
}
