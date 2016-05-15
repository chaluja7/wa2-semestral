package cz.cvut.wa2.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * Entity representing invalid incident reference.
 *
 * @author jakubchalupa
 * @since 14.05.16
 */
@Entity
@Table(name = "incident_addresses")
@NamedQuery(name = "IncidentAddress.findByIncidentId", query = "select ia from IncidentAddress ia where incident_id = :incidentId")
@SuppressWarnings("JpaQlInspection")
public class IncidentAddress extends AbstractEntity {

    @Column(nullable = false)
    @Length(max = 255)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incident_id")
    private Incident incident;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Incident getIncident() {
        return incident;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }
}
