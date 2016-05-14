package cz.cvut.wa2.service;


import cz.cvut.wa2.entity.Person;

import java.util.List;

/**
 * @author jakubchalupa
 * @since 19.03.16
 */
public interface PersonService {

    Person findPerson(long id);

    Person findPersonByToken(String token);

    void persistPerson(Person person);

    void mergePerson(Person person);

    void deletePerson(long id);

    List<Person> findAllPersons();

}
