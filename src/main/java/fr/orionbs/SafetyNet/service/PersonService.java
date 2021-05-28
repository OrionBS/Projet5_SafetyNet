package fr.orionbs.SafetyNet.service;

import fr.orionbs.SafetyNet.model.ChildAlert;
import fr.orionbs.SafetyNet.model.CommunityEmail;
import fr.orionbs.SafetyNet.model.Person;
import fr.orionbs.SafetyNet.model.PersonInfo;

import java.util.Collection;
import java.util.List;

public interface PersonService {
    Person addPerson(Person person);
    Person updatePerson(String firstName,String lastName, Person person);
    void deletePerson(String firstName, String lastName);
    Person findByFirstNameAndLastName(String firstName, String lastName);
    List<Person> save(Collection<Person> persons);
    List<Person> findAll();

    List<CommunityEmail> getEmailFilterByTown(String city);

    List<PersonInfo> getPersonInfo(String firstName, String lastName);

    List<ChildAlert> getChildByAddress(String address);

    Boolean getPerson(String firstName, String lastName);
}
