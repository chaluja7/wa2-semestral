package cz.cvut.wa2.service;


import cz.cvut.wa2.entity.Comment;

/**
 * @author jakubchalupa
 * @since 19.03.16
 */
public interface CommentService {

    Comment find(long id);

    void persist(Comment comment);

    void merge(Comment comment);

    void delete(long id);

    /**
     * @param id incident id
     * @return comment by id with author and incident
     */
    Comment findByIdLazyLoaded(long id);

}
