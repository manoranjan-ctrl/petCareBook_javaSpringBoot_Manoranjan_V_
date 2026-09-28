package com.PetCareBook.system.services.impl;

import com.PetCareBook.system.model.Pet;
import com.PetCareBook.system.repository.PetRepository;
import com.PetCareBook.system.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    @Autowired
    PetRepository petRepository;

    @Override
    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

}
