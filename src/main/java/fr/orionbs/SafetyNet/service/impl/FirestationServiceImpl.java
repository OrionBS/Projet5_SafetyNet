package fr.orionbs.SafetyNet.service.impl;

import fr.orionbs.SafetyNet.exception.MissingParamException;
import fr.orionbs.SafetyNet.model.Person;
import fr.orionbs.SafetyNet.repository.FirestationRepository;
import fr.orionbs.SafetyNet.repository.MedicalRecordRepository;
import fr.orionbs.SafetyNet.repository.PersonRepository;
import fr.orionbs.SafetyNet.service.FirestationService;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
@Transactional
public class FirestationServiceImpl implements FirestationService {

    @Autowired
    private FirestationRepository firestationRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public Firestation addFirestation(Firestation firestation) {
        log.info("Service Firestation : new address: "+firestation.getAddress()+" new station: "+firestation.getStation()+" adding.");
        return firestationRepository.save(firestation);
    }

    @Override
    public Firestation updateFirestation(String address,Firestation firestation) {
        log.info("Service Firestation : address: "+firestation.getAddress()+" station: "+firestation.getStation()+" updating.");
        Firestation firestationSelected = firestationRepository.findByAddress(address);
        if (firestationSelected == null) {
            throw new MissingParamException("Aucune station ne correspond à cette adresse: "+address);
        }
        firestationSelected.setStation(firestation.getStation());
        return firestationRepository.save(firestationSelected);
    }

    @Override
    public List<Firestation> save(Collection<Firestation> firestations) {
        log.info("Service Firestation : Firestation's list adding.");
        return firestationRepository.saveAll(firestations);
    }

    @Override
    public List<Firestation> findAll() {
        log.info("Service Firestation : All Firestation's list finding.");
        return firestationRepository.findAll();
    }

    @Override
    public FirestationCoverage getPersonsByCoverage(int station) {
        log.info("Service Firestation : station: "+station+" getting persons covered.");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        FirestationCoverage personsAndCount = new FirestationCoverage();
        List<Person> personsCovered = new ArrayList<>();
        List<Firestation> firestations = firestationRepository.findByStation(station);
        if (CollectionUtils.isEmpty(firestations)) {
            throw new MissingParamException("Aucune station n'existe avec ce numéro "+station);
        }
        firestations.forEach(firestation -> {
            List<Person> personsByAddress = personRepository.findByAddress(firestation.getAddress());
            personsByAddress.forEach(person -> {
                personsCovered.add(person);
                LocalDate dateNow = LocalDate.now();
                MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
                LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(), dateTimeFormatter);
                if(Period.between(birthDate, dateNow).getYears() >= 18) {
                    personsAndCount.setAdultCount(personsAndCount.getAdultCount()+1);
                }
                if (Period.between(birthDate, dateNow).getYears() <= 18) {
                    personsAndCount.setChildCount(personsAndCount.getChildCount()+1);
                }
            });
        });
        personsAndCount.setPersons(personsCovered);
        return personsAndCount;
    }

    @Override
    public List<PhoneAlert> getPhoneByCoverage(int station) {
        log.info("Service Firestation : station: "+station+" getting persons phone covered.");
        List<PhoneAlert> phonesCovered = new ArrayList<>();
        List<Firestation> firestations = firestationRepository.findByStation(station);
        if (CollectionUtils.isEmpty(firestations)) {
            throw new MissingParamException("Aucune station ne correspond à ce numéro: "+station);
        }
        firestations.forEach(firestation -> {
            List<Person> personsByAddress = personRepository.findByAddress(firestation.getAddress());
            personsByAddress.forEach(person -> {
                phonesCovered.add(new PhoneAlert(person.getPhone()));
            });
        });
        return phonesCovered;
    }

    @Override
    public List<Fire> getFirestationAndPersonsMedicalRecord(String address) {
        log.info("Service Firestation : address: "+address+" getting firestation and persons medicalRecord by address.");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        List<Fire> firestationAndPersonsMedicalRecord = new ArrayList<>();
        Firestation firestation = firestationRepository.findByAddress(address);
        if (firestation == null) {
            throw new MissingParamException("Aucune station à cette adresse: "+address);
        }
        List<Person> persons = personRepository.findByAddress(address);
        if (CollectionUtils.isEmpty(persons)) {
            throw new MissingParamException("Aucune personne à cette adresse: "+address);
        }
        persons.forEach(person -> {
            Fire fire = new Fire();
            fire.setFirestation(firestation);
            fire.setLastName(person.getLastName());
            fire.setPhone(person.getPhone());
            LocalDate dateNow = LocalDate.now();
            MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(), dateTimeFormatter);
            fire.setAge(Period.between(birthDate, dateNow).getYears());
            fire.setMedications(medicalRecord.getMedications());
            fire.setAllergies(medicalRecord.getAllergies());
            firestationAndPersonsMedicalRecord.add(fire);
        });
        return firestationAndPersonsMedicalRecord;
    }

    @Override
    public Boolean getFirestation(String address, int station) {
        log.info("Service Firestation : address: "+address+" station: "+station+" getting firestation.");
        Firestation firestation = firestationRepository.findByAddressAndStation(address, station);
        return firestation == null;
    }

    @Override
    public List<FloodStation> getFloodStation(List<Integer> stations) {
        log.info("Service Firestation : Getting floodstation firestation.");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        List<FloodStation> floodStations = new ArrayList<>();
        stations.forEach(station -> {
            List<Firestation> firestations = firestationRepository.findByStation(station);
            if (CollectionUtils.isEmpty(firestations)) {
                throw new MissingParamException("Aucune station ne correspond à ce numéro: "+station);
            }
            firestations.forEach(firestation -> {
                List<Person> persons = personRepository.findByAddress(firestation.getAddress());
                persons.forEach(person -> {
                    FloodStation floodStation = new FloodStation();
                    floodStation.setStation(station);
                    floodStation.setLastName(person.getLastName());
                    floodStation.setPhone(person.getPhone());
                    MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
                    LocalDate dateNow = LocalDate.now();
                    LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(), dateTimeFormatter);
                    floodStation.setAge(Period.between(birthDate, dateNow).getYears());
                    floodStation.setMedications(medicalRecord.getMedications());
                    floodStation.setAllergies(medicalRecord.getAllergies());
                    floodStations.add(floodStation);
                });
            });

        });
        return floodStations;
    }

    @Override
    public void deleteFirestationByAddress(String address) {
        log.info("Service Firestation : address: "+address+" firestation deleting.");
        firestationRepository.deleteByAddress(address);
    }
}
