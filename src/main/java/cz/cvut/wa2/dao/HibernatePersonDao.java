package cz.cvut.wa2.dao;

import cz.cvut.wa2.dao.generics.AbstractGenericHibernateDao;
import cz.cvut.wa2.entity.Person;
import org.springframework.stereotype.Repository;

/**
 * @author jakubchalupa
 * @since 19.03.16
 */
@Repository
@SuppressWarnings("JpaQueryApiInspection")
public class HibernatePersonDao extends AbstractGenericHibernateDao<Person> {

    public HibernatePersonDao() {
        super(Person.class);
    }

    public Person findWithRoles(long id) {
        return (Person) sessionFactory.getCurrentSession().getNamedQuery("Person.findWithRoles").setParameter("id", id).uniqueResult();
    }

    public Person findPersonByToken(String token) {
        return (Person) sessionFactory.getCurrentSession().getNamedQuery("Person.findPersonByToken").setParameter("token", token).uniqueResult();
    }

}
