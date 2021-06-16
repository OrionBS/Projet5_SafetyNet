package fr.orionbs.SafetyNet.controller;

import fr.orionbs.SafetyNet.model.CommunityEmail;
import fr.orionbs.SafetyNet.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CommunityEmailController {

    @Autowired
    private PersonService personService;

    //Retourne les emails des habitants de la ville.
    @GetMapping(path = "/communityEmail")
    public List<CommunityEmail> getEmailsFilterByTown(@RequestParam String city) {
        log.info("CommunityEmail GET : City: "+city);
        return personService.getEmailFilterByTown(city);
    }
}
