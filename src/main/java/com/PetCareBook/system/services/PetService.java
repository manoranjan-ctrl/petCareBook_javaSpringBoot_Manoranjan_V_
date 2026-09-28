package com.PetCareBook.system.services;

import com.PetCareBook.system.model.Pet;

import java.util.List;

public interface PetService {
 Pet addPet(Pet pet);
 List<Pet> getAllPets();
 Pet getPetById(Long id);
}
