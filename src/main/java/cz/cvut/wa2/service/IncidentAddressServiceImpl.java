package cz.cvut.wa2.service;

import cz.cvut.wa2.dao.HibernateIncidentAddressDao;
import cz.cvut.wa2.entity.IncidentAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author jakubchalupa
 * @since 14.05.16
 */
@Service
public class IncidentAddressServiceImpl implements IncidentAddressService {

    @Autowired
    protected HibernateIncidentAddressDao hibernateIncidentAddressDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public IncidentAddress find(long id) {
        return hibernateIncidentAddressDao.find(id);
    }

    @Override
    @Transactional
    public void persist(IncidentAddress incidentAddress) {
        hibernateIncidentAddressDao.persist(incidentAddress);
    }

    @Override
    @Transactional
    public void merge(IncidentAddress incidentAddress) {
        hibernateIncidentAddressDao.merge(incidentAddress);
    }

    @Override
    @Transactional
    public void delete(long id) {
        hibernateIncidentAddressDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<IncidentAddress> findByIncidentId(long incidentId) {
        return hibernateIncidentAddressDao.findByIncidentId(incidentId);
    }

}
