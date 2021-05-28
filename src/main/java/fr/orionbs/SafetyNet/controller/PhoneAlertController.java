package fr.orionbs.SafetyNet.controller;

import fr.orionbs.SafetyNet.model.PhoneAlert;
import fr.orionbs.SafetyNet.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PhoneAlertController {

    @Autowired
    FirestationService firestationService;

    //Retourne une liste des numéros couvert par la station.
    @GetMapping(path = "/phoneAlert")
    public List<PhoneAlert> getPhoneNumberCoveredByStation(@RequestParam int station) {
        return firestationService.getPhoneByCoverage(station);
    }
}
