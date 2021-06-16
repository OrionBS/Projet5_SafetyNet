package fr.orionbs.SafetyNet.controller;

import fr.orionbs.SafetyNet.model.Fire;
import fr.orionbs.SafetyNet.service.FirestationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class FireController {

    @Autowired
    FirestationService firestationService;

    //Retourne une liste des habitants vivant à l'adresse avec la caserne qui la dessert.*
    @GetMapping(path = "/fire")
    public List<Fire> getPersonsAndFirestationAtAddress(@RequestParam String address) {
        log.info("Fire GET : Address: "+address);
        return firestationService.getFirestationAndPersonsMedicalRecord(address);
    }
}
