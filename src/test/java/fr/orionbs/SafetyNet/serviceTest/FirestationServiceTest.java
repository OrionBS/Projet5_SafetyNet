package fr.orionbs.SafetyNet.serviceTest;

import fr.orionbs.SafetyNet.model.Firestation;
import fr.orionbs.SafetyNet.model.MedicalRecord;
import fr.orionbs.SafetyNet.model.Person;
import fr.orionbs.SafetyNet.model.PhoneAlert;
import fr.orionbs.SafetyNet.repository.FirestationRepository;
import fr.orionbs.SafetyNet.repository.MedicalRecordRepository;
import fr.orionbs.SafetyNet.repository.PersonRepository;
import fr.orionbs.SafetyNet.service.impl.FirestationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FirestationServiceTest {

    @InjectMocks
    private FirestationServiceImpl firestationService;

    @Mock
    private FirestationRepository firestationRepository;
    @Mock
    private MedicalRecordRepository medicalRecordRepository;
    @Mock
    private PersonRepository personRepository;

    @Test
    public void testAddFirestation(){
        //GIVEN
        Firestation firestation = new Firestation(1,"testAddress", 1);
        when(firestationRepository.save(firestation)).thenReturn(firestation);

        //WHEN
        firestationService.addFirestation(firestation);

        //THEN
        verify(firestationRepository, Mockito.times(1)).save(any(Firestation.class));
    }

    @Test
    public void testUpdateFirestation(){
        //GIVEN
        Firestation firestation = new Firestation(null,"1509 Culver St",1);
        Firestation newFirestation = new Firestation(null,"1509 Culver St",3);
        when(firestationRepository.findByAddress("1509 Culver St")).thenReturn(firestation);
        when(firestationRepository.save(newFirestation)).thenReturn(newFirestation);

        //WHEN
        firestationService.updateFirestation("1509 Culver St",newFirestation);

        //THEN
        verify(firestationRepository, Mockito.times(1)).findByAddress(any(String.class));
        verify(firestationRepository, Mockito.times(1)).save(any(Firestation.class));

    }

    @Test
    public void testSave() {
        //GIVEN
        List<Firestation> firestationList = new ArrayList<>();
        when(firestationRepository.saveAll(anyList())).thenReturn(firestationList);

        //WHEN
        firestationRepository.saveAll(anyList());

        //THEN
        verify(firestationRepository,Mockito.times(1)).saveAll(firestationList);
    }

    @Test
    public void testFindAll(){
        //GIVEN
        List<Firestation> firestationList = new ArrayList<>();
        when(firestationRepository.findAll()).thenReturn(firestationList);

        //WHEN
        firestationRepository.findAll();

        //THEN
        verify(firestationRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testGetPersonsByCoverage() {
        //GIVEN
        List<Firestation> firestationList = Arrays.asList(Firestation.builder().address("testAddress").station(1).build());
        List<Person> personList = Arrays.asList(Person.builder().firstName("test").lastName("Test").address("testAddress").city("testCity").phone("1").build());
        MedicalRecord medicalRecord = MedicalRecord.builder().firstName("test").lastName("Test").birthdate("03/02/2000").build();
        when(firestationRepository.findByStation(1)).thenReturn(firestationList);
        when(personRepository.findByAddress(any(String.class))).thenReturn(personList);
        when(medicalRecordRepository.findByFirstNameAndLastName(any(String.class),any(String.class))).thenReturn(medicalRecord);

        //WHEN
        firestationService.getPersonsByCoverage(1);

        //THEN
        verify(firestationRepository, Mockito.times(1)).findByStation(1);
        verify(personRepository, Mockito.times(1)).findByAddress(any(String.class));
        verify(medicalRecordRepository, Mockito.times(1)).findByFirstNameAndLastName(any(String.class),any(String.class));
    }

    @Test
    public void testGetPhoneByCoverage() {
        //GIVEN
        List<PhoneAlert> phoneAlertList = Arrays.asList(PhoneAlert.builder().phone("testPhone").build());
        List<Firestation> firestationList = Arrays.asList(Firestation.builder().address("testAddress").station(1).build());
        List<Person> personList = Arrays.asList(Person.builder().firstName("test").lastName("Test").address("testAddress").city("testCity").phone("1").build());
        when(firestationRepository.findByStation(any(Integer.class))).thenReturn(firestationList);
        when(personRepository.findByAddress(any(String.class))).thenReturn(personList);

        //WHEN
        firestationService.getPhoneByCoverage(1);

        //THEN
        verify(firestationRepository,Mockito.times(1)).findByStation(any(Integer.class));
        verify(personRepository,Mockito.times(1)).findByAddress(any(String.class));
    }

    @Test
    public void testGetFirestationAndPersonsMedicalRecord() {
        //GIVEN
        Firestation firestation = Firestation.builder().address("testAddress").station(1).build();
        List<Person> personList = Arrays.asList(Person.builder().firstName("test").lastName("Test").address("testAddress").city("testCity").phone("1").build());
        MedicalRecord medicalRecord = MedicalRecord.builder().firstName("test").lastName("Test").birthdate("03/02/2000").build();
        when(firestationRepository.findByAddress(any(String.class))).thenReturn(firestation);
        when(personRepository.findByAddress(any(String.class))).thenReturn(personList);
        when(medicalRecordRepository.findByFirstNameAndLastName(any(String.class),any(String.class))).thenReturn(medicalRecord);

        //WHEN
        firestationService.getFirestationAndPersonsMedicalRecord(firestation.getAddress());

        //THEN
        verify(firestationRepository, Mockito.times(1)).findByAddress(any(String.class));
        verify(personRepository, Mockito.times(1)).findByAddress(any(String.class));
        verify(medicalRecordRepository, Mockito.times(1)).findByFirstNameAndLastName(any(String.class),any(String.class));
    }

    @Test
    public void testGetFirestation() {
        //GIVEN
        Firestation firestation = Firestation.builder().address("testAddress").station(1).build();
        when(firestationRepository.findByAddressAndStation(anyString(),anyInt())).thenReturn(firestation);

        //WHEN
        firestationService.getFirestation(firestation.getAddress(),firestation.getStation());

        //THEN
        verify(firestationRepository,Mockito.times(1)).findByAddressAndStation(anyString(),anyInt());
    }

    @Test
    public void testGetFloodStation() {
        //GIVEN
        int station = 1;
        List<Firestation> firestationList = Arrays.asList(Firestation.builder().address("testAddress").station(1).build());
        List<Person> personList = Arrays.asList(Person.builder().firstName("test").lastName("Test").address("testAddress").city("testCity").phone("1").build());
        MedicalRecord medicalRecord = MedicalRecord.builder().firstName("test").lastName("Test").birthdate("03/02/2000").build();
        when(firestationRepository.findByStation(anyInt())).thenReturn(firestationList);
        when(personRepository.findByAddress(anyString())).thenReturn(personList);
        when(medicalRecordRepository.findByFirstNameAndLastName(anyString(),anyString())).thenReturn(medicalRecord);

        //WHEN
        firestationService.getFloodStation(Arrays.asList(1));

        //THEN
        verify(firestationRepository,Mockito.times(1)).findByStation(anyInt());
        verify(personRepository,Mockito.times(1)).findByAddress(anyString());
        verify(medicalRecordRepository,Mockito.times(1)).findByFirstNameAndLastName(anyString(),anyString());
    }
}
