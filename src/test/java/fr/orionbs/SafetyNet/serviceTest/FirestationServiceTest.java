package fr.orionbs.SafetyNet.serviceTest;

import fr.orionbs.SafetyNet.model.Firestation;
import fr.orionbs.SafetyNet.model.MedicalRecord;
import fr.orionbs.SafetyNet.model.Person;
import fr.orionbs.SafetyNet.repository.FirestationRepository;
import fr.orionbs.SafetyNet.repository.MedicalRecordRepository;
import fr.orionbs.SafetyNet.repository.PersonRepository;
import fr.orionbs.SafetyNet.service.FirestationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FirestationServiceTest {

    @Autowired
    private FirestationService firestationService;

    @MockBean
    private static FirestationRepository firestationRepository;
    @MockBean
    private static MedicalRecordRepository medicalRecordRepository;
    @MockBean
    private static PersonRepository personRepository;

    @Test
    public void testAddFirestation(){
        //GIVEN
        Firestation firestation = new Firestation(1,"testAddress", 1);

        //WHEN
        when(firestationRepository.save(firestation)).thenReturn(firestation);

        firestationService.addFirestation(firestation);

        //THEN
        verify(firestationRepository, Mockito.times(14)).save(any(Firestation.class));
    }

    @Test
    public void testUpdateFirestation(){
        //GIVEN
        Firestation firestation = new Firestation(null,"1509 Culver St",1);
        Firestation newFirestation = new Firestation(null,"1509 Culver St",3);

        //WHEN
        when(firestationRepository.findByAddress("1509 Culver St")).thenReturn(firestation);
        when(firestationRepository.save(newFirestation)).thenReturn(newFirestation);

        firestationService.updateFirestation("1509 Culver St",newFirestation);

        //THEN
        verify(firestationRepository, Mockito.times(1)).findByAddress(any(String.class));
        verify(firestationRepository, Mockito.times(14)).save(any(Firestation.class));

    }

    @Test
    public void testFindAll(){
        //GIVEN
        List<Firestation> firestationList = new ArrayList<>();

        //WHEN
        when(firestationRepository.findAll()).thenReturn(firestationList);
        firestationRepository.findAll();

        //THEN
        verify(firestationRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testGetPersonsByCoverage() {
        //GIVEN
        List<Firestation> firestationList = new ArrayList<>();
        List<Person> personList = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord();
        //WHEN
        when(firestationRepository.findByStation(1)).thenReturn(firestationList);
        when(personRepository.findByAddress(any(String.class))).thenReturn(personList);
        when(medicalRecordRepository.findByFirstNameAndLastName(any(String.class),any(String.class))).thenReturn(medicalRecord);

        //THEN
        verify(firestationRepository, Mockito.times(1)).findByStation(1);
        verify(personRepository, Mockito.times(1)).findByAddress(any(String.class));
        verify(medicalRecordRepository, Mockito.times(1)).findByFirstNameAndLastName(any(String.class),any(String.class));
    }

}
