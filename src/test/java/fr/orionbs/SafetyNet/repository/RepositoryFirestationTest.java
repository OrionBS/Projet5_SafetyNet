package fr.orionbs.SafetyNet.repository;

import fr.orionbs.SafetyNet.model.Firestation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RepositoryFirestationTest {


    @Test
    void deleteByAddressAndStation() {
    }

    @Test
    void testFindByStationShouldReturnList() {
        // GIVEN
        Firestation firestation = new Firestation();
        firestation.setAddress("testingg");
        firestation.setStation(4);

        // WHEN


    }

    @Test
    void findByAddress() {
    }

    @Test
    void findByAddressAndStation() {
    }
}