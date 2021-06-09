package fr.orionbs.SafetyNet.repository;

import fr.orionbs.SafetyNet.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    void deleteByFirstNameAndLastName(String firstName, String lastName);
    Person findByFirstNameAndLastName(String firstName, String lastName);
    List<Person> findByAddress(String address);

    List<Person> findByCity(String city);

    List<Person> findByLastName(String lastName);
}
