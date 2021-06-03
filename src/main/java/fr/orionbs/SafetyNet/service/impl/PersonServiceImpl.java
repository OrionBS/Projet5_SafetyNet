package fr.orionbs.SafetyNet.service.impl;

import fr.orionbs.SafetyNet.exception.MissingParamException;
import fr.orionbs.SafetyNet.model.*;
import fr.orionbs.SafetyNet.repositoryTest.MedicalRecordRepository;
import fr.orionbs.SafetyNet.repositoryTest.PersonRepository;
import fr.orionbs.SafetyNet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person updatePerson(String firstName,String lastName,Person person) {
        Person personSelected = personRepository.findByFirstNameAndLastName(firstName, lastName);
        personSelected.setAddress(person.getAddress());
        personSelected.setEmail(person.getEmail());
        personSelected.setPhone(person.getPhone());
        personSelected.setCity(person.getCity());
        personSelected.setZip(person.getZip());
        return personRepository.save(personSelected);
    }

    @Override
    public void deletePerson(String firstName, String lastName) {
        personRepository.deleteByFirstNameAndLastName(firstName,lastName);
    }

    @Override
    public Person findByFirstNameAndLastName(String firstName, String lastName) {
        return personRepository.findByFirstNameAndLastName(firstName,lastName);
    }

    @Override
    public List<Person> save(Collection<Person> persons) {
        return personRepository.saveAll(persons);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public List<CommunityEmail> getEmailFilterByTown(String city) {
        List<CommunityEmail> emails = new ArrayList<>();
        List<Person> persons = personRepository.findByCity(city);
        if (CollectionUtils.isEmpty(persons)) {
            throw new MissingParamException("Aucun email n'est lié à cette ville: "+city+".");
        }
        persons.forEach(person -> {
            emails.add(new CommunityEmail(person.getEmail()));
        });
        return emails;
    }

    @Override
    public List<PersonInfo> getPersonInfo(String firstName, String lastName) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        List<PersonInfo> personInfos = new ArrayList<>();

        List<Person> persons = personRepository.findByLastName(lastName);
        if (CollectionUtils.isEmpty(persons)) {
            throw new MissingParamException("Aucune personne ne correspond à cette dénomination: "+firstName+lastName);
        }
        persons.forEach(person -> {
            PersonInfo personInfo = new PersonInfo();
            personInfo.setLastName(person.getLastName());
            personInfo.setAddress(person.getAddress());
            LocalDate dateNow = LocalDate.now();
            MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(), dateTimeFormatter);
            personInfo.setAge(Period.between(birthDate, dateNow).getYears());
            personInfo.setEmail(person.getEmail());
            personInfo.setMedications(medicalRecord.getMedications());
            personInfo.setAllergies(medicalRecord.getAllergies());
            personInfos.add(personInfo);
        });

        return personInfos;
    }

    @Override
    public List<ChildAlert> getChildByAddress(String address) {
        List<ChildAlert> childs = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        List<Person> persons = personRepository.findByAddress(address);
        if (CollectionUtils.isEmpty(persons)) {
            throw new MissingParamException("Aucun enfant à cette adresse: "+address);
        }
        persons.forEach(person -> {
            LocalDate dateNow = LocalDate.now();
            MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(), dateTimeFormatter);
            int age = Period.between(birthDate, dateNow).getYears();
            if (age <= 18) {
                ChildAlert child = new ChildAlert();
                child.setChildFirstName(person.getFirstName());
                child.setChildLastName(person.getLastName());
                child.setChildAge(age);
                List<Person> otherAddressMember = personRepository.findByAddress(address);
                otherAddressMember.remove(person);
                child.setChildFamily(otherAddressMember);
                childs.add(child);
            }
        });

        return childs;
    }

    @Override
    public Boolean getPerson(String firstName, String lastName) {
        Person person = personRepository.findByFirstNameAndLastName(firstName,lastName);
        if (person == null) {
            return true;
        }
        return false;
    }
}
