package demo.service.impl;

import demo.model.Employee;

import javax.inject.Named;

/**
 * This service provides basic CRUD operations for Employee entity
 *
 * @author Erofeev Danil
 * @see demo.model.Employee
 */
@Named
public class EmployeeService extends EntityService<Employee>{

    public EmployeeService() {
        super(Employee.class);
    }

}
