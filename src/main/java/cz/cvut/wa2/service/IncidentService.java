package cz.cvut.wa2.service;

import cz.cvut.wa2.entity.Incident;
import cz.cvut.wa2.entity.IncidentState;

import java.util.List;

/**
 * @author jakubchalupa
 * @since 14.05.16
 */
public interface IncidentService {

    Incident find(long id);

    void persist(Incident incident);

    void merge(Incident incident);

    void delete(long id);

    List<Incident> findAll();
    
    /**
     * @param id incident id
     * @return incident by id with all messages, comments and its authors
     */
    Incident findByIdLazyLoaded(long id);

    /*
     * @param incidentStates possible states of incidents (if null then every state)
     * @return all incidents with given states
     */
    List<Incident> findAll(IncidentState... incidentStates);

    /**
     * will update state of incident
     * @param id incident id
     * @param state new state
     */
    void updateState(long id, IncidentState state);

}
