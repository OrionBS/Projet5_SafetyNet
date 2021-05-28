package fr.orionbs.SafetyNet.controller;

import fr.orionbs.SafetyNet.model.Person;
import fr.orionbs.SafetyNet.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(path = "/persons")
    public List<Person> getPersons(){
        return personService.findAll();
    }

    @PostMapping(path = "/person")
    public Person addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }

    @PutMapping(path = "/person")
    public Person updatePerson(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestBody Person person) {
        return personService.updatePerson(firstName, lastName,person);
    }
    @DeleteMapping(path = "/person")
    public ResponseEntity<Void> deletePerson(@RequestParam String firstName,
                                               @RequestParam String lastName) {
        try {
            personService.deletePerson(firstName, lastName);
            return ResponseEntity.accepted().build();
        } catch (ConfigDataResourceNotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
