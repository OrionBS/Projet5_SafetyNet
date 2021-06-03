package fr.orionbs.SafetyNet.repository;

import fr.orionbs.SafetyNet.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer> {
    void deleteByFirstNameAndLastName(String firstName, String lastName);

    MedicalRecord findByFirstNameAndLastName(String firstName, String lastName);
}
