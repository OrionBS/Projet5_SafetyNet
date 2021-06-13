package fr.orionbs.SafetyNet.controller;

import fr.orionbs.SafetyNet.model.PersonInfo;
import fr.orionbs.SafetyNet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonInfoController {

    @Autowired
    PersonService personService;

    //Retourne informations sur une personne.
    @GetMapping(path = "/personInfo")
    public List<PersonInfo> getPersonInfo(@RequestParam String firstName,
                                          @RequestParam String lastName) {
        return personService.getPersonInfo(firstName, lastName);
    }
}
