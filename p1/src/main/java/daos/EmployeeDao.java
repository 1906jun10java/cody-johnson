package daos;

import models.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {
	Employee getEmployee(int id) throws SQLException;
	Employee getEmployee(String email) throws SQLException;
	List<Employee> getEmployees() throws SQLException;
}
