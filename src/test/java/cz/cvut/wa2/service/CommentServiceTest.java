package cz.cvut.wa2.service;

import cz.cvut.wa2.entity.Comment;
import cz.cvut.wa2.entity.Incident;
import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jakubchalupa
 * @since 14.05.16
 */
public class CommentServiceTest extends AbstractServiceTest {

    @Autowired
    protected CommentService commentService;

    @Autowired
    protected IncidentService incidentService;

    @Autowired
    protected PersonService personService;

    @Test
    public void testCreateFind() {
        Comment comment = prepareComment();
        incidentService.persist(comment.getIncident());
        commentService.persist(comment);

        sessionFactory.getCurrentSession().clear();

        Comment retrieved = commentService.findByIdLazyLoaded(comment.getId());

        Assert.assertNotNull(retrieved);
        Assert.assertEquals(comment, retrieved);
        Assert.assertEquals(comment.getInsertedTime(), retrieved.getInsertedTime());
        Assert.assertEquals(comment.getText(), retrieved.getText());
        Assert.assertNotNull(retrieved.getAuthor());
        Assert.assertNotNull(retrieved.getIncident());
    }

    public Comment prepareComment() {
        Comment comment = new Comment();

        Incident incident = incidentService.find(1);
        comment.setIncident(incident);
        comment.setAuthor(personService.find(1));
        comment.setInsertedTime(new LocalDateTime());
        comment.setText("foo");

        return comment;
    }

}
