package demo.view.paging;

import demo.model.Employee;
import demo.service.impl.EmployeeService;
import demo.view.paging.util.EmployeeRowsComparator;
import demo.view.rowediting.EmployeeRow;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Controller class for paging, adding, deleting and editing Employee
 *
 * @author Erofeev Danil
 */
@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class PagingViewController extends SelectorComposer {

    @WireVariable
    private EmployeeService employeeService;

    private List<EmployeeRow> employeeRowStatuses;

    private EmployeeRowsComparator employeeRowsComparator;

    @Wire
    private Textbox firstName;
    @Wire
    private Textbox lastName;
    @Wire
    private Intbox salary;

    @Init
    public void init() {    // Initialize and load data
        employeeRowStatuses = new ArrayList<>();
        for (Employee employee : employeeService.findAll()) {
            employeeRowStatuses.add(new EmployeeRow(employee, false));
        }
    }

    @AfterCompose   // Method wires textboxes to controller
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
    }

    /**
     * Getter method for employee rows
     *
     * @return list of EmployeeRows
     */
    public List<EmployeeRow> getEmployeeRowStatuses() {
        return employeeRowStatuses;
    }

    @Command
    public void changeEditableStatus(@BindingParam("employeeRowStatus") EmployeeRow ers) {
        ers.setEditingStatus(!ers.getEditingStatus());
        refreshRowTemplate(ers);
    }

    /**
     * Saves changes to Employee
     *
     * @param ers from view
     */
    @Command
    public void confirm(@BindingParam("employeeRowStatus") EmployeeRow ers) {
        employeeService.saveOrUpdate(ers.getEmployee());
        changeEditableStatus(ers);
        refreshRowTemplate(ers);
    }

    /**
     * Deletes EmployeeRow from controller and Employee from database
     *
     * @param ers from view
     */
    @Command
    @NotifyChange("employeeRowStatuses")
    public void deleteItem(@BindingParam("employeeRowStatus") EmployeeRow ers) {
        employeeService.remove(ers.getEmployee());
        employeeRowStatuses.remove(ers);
    }

    /**
     * Adds new Employee to database and new row to controller with values from boxes
     */
    @Command
    @NotifyChange("employeeRowStatuses")
    public void addNewEmployee() {
        Employee employee = new Employee(firstName.getValue(), lastName.getValue(), salary.getValue());
        EmployeeRow employeeRow = new EmployeeRow(employee, false);
        employeeRowStatuses.add(employeeRow);
        employeeService.saveOrUpdate(employee);
        firstName.setValue("");
        lastName.setValue("");
        salary.setValue(null);
    }

    /**
     * This code is special and notifies ZK that the bean's value
     * has changed as it is used in the template mechanism.
     * This stops the entire Grid's data from being refreshed
     *
     * @param ers updated row
     */
    public void refreshRowTemplate(EmployeeRow ers) {
        BindUtils.postNotifyChange(null, null, ers, "editingStatus");
    }

    /**
     * Sort rows by first employee name asc then changes current
     */
    @Command
    @NotifyChange("employeeRowStatuses")
    public void sortByName() {
        prepareRowsComparatorForType(EmployeeRowsComparator.FIRST_NAME);
        Collections.sort(employeeRowStatuses, employeeRowsComparator);
    }

    /**
     * Sort rows by last employee name asc then changes current
     */
    @Command
    @NotifyChange("employeeRowStatuses")
    public void sortBySurname() {
        prepareRowsComparatorForType(EmployeeRowsComparator.LAST_NAME);
        Collections.sort(employeeRowStatuses, employeeRowsComparator);
    }

    /**
     * Sort rows by employee salary asc then changes current
     */
    @Command
    @NotifyChange("employeeRowStatuses")
    public void sortBySalary() {
        prepareRowsComparatorForType(EmployeeRowsComparator.SALARY);
        Collections.sort(employeeRowStatuses, employeeRowsComparator);
        System.out.println();
    }

    /**
     * Prepares comparator by needed type
     *
     * @param type 1 - first name
     *             2 - last name
     *             3 - salary
     */
    private void prepareRowsComparatorForType(int type) {
        if (employeeRowsComparator == null)
            employeeRowsComparator = new EmployeeRowsComparator(true, type);
        else if (employeeRowsComparator.getType() == type) {
            employeeRowsComparator.setAsc(!employeeRowsComparator.isAsc());
        } else {
            employeeRowsComparator.setAsc(true);
            employeeRowsComparator.setType(type);
        }
    }
}
