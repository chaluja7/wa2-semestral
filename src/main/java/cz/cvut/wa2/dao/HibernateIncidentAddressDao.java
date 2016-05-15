package cz.cvut.wa2.dao;

import cz.cvut.wa2.dao.generics.AbstractGenericHibernateDao;
import cz.cvut.wa2.entity.IncidentAddress;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jakubchalupa
 * @since 14.05.16
 */
@Repository
@SuppressWarnings("JpaQueryApiInspection")
public class HibernateIncidentAddressDao extends AbstractGenericHibernateDao<IncidentAddress> {

    public HibernateIncidentAddressDao() {
        super(IncidentAddress.class);
    }

    /**
     * @param incidentId id of incident
     * @return incident addresses for given incident id
     */
    public List<IncidentAddress> findByIncidentId(long incidentId) {
        return sessionFactory.getCurrentSession().getNamedQuery("IncidentAddress.findByIncidentId").setParameter("incidentId", incidentId).list();
    }

}
