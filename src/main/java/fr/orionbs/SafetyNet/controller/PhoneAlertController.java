package fr.orionbs.SafetyNet.controller;

import fr.orionbs.SafetyNet.model.PhoneAlert;
import fr.orionbs.SafetyNet.service.FirestationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class PhoneAlertController {

    @Autowired
    FirestationService firestationService;

    //Retourne une liste des numéros couvert par la station.
    @GetMapping(path = "/phoneAlert")
    public List<PhoneAlert> getPhoneNumberCoveredByStation(@RequestParam int station) {
        log.info("PhoneAlert GET : Station: "+station);
        return firestationService.getPhoneByCoverage(station);
    }
}
