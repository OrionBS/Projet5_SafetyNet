package fr.orionbs.SafetyNet.repository;

import fr.orionbs.SafetyNet.model.MedicalRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MedicalRecordRepositoryTest {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Test
    public void testFindByFirstNameAndLastName(){
        //GIVEN

        //WHEN
        MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName("John","Boyd");

        //THEN
        assertThat(medicalRecord).isNotNull();
        assertThat(medicalRecord.getFirstName()).isEqualTo("John");
        assertThat(medicalRecord.getLastName()).isEqualTo("Boyd");
    }
}
