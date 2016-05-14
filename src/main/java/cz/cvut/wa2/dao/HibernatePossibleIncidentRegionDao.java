package cz.cvut.wa2.dao;

import cz.cvut.wa2.dao.generics.AbstractGenericHibernateDao;
import cz.cvut.wa2.entity.PossibleIncidentRegion;
import org.springframework.stereotype.Repository;

/**
 * @author jakubchalupa
 * @since 14.05.16
 */
@Repository
@SuppressWarnings("JpaQueryApiInspection")
public class HibernatePossibleIncidentRegionDao extends AbstractGenericHibernateDao<PossibleIncidentRegion> {

    public HibernatePossibleIncidentRegionDao() {
        super(PossibleIncidentRegion.class);
    }

    /**
     * @param incidentId id of incident
     * @return invalid incident reference for given incident id or null
     */
    public PossibleIncidentRegion findByIncidentId(long incidentId) {
        return (PossibleIncidentRegion) sessionFactory.getCurrentSession().getNamedQuery("PossibleIncidentRegion.findByIncidentId").setParameter("incidentId", incidentId).uniqueResult();
    }

}
