package lu.findl.multitenant.entities.tenant;

import lu.findl.multitenant.entities.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "customer")
public class Customer extends AbstractEntity {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, unique = true)
    private String name;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
