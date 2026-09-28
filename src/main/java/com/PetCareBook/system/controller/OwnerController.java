package com.PetCareBook.system.controller;

import com.PetCareBook.system.model.Owner;
import com.PetCareBook.system.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OwnerController {
    @Autowired
    OwnerService ownerService;

    @PostMapping("/addOwner")
    Owner addOwner(@RequestBody Owner owner){
      return  ownerService.addOwner(owner);
    }

    @GetMapping("/owners")
    List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("/owners/{id}")
    Owner getOwnerById(@PathVariable Long id) {
        return ownerService.getOwnerById(id);
    }
    @PutMapping("/owners/{id}")
    Owner updateOwner(
            @PathVariable Long id,
            @RequestBody Owner owner) {

        return ownerService.updateOwner(id, owner);
    }

    @DeleteMapping("/owners/{id}")
    String deleteOwner(@PathVariable Long id) {

        ownerService.deleteOwner(id);

        return "Owner deleted successfully";
    }
}
