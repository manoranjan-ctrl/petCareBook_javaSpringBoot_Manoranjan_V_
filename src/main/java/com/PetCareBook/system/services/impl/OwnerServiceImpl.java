package com.PetCareBook.system.services.impl;

import com.PetCareBook.system.model.Owner;
import com.PetCareBook.system.repository.OwnerRepository;
import com.PetCareBook.system.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository){
        this.ownerRepository=ownerRepository;
    }

    @Override
    public Owner addOwner(Owner owner) {
        return ownerRepository.save(owner);

    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner getOwnerById(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }
    @Override
    public Owner updateOwner(Long id, Owner owner) {

        Owner existingOwner = ownerRepository.findById(id).orElse(null);

        if (existingOwner != null) {

            existingOwner.setName(owner.getName());
            existingOwner.setPhone(owner.getPhone());

            return ownerRepository.save(existingOwner);
        }

        return null;
    }

    @Override
    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }
}
