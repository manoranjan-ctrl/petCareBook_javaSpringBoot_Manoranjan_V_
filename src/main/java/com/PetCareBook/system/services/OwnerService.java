package com.PetCareBook.system.services;

import com.PetCareBook.system.model.Owner;

import java.util.List;

public interface OwnerService {
    Owner addOwner(Owner owner);
    List<Owner> getAllOwners();
    Owner getOwnerById(Long id);
    Owner updateOwner(Long id, Owner owner);
    void deleteOwner(Long id);
}
