package cz.cvut.wa2.web.wrapper.request;

import cz.cvut.wa2.entity.Role;

import java.util.List;

/**
 * @author jakubchalupa
 * @since 17.04.16
 */
public class NewPersonWrapper {

    private String email;

    private String name;

    private String surname;

    private List<Role.Type> roles;

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

    public List<Role.Type> getRoles() {
        return roles;
    }

    public void setRoles(List<Role.Type> roles) {
        this.roles = roles;
    }
}
