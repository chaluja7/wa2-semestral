package cz.cvut.basic.service;


import cz.cvut.basic.entity.Person;

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
