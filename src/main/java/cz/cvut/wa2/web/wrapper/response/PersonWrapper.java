package cz.cvut.wa2.web.wrapper.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import cz.cvut.wa2.web.wrapper.request.NewPersonWrapper;

/**
 * @author jakubchalupa
 * @since 17.04.16
 */
@JsonPropertyOrder({"id"})
public class PersonWrapper extends NewPersonWrapper {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
