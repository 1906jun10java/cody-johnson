package daos;

import models.Employee;

import java.sql.SQLException;

public interface EmployeeDao {
	Employee getEmployee(String email) throws SQLException;
}
