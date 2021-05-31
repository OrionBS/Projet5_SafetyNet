package fr.orionbs.SafetyNet.repository;

import fr.orionbs.SafetyNet.model.Firestation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FirestationRepositoryTest {


    @Autowired
    private FirestationRepository firestationRepository;

    @Test
    void deleteByAddressAndStation() {
    }

    @Test
    void testFindByStationShouldReturnList() {
        // WHEN
        List<Firestation> byStation = firestationRepository.findByStation(4);

        // THEN
        assertThat(byStation).isNotEmpty();
        assertThat(byStation).hasSize(2);

    }

    @Test
    void findByAddress() {
    }

    @Test
    void findByAddressAndStation() {
    }
}
