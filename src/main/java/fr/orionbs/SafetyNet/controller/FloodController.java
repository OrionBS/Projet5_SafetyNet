package fr.orionbs.SafetyNet.controller;

import fr.orionbs.SafetyNet.model.FloodStation;
import fr.orionbs.SafetyNet.service.FirestationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class FloodController {

    @Autowired
    FirestationService firestationService;

    @GetMapping(path = "/flood/stations")
    List<FloodStation> getFirestationCoverage(@RequestParam List<Integer> stations) {
        log.info("Flood GET : Stations: "+stations);
        return firestationService.getFloodStation(stations);
    }
}
