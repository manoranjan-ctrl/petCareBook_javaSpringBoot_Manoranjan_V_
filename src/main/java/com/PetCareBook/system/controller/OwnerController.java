package com.PetCareBook.system.controller;

import com.PetCareBook.system.model.Owner;
import com.PetCareBook.system.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OwnerController {
    @Autowired
    OwnerService ownerService;

    @PostMapping("/addOwner")
    Owner addOwner(@RequestBody Owner owner){
      return  ownerService.addOwner(owner);
    }
}
