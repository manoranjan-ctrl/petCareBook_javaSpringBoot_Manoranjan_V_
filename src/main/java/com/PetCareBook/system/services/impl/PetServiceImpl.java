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

        if (pet.getName() == null || pet.getName().trim().isEmpty()) {
            throw new RuntimeException("Pet name cannot be empty");
        }

        if (pet.getSpecies() == null || pet.getSpecies().trim().isEmpty()) {
            throw new RuntimeException("Pet species cannot be empty");
        }

        if (pet.getBreed() == null || pet.getBreed().trim().isEmpty()) {
            throw new RuntimeException("Pet breed cannot be empty");
        }

        if (pet.getDateOfBirth() == null) {
            throw new RuntimeException("Pet date of birth cannot be empty");
        }

        if (pet.getOwner() == null) {
            throw new RuntimeException("Pet owner is required");
        }

        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public Pet updatePet(Long id, Pet pet) {

        Pet existingPet = petRepository.findById(id).orElse(null);

        if (existingPet != null) {

            if (pet.getName() == null || pet.getName().trim().isEmpty()) {
                throw new RuntimeException("Pet name cannot be empty");
            }

            if (pet.getSpecies() == null || pet.getSpecies().trim().isEmpty()) {
                throw new RuntimeException("Pet species cannot be empty");
            }

            if (pet.getBreed() == null || pet.getBreed().trim().isEmpty()) {
                throw new RuntimeException("Pet breed cannot be empty");
            }

            if (pet.getDateOfBirth() == null) {
                throw new RuntimeException("Pet date of birth cannot be empty");
            }

            if (pet.getOwner() == null) {
                throw new RuntimeException("Pet owner is required");
            }

            existingPet.setName(pet.getName());
            existingPet.setSpecies(pet.getSpecies());
            existingPet.setBreed(pet.getBreed());
            existingPet.setDateOfBirth(pet.getDateOfBirth());
            existingPet.setOwner(pet.getOwner());

            return petRepository.save(existingPet);
        }

        return null;
    }

    @Override
    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
}