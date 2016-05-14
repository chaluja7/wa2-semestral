package cz.cvut.wa2.service;

import cz.cvut.wa2.entity.PossibleIncidentRegion;

/**
 * @author jakubchalupa
 * @since 14.05.16
 */
public interface PossibleIncidentRegionService {

    PossibleIncidentRegion find(long id);

    void persist(PossibleIncidentRegion possibleIncidentRegion);

    void merge(PossibleIncidentRegion possibleIncidentRegion);

    void delete(long id);

    /**
     * @param incidentId id of incident
     * @return invalid incident reference for given incident id or null
     */
    PossibleIncidentRegion findByIncidentId(long incidentId);

}
