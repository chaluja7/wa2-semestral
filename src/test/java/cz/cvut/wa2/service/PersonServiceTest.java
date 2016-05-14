package cz.cvut.wa2.service;

import cz.cvut.wa2.entity.Person;
import cz.cvut.wa2.entity.Role;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jakubchalupa
 * @since 19.03.16
 */
public class PersonServiceTest extends AbstractServiceTest {

    @Autowired
    protected PersonService personService;

    @Test
    public void testPersonCRUD() {
        final String email = "aaa@bb.cz";
        final String name = "jmeno";
        final String surname = "prijmeni";
        final String token = "asdf";
        Set<Role> roles = new HashSet<>(Arrays.asList(new Role(Role.Type.ADMIN),new Role(Role.Type.USER)));

        Person person = getPerson(email, name, surname, roles, token);
        personService.persist(person);

        Person retrievedPerson = personService.findWithRoles(person.getId());
        Assert.assertNotNull(retrievedPerson);
        Assert.assertEquals(email, retrievedPerson.getEmail());
        Assert.assertEquals(name, retrievedPerson.getName());
        Assert.assertEquals(surname, retrievedPerson.getSurname());
        for(Role role : retrievedPerson.getRoles()) {
            Assert.assertEquals(true, roles.contains(role));
        }
        Assert.assertEquals(roles.size(), retrievedPerson.getRoles().size());

        final String newName = "jmeno2";
        retrievedPerson.setName(newName);
        retrievedPerson.getRoles().remove(new Role(Role.Type.ADMIN));

        personService.merge(retrievedPerson);
        retrievedPerson = personService.findPersonByToken(token);
        Assert.assertNotNull(retrievedPerson);
        Assert.assertEquals(newName, retrievedPerson.getName());
        Assert.assertEquals(1, retrievedPerson.getRoles().size());
        Assert.assertTrue(retrievedPerson.getRoles().contains(new Role(Role.Type.USER)));
        Assert.assertFalse(retrievedPerson.getRoles().contains(new Role(Role.Type.ADMIN)));

        retrievedPerson.getRoles().add(new Role(Role.Type.ADMIN));
        personService.merge(retrievedPerson);

        retrievedPerson = personService.findPersonByToken(token);
        Assert.assertNotNull(retrievedPerson);
        Assert.assertTrue(retrievedPerson.getRoles().contains(new Role(Role.Type.USER)));
        Assert.assertTrue(retrievedPerson.getRoles().contains(new Role(Role.Type.ADMIN)));

        personService.delete(person.getId());
        Assert.assertNull(personService.findWithRoles(person.getId()));
    }

    public static Person getPerson(String email, String name, String surname, Set<Role> roles, String token) {
        Person person = new Person();
        person.setEmail(email);
        person.setName(name);
        person.setSurname(surname);
        person.setRoles(roles);
        person.setToken(token);
        return person;
    }

}
