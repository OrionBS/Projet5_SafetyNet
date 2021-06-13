package fr.orionbs.SafetyNet.repository;

import fr.orionbs.SafetyNet.model.Firestation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class FirestationRepositoryTest {

    @Autowired
    private FirestationRepository firestationRepository;

    @Test
    void testFindByStation() {
        // GIVEN

        // WHEN
        List<Firestation> byStation = firestationRepository.findByStation(4);

        // THEN
        assertThat(byStation).isNotEmpty();
        assertThat(byStation).hasSize(2);

    }

    @Test
    void findByAddress() {
        //GIVEN

        //WHEN
        Firestation firestation = firestationRepository.findByAddress("1509 Culver St");

        //THEN
        assertThat(firestation).isNotNull();
        assertThat(firestation.getStation()).isEqualTo(3);
    }

    @Test
    void findByAddressAndStation() {
        //GIVEN

        //WHEN
        Firestation firestation = firestationRepository.findByAddressAndStation("1509 Culver St",3);

        //THEN
        assertThat(firestation).isNotNull();
        assertThat(firestation.getStation()).isEqualTo(3);
    }
}