package fr.orionbs.SafetyNet.controller;

import fr.orionbs.SafetyNet.model.CommunityEmail;
import fr.orionbs.SafetyNet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommunityEmailController {

    @Autowired
    private PersonService personService;

    //Retourne les emails des habitants de la ville.
    @GetMapping(path = "/communityEmail")
    public List<CommunityEmail> getEmailsFilterByTown(@RequestParam String city) {
        return personService.getEmailFilterByTown(city);
    }
}
