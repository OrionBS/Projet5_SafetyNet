package fr.orionbs.SafetyNet.controller;

import fr.orionbs.SafetyNet.model.MedicalRecord;
import fr.orionbs.SafetyNet.service.MedicalRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping(path = "/medicalRecords")
    public List<MedicalRecord> getMedicalRecords() {
        log.info("MedicalRecord GET : All");
        return medicalRecordService.findAll();
    }

    @PostMapping(path = "/medicalRecord")
    public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        log.info("MedicalRecord POST : MedicalRecord: "+medicalRecord);
        return medicalRecordService.addMedicalRecord(medicalRecord);
    }
    @PutMapping(path = "/medicalRecord")
    public MedicalRecord updateMedicalRecord(@RequestParam String firstName,
                                             @RequestParam String lastName,
                                             @RequestBody MedicalRecord medicalRecord) {
        log.info("MedicalRecord PUT : Firstname: "+firstName+" Lastname: "+lastName+" MedicalRecord: "+medicalRecord);
        return medicalRecordService.updateMedicalRecord(firstName,lastName,medicalRecord);
    }
    @DeleteMapping(path = "/medicalRecord")
    public ResponseEntity<Void> deleteMedicalRecord(@RequestParam String firstName,
                                                    @RequestParam String lastName) {
        try {
            log.info("MedicalRecord DELETE : Firstname: "+firstName+" Lastname: "+lastName);
            medicalRecordService.deleteMedicalRecord(firstName,lastName);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            log.error("MedicalRecord DELETE : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
