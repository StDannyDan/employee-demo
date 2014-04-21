package demo.view.rowediting;

import demo.model.Employee;

/**
 * Util class for PagingViewController to implement editing of the rows
 *
 * @author Erofeev Danil
 * @see demo.view.paging.PagingViewController
 */
public class EmployeeRow {

    private Employee employee;
    private boolean editingStatus;

    public EmployeeRow(Employee employee, boolean editingStatus) {
        this.employee = employee;
        this.editingStatus = editingStatus;
    }

    public Employee getEmployee() {
        return employee;
    }

    public boolean getEditingStatus() {
        return editingStatus;
    }

    public void setEditingStatus(boolean editingStatus) {
        this.editingStatus = editingStatus;
    }
}
