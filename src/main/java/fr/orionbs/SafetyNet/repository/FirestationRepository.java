package fr.orionbs.SafetyNet.repository;

import fr.orionbs.SafetyNet.model.Firestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirestationRepository extends JpaRepository<Firestation, Integer> {

    List<Firestation> findByStation(int number);

    Firestation findByAddress(String address);

    Firestation findByAddressAndStation(String address, int station);

    void deleteByAddress(String address);
}