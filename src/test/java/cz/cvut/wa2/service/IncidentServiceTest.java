package cz.cvut.wa2.service;

import cz.cvut.wa2.entity.Incident;
import cz.cvut.wa2.entity.IncidentState;
import cz.cvut.wa2.entity.Message;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author jakubchalupa
 * @since 14.05.16
 */
public class IncidentServiceTest extends AbstractServiceTest {

    @Autowired
    protected IncidentService incidentService;

    private Incident prepareIncident(String text) {
        Incident incident = new Incident();

        Message message = new Message();
        message.setText(text);
        Set<Message> messages = new HashSet<>();
        messages.add(message);

        incident.setTitle("testIncident");
        incident.setLatitude(10);
        incident.setLongitude(20);
        incident.setDescription("testDescription");
        incident.setMessages(messages);

        return incident;
    }

    @Test
    public void testCreateFind() {
        Incident incident = prepareIncident("foo");
        incidentService.persist(incident);
        sessionFactory.getCurrentSession().clear();

        Incident retrieved = incidentService.find(incident.getId());
        Assert.assertNotNull(retrieved);
        Assert.assertEquals(incident, retrieved);
        Assert.assertEquals(incident.getTitle(), retrieved.getTitle());
        Assert.assertEquals(incident.getLatitude(), retrieved.getLatitude(), 0);
        Assert.assertEquals(incident.getLongitude(), retrieved.getLongitude(), 0);
        Assert.assertEquals(incident.getState(), retrieved.getState());
        Assert.assertEquals(incident.getAddress(), retrieved.getAddress());
        Assert.assertEquals(incident.getDescription(), retrieved.getDescription());
    }

    @Test
    public void testUpdateState() {
        Incident incident = prepareIncident("foo");
        incidentService.persist(incident);

        incidentService.updateState(incident.getId(), IncidentState.IN_PROGRESS);

        sessionFactory.getCurrentSession().clear();

        Incident updated = incidentService.find(incident.getId());
        Assert.assertNotNull(updated);
        Assert.assertNotNull(updated.getState());
        Assert.assertEquals(updated.getState(), IncidentState.IN_PROGRESS);
    }

    @Test
    public void findAll() {
        Incident incident1 = prepareIncident("foo1");
        Incident incident2 = prepareIncident("foo2");
        Incident incident3 = prepareIncident("foo3");
        List<Incident> initialList = new ArrayList<>();
        initialList.add(incident1);
        initialList.add(incident2);

        incidentService.persist(incident1);
        incidentService.persist(incident2);
        incidentService.persist(incident3);

        List<Incident> retrievedList = incidentService.findAll(incident2.getState());
        Assert.assertTrue(retrievedList.containsAll(initialList));

        initialList.add(incident3);
        Assert.assertFalse(retrievedList.contains(initialList));
    }

    @Test
    public void testFindIncidentLazyInitialized() {
        Incident incident = prepareIncident("foo");
        incidentService.persist(incident);

        Incident retrieved = incidentService.find(incident.getId());
        Assert.assertEquals(retrieved.getComments().size(), 0);

        sessionFactory.getCurrentSession().clear();

        retrieved = incidentService.findByIdLazyLoaded(incident.getId());
        Assert.assertEquals(retrieved.getComments().size(), incident.getComments().size());
    }

    @Test
    public void testSavedData() {
        Incident byIdLazyLoaded = incidentService.findByIdLazyLoaded(1);
        Assert.assertNotNull(byIdLazyLoaded);
        Assert.assertNotNull(byIdLazyLoaded.getComments());
        Assert.assertNotNull(byIdLazyLoaded.getMessages());
    }

}
