package com.PetCareBook.system.repository;

import com.PetCareBook.system.model.VaccinationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VaccinationRecordRepository extends JpaRepository<VaccinationRecord,Long> {
    List<VaccinationRecord> findByNextDueDateBetween(
            LocalDate startDate,
            LocalDate endDate);
}
