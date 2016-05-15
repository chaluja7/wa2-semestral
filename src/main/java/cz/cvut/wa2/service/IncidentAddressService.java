package cz.cvut.wa2.service;

import cz.cvut.wa2.entity.IncidentAddress;

import java.util.List;

/**
 * @author jakubchalupa
 * @since 14.05.16
 */
public interface IncidentAddressService {

    IncidentAddress find(long id);

    void persist(IncidentAddress incidentAddress);

    void merge(IncidentAddress incidentAddress);

    void delete(long id);

    /**
     * @param incidentId id of incident
     * @return incident addresses for given incident id
     */
    List<IncidentAddress> findByIncidentId(long incidentId);

}
