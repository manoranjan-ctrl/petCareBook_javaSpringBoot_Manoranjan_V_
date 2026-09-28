package com.PetCareBook.system.controller;

import com.PetCareBook.system.model.VaccinationRecord;
import com.PetCareBook.system.services.VaccinationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VaccinationRecordController {

    @Autowired
    VaccinationRecordService vaccinationRecordService;

    @PostMapping("/addVaccinationRecord")
    VaccinationRecord addVaccinationRecord(@RequestBody VaccinationRecord record) {
        return vaccinationRecordService.addVaccinationRecord(record);
    }
    @GetMapping("/vaccinationRecords")
    List<VaccinationRecord> getAllVaccinationRecords() {
        return vaccinationRecordService.getAllVaccinationRecords();
    }


}