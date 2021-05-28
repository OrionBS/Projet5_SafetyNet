package fr.orionbs.SafetyNet.service;

import fr.orionbs.SafetyNet.model.MedicalRecord;

import java.util.Collection;
import java.util.List;

public interface MedicalRecordService {
    MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);
    MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord);
    void deleteMedicalRecord(String firstName, String lastName);
    List<MedicalRecord> save(Collection<MedicalRecord> medicalRecords);
    List<MedicalRecord> findAll();

    Boolean getMedicalRecord(String firstName, String lastName);
}
