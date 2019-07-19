package daos;

import models.Credential;
import services.ConnectionService;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CredentialDaoImpl implements CredentialDao {
	private static ConnectionService cs = ConnectionService.getInstance();
	private static Connection connection = cs.getConnection();

	@Override
	public Credential getPassword(String email) throws SQLException {
		String sql = 	"SELECT E.E_ID, C.E_PASSWORD\n" +
						"FROM EMPLOYEE E\n" +
						"INNER JOIN CREDENTIAL C\n" +
						"ON E.E_ID = C.E_ID\n" +
						"WHERE E_EMAIL = ?";
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
