package fr.orionbs.SafetyNet.service.impl;

import fr.orionbs.SafetyNet.model.MedicalRecord;
import fr.orionbs.SafetyNet.repository.MedicalRecordRepository;
import fr.orionbs.SafetyNet.service.MedicalRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@Transactional
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        log.info("Service MedicalRecord : "+medicalRecord.toString()+" adding.");
        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord updateMedicalRecord(String firstName, String lastName,MedicalRecord medicalRecord) {
        log.info("Service MedicalRecord : firstname: "+firstName+" lastname: "+lastName+" updating.");
        MedicalRecord medicalRecordSelected = medicalRecordRepository.findByFirstNameAndLastName(firstName,lastName);
        medicalRecordSelected.setBirthdate(medicalRecord.getBirthdate());
        medicalRecordSelected.setMedications(medicalRecord.getMedications());
        medicalRecordSelected.setAllergies(medicalRecord.getAllergies());
        return medicalRecordRepository.save(medicalRecordSelected);
    }

    @Override
    public void deleteMedicalRecord(String firstName, String lastName) {
        log.info("Service MedicalRecord : firstname: "+firstName+" lastname: "+lastName+" deleting.");
        medicalRecordRepository.deleteByFirstNameAndLastName(firstName,lastName);
    }

    @Override
    public List<MedicalRecord> save(Collection<MedicalRecord> medicalRecords) {
        log.info("Service MedicalRecord : MedicalRecord's list adding.");
        return medicalRecordRepository.saveAll(medicalRecords);
    }

    @Override
    public List<MedicalRecord> findAll() {
        log.info("Service MedicalRecord : All MedicalRecord's list finding.");
        return medicalRecordRepository.findAll();
    }

    @Override
    public Boolean getMedicalRecord(String firstName, String lastName) {
        log.info("Service MedicalRecord : firstname: "+firstName+" lastname: "+lastName+" finding.");
        MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(firstName,lastName);
        if (medicalRecord == null) {
            return true;
        }
        return false;
    }
}