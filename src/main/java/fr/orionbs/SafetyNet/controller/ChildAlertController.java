package fr.orionbs.SafetyNet.controller;

import fr.orionbs.SafetyNet.model.ChildAlert;
import fr.orionbs.SafetyNet.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
public class ChildAlertController {

    @Autowired
    private PersonService personService;

    //Retourne une liste d'enfants à l'adresse spécifié
    @GetMapping(path = "/childAlert")
    public List<ChildAlert> getChildFilterByAddress(@RequestParam String address) {
        log.info("ChildAlert GET : Address: "+address);
        return personService.getChildByAddress(address);
    }
}
