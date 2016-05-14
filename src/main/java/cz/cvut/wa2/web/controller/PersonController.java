package cz.cvut.wa2.web.controller;

import cz.cvut.wa2.entity.Person;
import cz.cvut.wa2.entity.Role;
import cz.cvut.wa2.service.PersonService;
import cz.cvut.wa2.web.controller.exception.BadRequestException;
import cz.cvut.wa2.web.controller.exception.ResourceNotFoundException;
import cz.cvut.wa2.web.interceptor.CheckAccess;
import cz.cvut.wa2.web.wrapper.PersonWrapper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Persons controller - accessed by admin only.
 *
 * @author jakubchalupa
 * @since 20.03.16
 */
@RestController
@CheckAccess(Role.Type.ADMIN)
public class PersonController extends AbstractController {

    @Autowired
    protected PersonService personService;

    @RequestMapping(value = "/persons/all", method = RequestMethod.GET)
    public List<PersonWrapper> getPersons() {
        List<PersonWrapper> personWrappers = new ArrayList<>();
        for (Person person : personService.findAll()) {
            personWrappers.add(getPersonWrapperFromPerson(person));
        }

        return personWrappers;
    }

    @RequestMapping(value = "/persons/{personId}", method = RequestMethod.GET)
    public PersonWrapper getPerson(@PathVariable Long personId) {
        PersonWrapper personWrapper = getPersonWrapperFromPerson(personService.findWithRoles(personId));
        if(personWrapper == null) {
            throw new ResourceNotFoundException();
        }

        return personWrapper;
    }

    @RequestMapping(value = "/persons/create", method = RequestMethod.POST)
    public ResponseEntity<String> doCreateOrder(@RequestBody PersonWrapper personWrapper) {
        if(personWrapper == null) {
            throw new BadRequestException();
        }

        Person person = getPersonFromPersonWrapper(personWrapper);
        try {
            personService.persist(person);
        } catch (ConstraintViolationException e) {
            throw new BadRequestException();
        }

        return getResponseCreated("/persons/" + person.getId());
    }

    private PersonWrapper getPersonWrapperFromPerson(Person person) {
        if(person == null) return null;

        PersonWrapper wrapper = new PersonWrapper();
        wrapper.setEmail(person.getEmail());
        wrapper.setName(person.getName());
        wrapper.setSurname(person.getSurname());

        List<Role.Type> roles = new ArrayList<>();
        for(Role role : person.getRoles()) {
            roles.add(role.getType());
        }
        wrapper.setRoles(roles);

        return wrapper;
    }

    private Person getPersonFromPersonWrapper(PersonWrapper wrapper) {
        if(wrapper == null) return null;

        Person person = new Person();
        person.setEmail(wrapper.getEmail());
        person.setName(wrapper.getName());
        person.setSurname(wrapper.getSurname());

        if(wrapper.getRoles() != null) {
            Set<Role> roles = new HashSet<>();
            for (Role.Type type : wrapper.getRoles()) {
                roles.add(new Role(type));
            }
            person.setRoles(roles);
        }

        return person;
    }

}
