package cz.cvut.wa2.entity;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

/**
 * Message visible for all people.
 *
 * @author jakubchalupa
 * @since 14.05.16
 */
@Entity
@Table(name = "message")
@NamedQueries({
    @NamedQuery(name = "Message.findByIdLazyLoaded", query = "select distinct m from Message m left outer join fetch m.author " +
        "left outer join fetch m.incident where m.id = :id"),
    @NamedQuery(name = "Message.findByIncidentId", query = "select m from Message m left outer join fetch m.author " +
        "where m.incident.id = :incidentId order by m.id")
})
@SuppressWarnings("JpaQlInspection")
public class Message extends AbstractEntity {

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime insertedTime;

    @Column(length=2000)
    @NotBlank
    @Length(min = 2, max = 2000)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incident_id")
    private Incident incident;

    public LocalDateTime getInsertedTime() {
        return insertedTime;
    }

    public void setInsertedTime(LocalDateTime insertedTime) {
        this.insertedTime = insertedTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public Incident getIncident() {
        return incident;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }
}
