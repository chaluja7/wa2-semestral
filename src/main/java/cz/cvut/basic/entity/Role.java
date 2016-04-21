package cz.cvut.basic.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author jakubchalupa
 * @since 17.03.16
 */
@Entity
@Table(name = "roles")
public class Role implements Serializable {

    private static final long serialVersionUID = -3457743788806834700L;

    public Role() {
        //empty
    }

    public Role(Type type) {
        this.type = type;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<Person> persons;


    @Id
    @Column
    @Enumerated(EnumType.STRING)
    private Type type;

    public Type getType() {
        return type;
    }

    public void getType(Type type) {
        this.type = type;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if(getType() == null || o == null) {
            return false;
        }

        if(!(o instanceof Role)) {
            return false;
        }

        return getType().equals(((Role) o).type);
    }

    @Override
    public int hashCode() {
        return type.toString().hashCode();
    }

    @Override
    public String toString() {
        return "Role{" +
                "type=" + type +
                '}';
    }

    public enum Type {
        ADMIN,
        USER
    }

}
