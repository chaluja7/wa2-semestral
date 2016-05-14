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
@Table(name = "possible_incident_region")
@NamedQuery(name = "PossibleIncidentRegion.findByIncidentId", query = "select i from PossibleIncidentRegion i where incident_id = :incidentId")
@SuppressWarnings("JpaQlInspection")
public class PossibleIncidentRegion extends AbstractEntity {

    @Column(name = "possible_region_name", nullable = true)
    @Length(max = 255)
    private String possibleRegionName;

    /**
     * iff region for given incident id is currently being computed
     */
    @Column
    private boolean computing;

    /**
     * true if region for this incident is unknown
     */
    @Column
    private boolean unknownRegion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incident_id", unique = true)
    private Incident incident;

    public String getPossibleRegionName() {
        return possibleRegionName;
    }

    public void setPossibleRegionName(String possibleRegionName) {
        this.possibleRegionName = possibleRegionName;
    }

    public boolean isComputing() {
        return computing;
    }

    public void setComputing(boolean computing) {
        this.computing = computing;
    }

    public boolean isUnknownRegion() {
        return unknownRegion;
    }

    public void setUnknownRegion(boolean unknownRegion) {
        this.unknownRegion = unknownRegion;
    }

    public Incident getIncident() {
        return incident;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }
}
