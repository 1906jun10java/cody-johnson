package driver;

import beans.Department;
import beans.Employee;
import dao.DepartmentDAOImpl;
import dao.EmployeeDAOImpl;

import java.sql.SQLException;

public class Driver {
    private static DepartmentDAOImpl ddi = new DepartmentDAOImpl();
    private static EmployeeDAOImpl edi = new EmployeeDAOImpl();

    // Main
    public static void main(String[] args) {
        //insertDepartments();
        //insertEmployees();

        try {
            System.out.println(edi.getAvgDeptSalary(1));
            System.out.println(edi.getAvgDeptSalary(2));
            System.out.println(edi.getAvgDeptSalary(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Seed departments
    private static void insertDepartments() {
        Department d0 = new Department("HR");
        Department d1 = new Department("Sales");
        Department d2 = new Department("R&D");
        try {
            ddi.insertDepartment(d0);
            ddi.insertDepartment(d1);
            ddi.insertDepartment(d2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Seed employees
    private static void insertEmployees() {
        Employee e0 = new Employee(
            "Timothy", "Barnett", "tbarnett@gmail.com", 35000.00, 1
        );
        Employee e1 = new Employee(
            "Eric", "Moody", "emoody@gmail.com", 43000.00, 1
        );
        Employee e2 = new Employee(
            "Debra", "Lloyd", "dlloyd@gmail.com", 41000.00, 2
        );
        Employee e3 = new Employee(
            "Barbara", "Duncan", "bduncan@gmail.com", 48000.00, 2
        );
        Employee e4 = new Employee(
            "Lee", "James", "ljames@gmail.com", 65000.00, 3
        );
        Employee e5 = new Employee(
            "Van", "Rodgers", "vrodgers@gmail.com", 61000.00, 3
        );
        try {
            edi.insertEmployee(e0);
            edi.insertEmployee(e1);
            edi.insertEmployee(e2);
            edi.insertEmployee(e3);
            edi.insertEmployee(e4);
            edi.insertEmployee(e5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
