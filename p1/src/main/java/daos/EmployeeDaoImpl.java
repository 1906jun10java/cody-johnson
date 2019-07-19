package daos;

import models.Employee;
import utilities.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoImpl implements EmployeeDao {
	private static ConnectionUtility cs = ConnectionUtility.getInstance();
	private static Connection connection = cs.getConnection();

	@Override
	public Employee getEmployee(int id) throws SQLException {
		String sql = "SELECT * FROM EMPLOYEE WHERE E_ID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();
		return parseResultSet(rs);
	}
	@Override
	public Employee getEmployee(String email) throws SQLException {
		String sql = "SELECT * FROM EMPLOYEE WHERE E_EMAIL = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, email);

		ResultSet rs = stmt.executeQuery();
		return parseResultSet(rs);
	}

	// Returns a single Employee from the result set
	private Employee parseResultSet(ResultSet rs) throws SQLException {
		Employee e = new Employee();
		if (!rs.isBeforeFirst()) {
			return null;
		}
		while (rs.next()) {
			e.setId(rs.getInt("E_ID"));
			e.setLevel(rs.getInt("E_LEVEL"));
			e.setEmail(rs.getString("E_EMAIL"));
			e.setFirstName(rs.getString("E_FIRST_NAME"));
			e.setLastName(rs.getString("E_LAST_NAME"));
			e.setReportsTo(rs.getInt("E_REPORTS_TO"));
		}

		return e;
	}
}
