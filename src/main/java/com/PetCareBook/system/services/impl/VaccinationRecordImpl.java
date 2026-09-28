package com.PetCareBook.system.services.impl;

import com.PetCareBook.system.model.VaccinationRecord;
import com.PetCareBook.system.repository.VaccinationRecordRepository;
import com.PetCareBook.system.services.VaccinationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VaccinationRecordImpl implements VaccinationRecordService {
    @Autowired
    VaccinationRecordRepository vaccinationRecordRepository;


    @Override
    public VaccinationRecord addVaccinationRecord(VaccinationRecord record) {

        int interval = record.getVaccineType().getIntervalDays();

        LocalDate nextDueDate = record.getDate().plusDays(interval);

        record.setNextDueDate(nextDueDate);

        return vaccinationRecordRepository.save(record);
    }

    @Override
    public List<VaccinationRecord> getAllVaccinationRecords() {
        return List.of();
    }
    @Override
    public List<VaccinationRecord> getUpcomingVaccinations() {

        LocalDate today = LocalDate.now();

        LocalDate next7Days = today.plusDays(7);

        return vaccinationRecordRepository
                .findByNextDueDateBetween(today, next7Days);
    }

    @Override
    public VaccinationRecord getVaccinationRecordById(Long id) {
        return vaccinationRecordRepository.findById(id).orElse(null);
    }
    @Override
    public VaccinationRecord updateVaccinationRecord(
            Long id,
            VaccinationRecord record) {

        VaccinationRecord existingRecord =
                vaccinationRecordRepository
                        .findById(id)
                        .orElse(null);

        if (existingRecord != null) {

            existingRecord.setDate(record.getDate());

            existingRecord.setPet(record.getPet());

            existingRecord.setVaccineType(
                    record.getVaccineType());


            int interval =
                    record.getVaccineType().getIntervalDays();

            LocalDate nextDueDate =
                    record.getDate().plusDays(interval);

            existingRecord.setNextDueDate(nextDueDate);


            return vaccinationRecordRepository
                    .save(existingRecord);
        }

        return null;
    }


    @Override
    public void deleteVaccinationRecord(Long id) {

        vaccinationRecordRepository.deleteById(id);
    }

}
