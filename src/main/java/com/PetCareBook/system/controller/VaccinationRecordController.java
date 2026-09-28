package com.PetCareBook.system.controller;

import com.PetCareBook.system.model.VaccinationRecord;
import com.PetCareBook.system.services.VaccinationRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VaccinationRecordController {

    @Autowired
    VaccinationRecordService vaccinationRecordService;


    @PostMapping("/addVaccinationRecord")
    VaccinationRecord addVaccinationRecord(
            @RequestBody VaccinationRecord record) {

        return vaccinationRecordService
                .addVaccinationRecord(record);
    }


    @GetMapping("/vaccinationRecords")
    List<VaccinationRecord> getAllVaccinationRecords() {

        return vaccinationRecordService
                .getAllVaccinationRecords();
    }


    @GetMapping("/vaccinationRecords/{id}")
    VaccinationRecord getVaccinationRecordById(
            @PathVariable Long id) {

        return vaccinationRecordService
                .getVaccinationRecordById(id);
    }


    @GetMapping("/upcomingVaccinations")
    List<VaccinationRecord> getUpcomingVaccinations() {

        return vaccinationRecordService
                .getUpcomingVaccinations();
    }


    @PutMapping("/vaccinationRecords/{id}")
    VaccinationRecord updateVaccinationRecord(
            @PathVariable Long id,
            @RequestBody VaccinationRecord record) {

        return vaccinationRecordService
                .updateVaccinationRecord(id, record);
    }


    @DeleteMapping("/vaccinationRecords/{id}")
    String deleteVaccinationRecord(
            @PathVariable Long id) {

        vaccinationRecordService
                .deleteVaccinationRecord(id);

        return "Vaccination record deleted successfully";
    }
}