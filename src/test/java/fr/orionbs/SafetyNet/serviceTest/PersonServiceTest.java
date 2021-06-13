package fr.orionbs.SafetyNet.serviceTest;

import fr.orionbs.SafetyNet.model.MedicalRecord;
import fr.orionbs.SafetyNet.model.Person;
import fr.orionbs.SafetyNet.repository.MedicalRecordRepository;
import fr.orionbs.SafetyNet.repository.PersonRepository;
import fr.orionbs.SafetyNet.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonServiceImpl personService;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;
    @Mock
    private PersonRepository personRepository;

    @Test
    public void testAddMedicalRecord() {
        //GIVEN
        Person person = new Person(null, "test", "Test", "testAddress", "testCity", 54000, "testPhone", "mailTest");
        when(personRepository.save(any(Person.class))).thenReturn(person);

        //WHEN
        personService.addPerson(person);

        //THEN
        verify(personRepository, Mockito.times(1)).save(any(Person.class));
    }

    @Test
    public void testUpdatePerson() {
        //GIVEN
        Person person = Person.builder().firstName("test").lastName("Test").address("testAddress").city("testCity").zip(54000).phone("testPhone").email("testEmail").build();
        when(personRepository.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(person);
        when(personRepository.save(any(Person.class))).thenReturn(person);

        //WHEN
        personService.updatePerson(anyString(), anyString(), person);

        //THEN
        verify(personRepository, Mockito.times(1)).findByFirstNameAndLastName(anyString(), anyString());
        verify(personRepository, Mockito.times(1)).save(any(Person.class));
    }

    @Test
    public void testFindByFirstNameAndLastName() {
        //GIVEN
        Person person = Person.builder().firstName("test").lastName("Test").address("testAddress").city("testCity").zip(54000).phone("testPhone").email("testEmail").build();
        when(personRepository.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(person);

        //WHEN
        personService.findByFirstNameAndLastName(anyString(), anyString());

        //THEN
        verify(personRepository, Mockito.times(1)).findByFirstNameAndLastName(anyString(), anyString());
    }

    @Test
    public void testSave() {
        //GIVEN
        List<Person> personList = Arrays.asList(Person.builder().firstName("test").lastName("Test").address("testAddress").city("testCity").zip(54000).phone("testPhone").email("testEmail").build());
        when(personRepository.saveAll(anyList())).thenReturn(personList);

        //WHEN
        personService.save(anyList());

        //THEN
        verify(personRepository, Mockito.times(1)).saveAll(anyList());
    }

    @Test
    public void testFindAll() {
        //GIVEN
        List<Person> personList = Arrays.asList(Person.builder().firstName("test").lastName("Test").address("testAddress").city("testCity").zip(54000).phone("testPhone").email("testEmail").build());
        when(personRepository.findAll()).thenReturn(personList);

        //WHEN
        personService.findAll();

        //THEN
        verify(personRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testGetEmailFilterByTown() {
        //GIVEN
        List<Person> personList = Arrays.asList(Person.builder().firstName("test").lastName("Test").address("testAddress").city("testCity").zip(54000).phone("testPhone").email("testEmail").build());
        when(personRepository.findByCity(anyString())).thenReturn(personList);

        //WHEN
        personService.getEmailFilterByTown(anyString());

        //THEN
        verify(personRepository, Mockito.times(1)).findByCity(anyString());
    }

    @Test
    public void testGetPersonInfo() {
        //GIVEN
        List<Person> personList = Arrays.asList(Person.builder().firstName("test").lastName("Test").address("testAddress").city("testCity").zip(54000).phone("testPhone").email("testEmail").build());
        MedicalRecord medicalRecord = MedicalRecord.builder().firstName("test").lastName("Test").birthdate("03/02/2000").build();
        when(personRepository.findByLastName(anyString())).thenReturn(personList);
        when(medicalRecordRepository.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(medicalRecord);

        //WHEN
        personService.getPersonInfo(medicalRecord.getFirstName(), medicalRecord.getLastName());

        //THEN
        verify(personRepository, Mockito.times(1)).findByLastName(anyString());
        verify(medicalRecordRepository, Mockito.times(1)).findByFirstNameAndLastName(anyString(), anyString());
    }

    @Test
    public void testGetChildByAddress() {
        //GIVEN
        List<Person> personList = Arrays.asList(Person.builder().firstName("test").lastName("Test").address("testAddress").city("testCity").zip(54000).phone("testPhone").email("testEmail").build());
        MedicalRecord medicalRecord = MedicalRecord.builder().firstName("test").lastName("Test").birthdate("03/02/2000").build();
        List<Person> otherPersonsList = Arrays.asList(Person.builder().firstName("test").lastName("Test").address("testAddress").city("testCity").zip(54000).phone("testPhone").email("testEmail").build());
        when(personRepository.findByAddress(anyString())).thenReturn(personList);
        when(medicalRecordRepository.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(medicalRecord);
        when(personRepository.findByAddress(anyString())).thenReturn(otherPersonsList);

        //WHEN
        personService.getChildByAddress(anyString());

        //THEN
        verify(personRepository, Mockito.times(1)).findByAddress(anyString());
        verify(medicalRecordRepository, Mockito.times(1)).findByFirstNameAndLastName(anyString(), anyString());
        verify(personRepository, Mockito.times(1)).findByAddress(anyString());

    }

    @Test
    public void testGetPerson() {
        //GIVEN
        Person person = Person.builder().firstName("test").lastName("Test").address("testAddress").city("testCity").zip(54000).phone("testPhone").email("testEmail").build();
        when(personRepository.findByFirstNameAndLastName(anyString(),anyString())).thenReturn(person);

        //WHEN
        personService.getPerson(anyString(),anyString());

        //THEN
        verify(personRepository,Mockito.times(1)).findByFirstNameAndLastName(anyString(),anyString());
    }
}