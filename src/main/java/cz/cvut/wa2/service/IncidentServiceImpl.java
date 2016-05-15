package cz.cvut.wa2.service;

import cz.cvut.wa2.dao.HibernateIncidentDao;
import cz.cvut.wa2.entity.Incident;
import cz.cvut.wa2.entity.IncidentState;
import cz.cvut.wa2.entity.Message;
import cz.cvut.wa2.service.jms.JmsMessageSender;
import org.joda.time.LocalDateTime;
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
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    protected HibernateIncidentDao hibernateIncidentDao;

    @Autowired
    protected JmsMessageSender jmsMessageSender;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Incident find(long id) {
        return hibernateIncidentDao.find(id);
    }

    @Override
    @Transactional
    public void persist(Incident incident) {
        incident.setState(IncidentState.NEW);
        incident.setInsertedTime(new LocalDateTime());

        if(incident.getMessages().size() > 0) {
            for(Message message : incident.getMessages()) {
                message.setInsertedTime(new LocalDateTime());
            }
        }

        hibernateIncidentDao.persist(incident);

        //a dam pozadavek na urceni presnych adres z googlu pomoci JMS
        jmsMessageSender.sendIncidentIdToDownloadAddresses(incident.getId());
    }

    @Override
    @Transactional
    public void merge(Incident incident) {
        hibernateIncidentDao.merge(incident);
    }

    @Override
    @Transactional
    public void delete(long id) {
        hibernateIncidentDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Incident> findAll() {
        return hibernateIncidentDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Incident findByIdLazyLoaded(long id) {
        return hibernateIncidentDao.findByIdLazyLoaded(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Incident> findAll(IncidentState... incidentStates) {
        return hibernateIncidentDao.findAll(incidentStates);
    }

    @Override
    @Transactional
    public void updateState(long id, IncidentState state) {
        hibernateIncidentDao.updateState(id, state);
    }

}
