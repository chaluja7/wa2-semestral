package cz.cvut.wa2.entity;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jakubchalupa
 * @since 17.03.16
 */
@Entity
@Table(name = "persons")
@NamedQueries({
        @NamedQuery(name = "Person.findPersonByToken", query = "select distinct p from Person p left outer join fetch p.roles where p.token = :token"),
        @NamedQuery(name = "Person.findWithRoles", query = "select distinct p from Person p left outer join fetch p.roles where p.id = :id"),
        @NamedQuery(name = "Person.findAllWithRoles", query = "select distinct p from Person p left outer join fetch p.roles order by p.id")
})
@SuppressWarnings("JpaQlInspection")
public class Person extends AbstractEntity {

    private static final long serialVersionUID = -6719668226224017418L;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    @Size(max = 255)
    private String name;

    @Column(nullable = false)
    @Size(max = 255)
    private String surname;

    @Column(unique = true)
    @Size(max = 255)
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "person_role", joinColumns = @JoinColumn(name = "person_id", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false, updatable = false))
    private Set<Role> roles;

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

    public Set<Role> getRoles() {
        if(roles == null) {
            roles = new HashSet<>();
        }
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean hasRole(Role.Type roleNeeded) {
        return getRoles().contains(new Role(roleNeeded));
    }

    public String getWholeName() {
        return new StringBuilder().append(getName()).append(" ").append(getSurname()).toString();
    }
}
