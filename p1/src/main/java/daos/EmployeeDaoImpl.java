package daos;

import models.Employee;
import utilities.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
	private static ConnectionUtility cu = ConnectionUtility.getInstance();

	@Override
	public Employee getEmployee(int id) throws SQLException {
		String sql = "SELECT * FROM EMPLOYEE WHERE E_ID = ?";
		Connection connection = cu.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();
		Employee e = null;
		if (!rs.isBeforeFirst()) {
			return null;
		}
		while (rs.next()) {
			e = parseResultSet(rs);
		}
		return e;
	}
	@Override
	public Employee getEmployee(String email) throws SQLException {
		String sql = "SELECT * FROM EMPLOYEE WHERE E_EMAIL = ?";
		Connection connection = cu.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, email);

		ResultSet rs = stmt.executeQuery();
		Employee e = null;
		while (rs.next()) {
			e = parseResultSet(rs);
		}
		return e;
	}

	@Override
	public List<Employee> getEmployees() throws SQLException {
		String sql = "SELECT * FROM EMPLOYEE";
		Connection connection = cu.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();
		if (!rs.isBeforeFirst()) {
			return null;
		}
		List<Employee> el = new ArrayList<>();
		while (rs.next()) {
			el.add(parseResultSet(rs));
		}
		return el;
	}

	// Returns a single Employee from the result set
	private Employee parseResultSet(ResultSet rs) throws SQLException {
		Employee e = new Employee();
		e.setId(rs.getInt("E_ID"));
		e.setLevel(rs.getInt("E_LEVEL"));
		e.setEmail(rs.getString("E_EMAIL"));
		e.setFirstName(rs.getString("E_FIRST_NAME"));
		e.setLastName(rs.getString("E_LAST_NAME"));
		e.setReportsTo(rs.getInt("E_REPORTS_TO"));
		return e;
	}
}
