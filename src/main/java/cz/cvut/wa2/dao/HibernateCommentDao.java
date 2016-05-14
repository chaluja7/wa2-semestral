package cz.cvut.wa2.dao;

import cz.cvut.wa2.dao.generics.AbstractGenericHibernateDao;
import cz.cvut.wa2.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jakubchalupa
 * @since 14.05.16
 */
@Repository
@SuppressWarnings("JpaQueryApiInspection")
public class HibernateCommentDao extends AbstractGenericHibernateDao<Comment> {

    public HibernateCommentDao() {
        super(Comment.class);
    }

    /**
     * @param id incident id
     * @return comment by id with author and incident
     */
    public Comment findByIdLazyLoaded(long id) {
        return (Comment) sessionFactory.getCurrentSession().getNamedQuery("Comment.findByIdLazyLoaded").setParameter("id", id).uniqueResult();
    }

    /**
     * @param incidentId incident id
     * @return comment by incident id with authors
     */
    public List<Comment> findByIncidentId(long incidentId) {
        return sessionFactory.getCurrentSession().getNamedQuery("Comment.findByIncidentId").setParameter("incidentId", incidentId).list();
    }

}
