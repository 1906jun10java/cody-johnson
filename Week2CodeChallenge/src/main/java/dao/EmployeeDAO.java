package dao;

import beans.Employee;

import java.sql.SQLException;

public interface EmployeeDAO {
    void insertEmployee(Employee e) throws SQLException;
    Employee getEmployee(int id) throws SQLException;
    String getAvgDeptSalary(int deptId) throws SQLException;
}
