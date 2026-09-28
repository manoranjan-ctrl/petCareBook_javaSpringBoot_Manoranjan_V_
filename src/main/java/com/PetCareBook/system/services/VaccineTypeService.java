package com.PetCareBook.system.services;

import com.PetCareBook.system.model.VaccineType;

import java.util.List;

public interface VaccineTypeService {

    VaccineType addVaccineType(VaccineType vaccineType);
    List<VaccineType> getAllVaccineTypes();
    VaccineType getVaccineTypeById(Long id);
}