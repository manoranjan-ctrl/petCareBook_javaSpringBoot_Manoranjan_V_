package com.PetCareBook.system.repository;

import com.PetCareBook.system.model.VaccineType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineTypeRepository extends JpaRepository<VaccineType,Long> {
}
