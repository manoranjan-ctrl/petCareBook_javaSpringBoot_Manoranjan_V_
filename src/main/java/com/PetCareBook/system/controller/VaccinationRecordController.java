package com.PetCareBook.system.controller;

import com.PetCareBook.system.model.VaccinationRecord;
import com.PetCareBook.system.services.VaccinationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VaccinationRecordController {

    @Autowired
    VaccinationRecordService vaccinationRecordService;

    @PostMapping("/addVaccinationRecord")
    VaccinationRecord addVaccinationRecord(@RequestBody VaccinationRecord record) {
        return vaccinationRecordService.addVaccinationRecord(record);
    }
}