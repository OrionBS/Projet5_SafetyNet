package fr.orionbs.SafetyNet.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.orionbs.SafetyNet.model.Data;
import fr.orionbs.SafetyNet.model.Firestation;
import fr.orionbs.SafetyNet.model.MedicalRecord;
import fr.orionbs.SafetyNet.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
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
    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void loadingData() throws IOException {

        Data data = new Data();

        ObjectMapper objectMapper = new ObjectMapper();

        InputStream res = Data.class.getResourceAsStream("/static/data.json");

        try {
            data = objectMapper.readValue(res, Data.class);
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
