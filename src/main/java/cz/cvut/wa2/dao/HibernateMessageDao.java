package cz.cvut.wa2.dao;

import cz.cvut.wa2.dao.generics.AbstractGenericHibernateDao;
import cz.cvut.wa2.entity.Message;
import org.springframework.stereotype.Repository;

/**
 * @author jakubchalupa
 * @since 14.05.16
 */
@Repository
@SuppressWarnings("JpaQueryApiInspection")
public class HibernateMessageDao extends AbstractGenericHibernateDao<Message> {

    public HibernateMessageDao() {
        super(Message.class);
    }

    /**
     * @param id incident id
     * @return message by id with author and incident
     */
    public Message findByIdLazyLoaded(long id) {
        return (Message) sessionFactory.getCurrentSession().getNamedQuery("Message.findByIdLazyLoaded").setParameter("id", id).uniqueResult();
    }

}
