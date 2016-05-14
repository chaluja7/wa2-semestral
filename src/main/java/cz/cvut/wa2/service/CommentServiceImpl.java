package cz.cvut.wa2.service;

import cz.cvut.wa2.dao.HibernateCommentDao;
import cz.cvut.wa2.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jakubchalupa
 * @since 14.05.16
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    protected HibernateCommentDao hibernateCommentDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Comment find(long id) {
        return hibernateCommentDao.find(id);
    }

    @Override
    @Transactional
    public void persist(Comment comment) {
        hibernateCommentDao.persist(comment);
    }

    @Override
    @Transactional
    public void merge(Comment comment) {
        hibernateCommentDao.merge(comment);
    }

    @Override
    @Transactional
    public void delete(long id) {
        hibernateCommentDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Comment findByIdLazyLoaded(long id) {
        return hibernateCommentDao.findByIdLazyLoaded(id);
    }

}
