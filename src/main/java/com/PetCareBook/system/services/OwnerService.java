package com.PetCareBook.system.services;

import com.PetCareBook.system.model.Owner;

import java.util.List;

public interface OwnerService {
    Owner addOwner(Owner owner);
    List<Owner> getAllOwners();
    Owner getOwnerById(Long id);
}
