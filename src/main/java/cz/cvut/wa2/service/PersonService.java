package cz.cvut.wa2.service;


import cz.cvut.wa2.entity.Person;

import java.util.List;

/**
 * @author jakubchalupa
 * @since 19.03.16
 */
public interface PersonService {

    Person find(long id);

    Person findPersonByToken(String token);

    void persist(Person person);

    void merge(Person person);

    void delete(long id);

    List<Person> findAll();

    Person findWithRoles(long id);

    List<Person> findAllWithRoles();

}
