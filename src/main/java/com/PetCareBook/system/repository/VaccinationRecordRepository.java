package com.PetCareBook.system.repository;

import com.PetCareBook.system.model.VaccinationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationRecordRepository extends JpaRepository<VaccinationRecord,Long> {
}
