package demo.service;


import demo.model.Employee;
import demo.service.impl.EmployeeService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Testing basic CRUD operations
 * for entities which extends CoreEntity
 *
 * @author Erofeev Danil
 * @see demo.model.core.CoreEntity
 */
@RunWith(Arquillian.class)
public class EntityServiceTest {

    @Inject
    EmployeeService employeeService;

    @Deployment
    public static WebArchive createTestableDeployment() {
        return ShrinkWrap.create(WebArchive.class, "crudTest.war")
                .addPackages(true, "demo.model")
                .addPackages(true, "demo.service")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("beans.xml", "beans.xml");
    }

    @Test
    public void testEmployeeService() {
        employeeService.saveOrUpdate(new Employee("Name", "Surname", 10000));
        List<Employee> all = employeeService.findAll();
        assertNotNull(all);
        Employee employee = employeeService.find(all.get(0).getId());
        assertNotNull(employee);
        employee.setFirstName("FirstName");
        employee.setLastName("LastName");
        employee.setSalary(20000);
        employeeService.saveOrUpdate(employee);
        employee = employeeService.find(employee.getId());
        assertEquals("FirstName", employee.getFirstName());
        assertEquals("LastName", employee.getLastName());
        assertEquals((long) 20000, (long) employee.getSalary());
        employeeService.remove(employee);
        assertEquals(0, employeeService.findAll().size());
    }
}