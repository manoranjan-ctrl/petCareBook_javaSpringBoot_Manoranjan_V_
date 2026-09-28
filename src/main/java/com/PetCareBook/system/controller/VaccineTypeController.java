package com.PetCareBook.system.controller;

import com.PetCareBook.system.model.VaccineType;
import com.PetCareBook.system.services.VaccineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
   @GetMapping("/vaccineTypes/{id}")
   VaccineType getVaccineTypeById(@PathVariable Long id) {
      return vaccineTypeService.getVaccineTypeById(id);
   }
   @PutMapping("/vaccineTypes/{id}")
   VaccineType updateVaccineType(
           @PathVariable Long id,
           @RequestBody VaccineType vaccineType) {

      return vaccineTypeService.updateVaccineType(id, vaccineType);
   }

   @DeleteMapping("/vaccineTypes/{id}")
   String deleteVaccineType(@PathVariable Long id) {

      vaccineTypeService.deleteVaccineType(id);

      return "Vaccine type deleted successfully";
   }
}
