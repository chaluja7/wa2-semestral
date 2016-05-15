package cz.cvut.wa2.service.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @author jakubchalupa
 * @since 15.05.16
 */
@Service
public class JmsMessageSender {

    @Autowired
    protected JmsTemplate jmsTemplate;

    /**
     * send request to download addresses to this incident
     * @param incidentId id of incident to calculate addresses
     */
    public void sendIncidentIdToDownloadAddresses(final Long incidentId) {
        this.jmsTemplate.send(session -> session.createTextMessage(getDownloadAddressMessageBody(incidentId)));
    }

    private static String getDownloadAddressMessageBody(Long incidentId) {
        try {
            return new ObjectMapper().writeValueAsString(new JmsDownloadAddressesMessageBody(incidentId));
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

}
