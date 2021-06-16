package fr.orionbs.SafetyNet.controller;

import fr.orionbs.SafetyNet.model.Firestation;
import fr.orionbs.SafetyNet.service.FirestationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class FirestationController {

    @Autowired
    private FirestationService firestationService;

    @GetMapping(path = "/firestations")
    public List<Firestation> getFirestations() {
        log.info("Firestation GET : All");
        return firestationService.findAll();
    }

    @PostMapping(path = "/firestation")
    public Firestation addFirestation(@RequestBody Firestation firestation) {
        log.info("Firestation POST : Firestation: "+firestation);
        return firestationService.addFirestation(firestation);
    }

    @PutMapping(path = "/firestation")
    public Firestation updateFirestation(@RequestParam String address,
                                         @RequestBody Firestation firestation) {
        log.info("Firestation PUT : Address: "+address+" Firestation: "+firestation);
        return firestationService.updateFirestation(address,firestation);
    }
    @DeleteMapping(path = "/firestation")
    public ResponseEntity<Void> deleteFirestationByAddress(@RequestParam String address) {
        try {
            log.info("Firestation DELETE : Address: "+address);
            firestationService.deleteFirestationByAddress(address);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            log.error("Firestation DELETE : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
