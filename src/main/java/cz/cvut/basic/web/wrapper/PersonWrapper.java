package cz.cvut.basic.web.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author jakubchalupa
 * @since 17.04.16
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonWrapper implements Serializable {

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
