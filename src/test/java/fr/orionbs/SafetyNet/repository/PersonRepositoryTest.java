package fr.orionbs.SafetyNet.repository;

import fr.orionbs.SafetyNet.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    public void testFindByLastName(){
        //GIVEN

        //WHEN
        List<Person> personList = personRepository.findByLastName("Boyd");

        //THEN
        assertThat(personList).isNotEmpty();
        assertThat(personList).hasSize(6);
    }

    @Test
    public void testFindByFirstNameAndLastName(){
        //GIVEN

        //WHEN
        Person person = personRepository.findByFirstNameAndLastName("John","Boyd");

        //THEN
        assertThat(person).isNotNull();
        assertThat(person.getFirstName()).isEqualTo("John");
        assertThat(person.getLastName()).isEqualTo("Boyd");
    }

    @Test
    public void testFindByCity(){
        //GIVEN

        //WHEN
        List<Person> personList = personRepository.findByCity("Culver");

        //THEN
        assertThat(personList).isNotEmpty();
        assertThat(personList).hasSize(23);
    }
    @Test
    public void testFindByAddress(){
        //GIVEN

        //WHEN
        List<Person> personList = personRepository.findByAddress("1509 Culver St");

        //THEN
        assertThat(personList).isNotEmpty();
        assertThat(personList).hasSize(5);
    }

}
