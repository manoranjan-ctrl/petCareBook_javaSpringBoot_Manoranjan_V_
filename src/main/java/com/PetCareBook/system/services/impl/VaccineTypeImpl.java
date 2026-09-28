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

    @Override
    public VaccineType getVaccineTypeById(Long id) {
        return vaccineTypeRepository.findById(id).orElse(null);
    }
    @Override
    public VaccineType updateVaccineType(Long id, VaccineType vaccineType) {

        VaccineType existingVaccine =
                vaccineTypeRepository.findById(id).orElse(null);

        if (existingVaccine != null) {

            existingVaccine.setVaccineName(
                    vaccineType.getVaccineName());

            existingVaccine.setIntervalDays(
                    vaccineType.getIntervalDays());

            return vaccineTypeRepository.save(existingVaccine);
        }

        return null;
    }

    @Override
    public void deleteVaccineType(Long id) {
        vaccineTypeRepository.deleteById(id);
    }
}
