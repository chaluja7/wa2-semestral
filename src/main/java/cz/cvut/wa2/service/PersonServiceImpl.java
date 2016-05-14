package cz.cvut.wa2.service;

import cz.cvut.wa2.dao.HibernatePersonDao;
import cz.cvut.wa2.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author jakubchalupa
 * @since 19.03.16
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    protected HibernatePersonDao hibernatePersonDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Person find(long id) {
        return hibernatePersonDao.find(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Person findPersonByToken(String token) {
        return hibernatePersonDao.findPersonByToken(token);
    }

    @Override
    @Transactional
    public void persist(Person person) {
        hibernatePersonDao.persist(person);
    }

    @Override
    @Transactional
    public void merge(Person person) {
        hibernatePersonDao.merge(person);
    }

    @Override
    @Transactional
    public void delete(long id) {
        hibernatePersonDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Person> findAll() {
        return hibernatePersonDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Person findWithRoles(long id) {
        return hibernatePersonDao.findWithRoles(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Person> findAllWithRoles() {
        return hibernatePersonDao.findAllWithRoles();
    }

}
