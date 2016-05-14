package cz.cvut.wa2.service;


import cz.cvut.wa2.entity.Message;

/**
 * @author jakubchalupa
 * @since 19.03.16
 */
public interface MessageService {

    Message find(long id);

    void persist(Message message);

    void merge(Message message);

    void delete(long id);

    /**
     * @param id incident id
     * @return message by id with author and incident
     */
    Message findByIdLazyLoaded(long id);

}