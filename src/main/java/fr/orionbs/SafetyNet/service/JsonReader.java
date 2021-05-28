package fr.orionbs.SafetyNet.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.orionbs.SafetyNet.model.Data;
import fr.orionbs.SafetyNet.model.Firestation;
import fr.orionbs.SafetyNet.model.MedicalRecord;
import fr.orionbs.SafetyNet.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@Transactional
public class JsonReader {

    @Autowired
    private PersonService personService;
    @Autowired
    private FirestationService firestationService;
    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostConstruct
    public void loadingData() {

        Data data = new Data();

        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("src/main/resources/static/data.json");

        try {
            data = objectMapper.readValue(file, Data.class);
        } catch (IOException e) {
            log.error("Une  erreur est survenue lors de la serialisation en Objet.",e);
        }

        List<Person> dataPersons = data.getPersons();
        dataPersons.forEach(dataPerson -> {
            Boolean isEmpty = personService.getPerson(dataPerson.getFirstName(), dataPerson.getLastName());
            if (isEmpty) {
                personService.addPerson(dataPerson);
            }
        });

        List<Firestation> dataFirestations = data.getFirestations();
        dataFirestations.forEach(dataFirestation -> {
            Boolean isEmpty = firestationService.getFirestation(dataFirestation.getAddress(), dataFirestation.getStation());
            if (isEmpty) {
                firestationService.addFirestation(dataFirestation);
            }
        });
        List<MedicalRecord> dataMedicalRecords = data.getMedicalrecords();
        dataMedicalRecords.forEach(dataMedicalRecord -> {
            Boolean isEmpty = medicalRecordService.getMedicalRecord(dataMedicalRecord.getFirstName(), dataMedicalRecord.getLastName());
            if (isEmpty) {
                medicalRecordService.addMedicalRecord(dataMedicalRecord);
            }
        });
    }
}
