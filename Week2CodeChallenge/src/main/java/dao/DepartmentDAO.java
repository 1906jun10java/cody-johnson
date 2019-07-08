package dao;

import beans.Department;

import java.sql.SQLException;

public interface DepartmentDAO {
    void insertDepartment(Department d) throws SQLException;
    Department getDepartment(int id) throws SQLException;
}
