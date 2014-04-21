package demo.view.paging.util;

import demo.view.rowediting.EmployeeRow;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Comparator class for EmployeeRow
 * Sorts by type
 *
 * @author Erofeev Danil
 */
public class EmployeeRowsComparator implements Comparator<EmployeeRow>, Serializable {
    /**
     * Type for first name sorting
     */
    public static final int FIRST_NAME = 1;
    /**
     * Type for last name sorting
     */
    public static final int LAST_NAME = 2;
    /**
     * Type for salary sorting
     */
    public static final int SALARY = 3;

    private static final long serialVersionUID = -2127053833562854322L;

    private boolean asc = true;
    private int type = 0;

    public EmployeeRowsComparator(boolean asc, int type) {
        this.asc = asc;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    @Override
    public int compare(EmployeeRow o1, EmployeeRow o2) {
        switch (type) {
            case FIRST_NAME: // Compare First Name
                return o1.getEmployee().getFirstName().compareTo(o2.getEmployee().getFirstName()) * (asc ? 1 : -1);

            case LAST_NAME: // Compare Last Name
                return o1.getEmployee().getLastName().compareTo(o2.getEmployee().getLastName()) * (asc ? 1 : -1);

            case SALARY: // Compare Salary
                return o1.getEmployee().getSalary().compareTo(o2.getEmployee().getSalary()) * (asc ? 1 : -1);
            default:
                // Compare First Name
                return o1.getEmployee().getFirstName().compareTo(o2.getEmployee().getFirstName()) * (asc ? 1 : -1);
        }
    }
}
