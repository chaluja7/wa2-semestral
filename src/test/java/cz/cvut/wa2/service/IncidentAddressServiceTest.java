package cz.cvut.wa2.service;

import cz.cvut.wa2.entity.Incident;
import cz.cvut.wa2.entity.IncidentAddress;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jakubchalupa
 * @since 14.05.16
 */
public class IncidentAddressServiceTest extends AbstractServiceTest {

    @Autowired
    protected IncidentService incidentService;

    @Autowired
    protected IncidentAddressService incidentAddressService;

    @Test
    public void testCRUD() {
        IncidentAddress incidentAddress = prepareIncidentAddress();
        incidentAddressService.persist(incidentAddress);

        sessionFactory.getCurrentSession().clear();

        IncidentAddress retrieved = incidentAddressService.find(incidentAddress.getId());

        Assert.assertNotNull(retrieved);
        Assert.assertEquals(retrieved.getAddress(), incidentAddress.getAddress());

        retrieved.setAddress("PRAHA 10");
        incidentAddressService.merge(retrieved);

        retrieved = incidentAddressService.find(incidentAddress.getId());
        Assert.assertNotNull(retrieved);
        Assert.assertEquals("PRAHA 10", retrieved.getAddress());
    }

    public IncidentAddress prepareIncidentAddress() {
        IncidentAddress incidentAddress = new IncidentAddress();

        Incident incident = incidentService.find(1);
        incidentAddress.setIncident(incident);
        incidentAddress.setAddress("aaa bbb");

        return incidentAddress;
    }

}
