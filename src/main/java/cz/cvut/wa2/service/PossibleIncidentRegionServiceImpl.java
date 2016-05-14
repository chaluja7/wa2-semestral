package cz.cvut.wa2.service;

import cz.cvut.wa2.dao.HibernatePossibleIncidentRegionDao;
import cz.cvut.wa2.entity.PossibleIncidentRegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jakubchalupa
 * @since 14.05.16
 */
@Service
public class PossibleIncidentRegionServiceImpl implements PossibleIncidentRegionService {

    @Autowired
    protected HibernatePossibleIncidentRegionDao hibernatePossibleIncidentRegionDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PossibleIncidentRegion find(long id) {
        return hibernatePossibleIncidentRegionDao.find(id);
    }

    @Override
    @Transactional
    public void persist(PossibleIncidentRegion possibleIncidentRegion) {
        hibernatePossibleIncidentRegionDao.persist(possibleIncidentRegion);
    }

    @Override
    @Transactional
    public void merge(PossibleIncidentRegion possibleIncidentRegion) {
        hibernatePossibleIncidentRegionDao.merge(possibleIncidentRegion);
    }

    @Override
    @Transactional
    public void delete(long id) {
        hibernatePossibleIncidentRegionDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PossibleIncidentRegion findByIncidentId(long incidentId) {
        return hibernatePossibleIncidentRegionDao.findByIncidentId(incidentId);
    }

}
