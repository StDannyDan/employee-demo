package demo.model;

import demo.model.core.CoreEntity;

import javax.persistence.*;

/**
 * Employee entity class
 *
 * @author Erofeev Danil
 */
@Entity
@Table(name = "employees")
@Access(AccessType.FIELD)
@AttributeOverride(name = "id", column = @Column(name = "id_emp", insertable = false, updatable = false))
public class Employee extends CoreEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "salary")
    private Integer salary;

    public Employee() {
    }

    public Employee(String firstName, String lastName, Integer salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Id
    @Access(AccessType.PROPERTY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
    }
}
