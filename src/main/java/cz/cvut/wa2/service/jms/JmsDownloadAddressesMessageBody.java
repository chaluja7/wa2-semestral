package cz.cvut.wa2.service.jms;

/**
 * @author jakubchalupa
 * @since 15.05.16
 */
public class JmsDownloadAddressesMessageBody {

    private final Long incidentId;

    public JmsDownloadAddressesMessageBody(Long incidentId) {
        this.incidentId = incidentId;
    }

    public Long getIncidentId() {
        return incidentId;
    }

}
