package cz.cvut.wa2.service;

import cz.cvut.wa2.entity.Incident;
import cz.cvut.wa2.entity.Message;
import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jakubchalupa
 * @since 14.05.16
 */
public class MessageServiceTest extends AbstractServiceTest {

    @Autowired
    protected MessageService messageService;

    @Autowired
    protected IncidentService incidentService;

    @Autowired
    protected PersonService personService;

    @Test
    public void testCreateFind() {
        Message message = prepareMessage();
        incidentService.persist(message.getIncident());
        messageService.persist(message);

        sessionFactory.getCurrentSession().clear();

        Message retrieved = messageService.find(message.getId());

        Assert.assertNotNull(retrieved);
        Assert.assertEquals(message, retrieved);
        Assert.assertEquals(message.getInsertedTime(), retrieved.getInsertedTime());
        Assert.assertEquals(message.getText(), retrieved.getText());
        Assert.assertNotNull(retrieved.getAuthor());
        Assert.assertNotNull(retrieved.getIncident());
    }

    public Message prepareMessage() {
        Message message = new Message();

        Incident incident = incidentService.find(1);
        message.setIncident(incident);
        message.setAuthor(personService.find(1));
        message.setInsertedTime(new LocalDateTime());
        message.setText("foo");

        return message;
    }

}
