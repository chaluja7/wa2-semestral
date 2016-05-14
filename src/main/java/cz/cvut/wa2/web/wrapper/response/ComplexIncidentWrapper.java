package cz.cvut.wa2.web.wrapper.response;

import java.util.List;

/**
 * Complex incident model.
 *
 * @author jakubchalupa
 * @since 29.12.14
 */
public class ComplexIncidentWrapper extends SimpleIncidentWrapper {

    private String description;

    private String address;

    private List<MessageWrapper> messages;

    private List<MessageWrapper> comments;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<MessageWrapper> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageWrapper> messages) {
        this.messages = messages;
    }

    public List<MessageWrapper> getComments() {
        return comments;
    }

    public void setComments(List<MessageWrapper> comments) {
        this.comments = comments;
    }
}
