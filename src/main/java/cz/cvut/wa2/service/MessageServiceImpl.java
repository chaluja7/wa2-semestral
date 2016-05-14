package cz.cvut.wa2.service;

import cz.cvut.wa2.dao.HibernateMessageDao;
import cz.cvut.wa2.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jakubchalupa
 * @since 14.05.16
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    protected HibernateMessageDao hibernateMessageDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Message find(long id) {
        return hibernateMessageDao.find(id);
    }

    @Override
    @Transactional
    public void persist(Message message) {
        hibernateMessageDao.persist(message);
    }

    @Override
    @Transactional
    public void merge(Message message) {
        hibernateMessageDao.merge(message);
    }

    @Override
    @Transactional
    public void delete(long id) {
        hibernateMessageDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Message findByIdLazyLoaded(long id) {
        return hibernateMessageDao.findByIdLazyLoaded(id);
    }

}