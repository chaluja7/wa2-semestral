package cz.cvut.wa2.dao;

import cz.cvut.wa2.dao.generics.AbstractGenericHibernateDao;
import cz.cvut.wa2.entity.Incident;
import cz.cvut.wa2.entity.IncidentState;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * @author jakubchalupa
 * @since 19.03.16
 */
@Repository
@SuppressWarnings("JpaQueryApiInspection")
public class HibernateIncidentDao extends AbstractGenericHibernateDao<Incident> {

    public HibernateIncidentDao() {
        super(Incident.class);
    }

    /**
     * @param id incident id
     * @return incident by id with all messages, comments and its authors
     */
    public Incident findByIdLazyLoaded(long id) {
        return (Incident) sessionFactory.getCurrentSession().getNamedQuery("Incident.findByIdLazyLoaded").setParameter("id", id).uniqueResult();
    }

    /*
     * @param incidentStates possible states of incidents (if null then every state)
     * @return all incidents with given states
     */
    public List<Incident> findAll(IncidentState... incidentStates) {
        if(incidentStates == null || incidentStates.length == 0) {
            return sessionFactory.getCurrentSession().getNamedQuery("Incident.findAll").list();
        }

        return sessionFactory.getCurrentSession().getNamedQuery("Incident.findAllInStates").setParameterList("states", Arrays.asList(incidentStates)).list();
    }

    /**
     * will update state of incident
     * @param id incident id
     * @param state new state
     */
    public void updateState(long id, IncidentState state) {
        sessionFactory.getCurrentSession().getNamedQuery("Incident.updateState").setParameter("state", state).setParameter("id", id).executeUpdate();
    }

}
