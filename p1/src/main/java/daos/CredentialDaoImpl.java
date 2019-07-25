package daos;

import models.Credential;
import utilities.ConnectionUtility;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CredentialDaoImpl implements CredentialDao {
	private static ConnectionUtility cu = ConnectionUtility.getInstance();

	@Override
	public Credential getCredential(String email) throws SQLException {
		String sql = "SELECT E.E_ID, C.E_PASSWORD\n" +
		"FROM EMPLOYEE E\n" +
		"INNER JOIN CREDENTIAL C\n" +
		"ON E.E_ID = C.E_ID\n" +
		"WHERE E_EMAIL = ?";
		Connection connection = cu.getConnection();
		CallableStatement stmt = connection.prepareCall(sql);
		stmt.setString(1, email);

		ResultSet rs = stmt.executeQuery();
		Credential c = new Credential();
		if (!rs.isBeforeFirst()) {
			return null;
		}
		while (rs.next()) {
			c.seteId(rs.getInt("E_ID"));
			c.setPassword(rs.getString("E_PASSWORD"));
		}
		return c;
	}
}
