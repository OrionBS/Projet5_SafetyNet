package fr.orionbs.SafetyNet.service;

import fr.orionbs.SafetyNet.model.*;

import java.util.Collection;
import java.util.List;

public interface FirestationService {

    Firestation addFirestation(Firestation firestation);

    Firestation updateFirestation(String address,Firestation firestation);

    List<Firestation> save(Collection<Firestation> firestations);

    List<Firestation> findAll();

    FirestationCoverage getPersonsByCoverage(int number);

    List<PhoneAlert> getPhoneByCoverage(int station);

    List<Fire> getFirestationAndPersonsMedicalRecord(String address);

    Boolean getFirestation(String address, int station);

    List<FloodStation> getFloodStation(List<Integer> stations);

    void deleteFirestationByAddress(String address);

    void deleteFirestationByStation(String station);
}
