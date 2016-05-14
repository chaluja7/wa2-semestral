package cz.cvut.wa2.service;

import cz.cvut.wa2.entity.Incident;
import cz.cvut.wa2.entity.PossibleIncidentRegion;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jakubchalupa
 * @since 14.05.16
 */
public class PossibleIncidentRegionServiceTest extends AbstractServiceTest {

    @Autowired
    protected IncidentService incidentService;

    @Autowired
    protected PossibleIncidentRegionService possibleIncidentRegionService;

    @Test
    public void testCRUD() {
        PossibleIncidentRegion possibleIncidentRegion = preparePossibleIncidentRegion();
        possibleIncidentRegionService.persist(possibleIncidentRegion);

        sessionFactory.getCurrentSession().clear();

        PossibleIncidentRegion retrieved = possibleIncidentRegionService.find(possibleIncidentRegion.getId());

        Assert.assertNotNull(retrieved);
        Assert.assertEquals(retrieved.isComputing(), possibleIncidentRegion.isComputing());

        retrieved.setPossibleRegionName("PRAHA 10");
        retrieved.setComputing(false);
        possibleIncidentRegionService.merge(retrieved);

        retrieved = possibleIncidentRegionService.find(possibleIncidentRegion.getId());
        Assert.assertNotNull(retrieved);
        Assert.assertEquals("PRAHA 10", retrieved.getPossibleRegionName());
        Assert.assertFalse(retrieved.isComputing());
        Assert.assertFalse(retrieved.isUnknownRegion());
    }

    public PossibleIncidentRegion preparePossibleIncidentRegion() {
        PossibleIncidentRegion possibleIncidentRegion = new PossibleIncidentRegion();

        Incident incident = incidentService.find(1);
        possibleIncidentRegion.setIncident(incident);
        possibleIncidentRegion.setComputing(true);

        return possibleIncidentRegion;
    }

}
