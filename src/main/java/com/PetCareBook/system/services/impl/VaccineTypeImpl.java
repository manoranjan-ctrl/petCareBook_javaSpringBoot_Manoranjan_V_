package com.PetCareBook.system.services.impl;

import com.PetCareBook.system.model.VaccineType;
import com.PetCareBook.system.repository.VaccineTypeRepository;
import com.PetCareBook.system.services.VaccineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineTypeImpl implements VaccineTypeService {
    @Autowired
    VaccineTypeRepository vaccineTypeRepository;

    @Override
    public VaccineType addVaccineType(VaccineType vaccineType) {
        return vaccineTypeRepository.save(vaccineType);
    }

    @Override
    public List<VaccineType> getAllVaccineTypes() {
        return List.of();
    }
}
