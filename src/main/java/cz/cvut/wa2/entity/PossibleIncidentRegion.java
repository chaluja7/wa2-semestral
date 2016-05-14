package cz.cvut.wa2.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * Entity representing invalid incident reference.
 *
 * @author jakubchalupa
 * @since 14.05.16
 */
@Entity
@Table(name = "possible_incident_region")
@NamedQuery(name = "PossibleIncidentRegion.findByIncidentId", query = "select i from PossibleIncidentRegion i where incident_id = :incidentId")
@SuppressWarnings("JpaQlInspection")
public class PossibleIncidentRegion extends AbstractEntity {

    @Column(name = "possible_region_name", nullable = false)
    @NotBlank
    @Length(min = 2, max = 20)
    private String possibleRegionName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "incident_id", unique = true)
    private Incident incident;

    public String getPossibleRegionName() {
        return possibleRegionName;
    }

    public void setPossibleRegionName(String possibleRegionName) {
        this.possibleRegionName = possibleRegionName;
    }

    public Incident getIncident() {
        return incident;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }
}
