package fr.orionbs.SafetyNet.controller;

import fr.orionbs.SafetyNet.model.FirestationCoverage;
import fr.orionbs.SafetyNet.service.FirestationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FirestationCoverageController {

    @Autowired
    FirestationService firestationService;

    //Retourne une liste des peronnes couverte pas le numéro de station.
    @GetMapping(path = "/firestation")
    public FirestationCoverage getPersonsCoverageByNumber(@RequestParam int station) {
        log.info("FirestationCoverage GET : Station: "+station);
        return firestationService.getPersonsByCoverage(station);
    }
}
