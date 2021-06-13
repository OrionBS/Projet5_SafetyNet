package fr.orionbs.SafetyNet.controller;

import fr.orionbs.SafetyNet.model.FirestationCoverage;
import fr.orionbs.SafetyNet.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirestationCoverageController {

    @Autowired
    FirestationService firestationService;

    //Retourne une liste des peronnes couverte pas le numéro de station.
    @GetMapping(path = "/firestation")
    public FirestationCoverage getPersonsCoverageByNumber(@RequestParam int station) {
        return firestationService.getPersonsByCoverage(station);
    }
}
