package fr.orionbs.SafetyNet.service.impl;

import fr.orionbs.SafetyNet.model.MedicalRecord;
import fr.orionbs.SafetyNet.repository.MedicalRecordRepository;
import fr.orionbs.SafetyNet.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord updateMedicalRecord(String firstName, String lastName,MedicalRecord medicalRecord) {
        MedicalRecord medicalRecordSelected = medicalRecordRepository.findByFirstNameAndLastName(firstName,lastName);
        medicalRecordSelected.setBirthdate(medicalRecord.getBirthdate());
        medicalRecordSelected.setMedications(medicalRecord.getMedications());
        medicalRecordSelected.setAllergies(medicalRecord.getAllergies());
        return medicalRecordRepository.save(medicalRecordSelected);
    }

    @Override
    public void deleteMedicalRecord(String firstName, String lastName) {
        medicalRecordRepository.deleteByFirstNameAndLastName(firstName,lastName);
    }

    @Override
    public List<MedicalRecord> save(Collection<MedicalRecord> medicalRecords) {
        return medicalRecordRepository.saveAll(medicalRecords);
    }

    @Override
    public List<MedicalRecord> findAll() {
        return medicalRecordRepository.findAll();
    }

    @Override
    public Boolean getMedicalRecord(String firstName, String lastName) {
        MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(firstName,lastName);
        if (medicalRecord == null) {
            return true;
        }
        return false;
    }
}
