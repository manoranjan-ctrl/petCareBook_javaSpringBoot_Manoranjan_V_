package com.PetCareBook.system.controller;

import com.PetCareBook.system.model.VaccineType;
import com.PetCareBook.system.services.VaccineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VaccineTypeController {
   @Autowired
   VaccineTypeService vaccineTypeService;

   @PostMapping("/addVaccineType")
    VaccineType addVaccineType(@RequestBody VaccineType vaccineType){
       return vaccineTypeService.addVaccineType(vaccineType);
   }
   @GetMapping("/vaccineTypes")
   List<VaccineType> getAllVaccineTypes() {
      return vaccineTypeService.getAllVaccineTypes();
   }
}
