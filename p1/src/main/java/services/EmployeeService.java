package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import daos.CredentialDaoImpl;
import daos.EmployeeDaoImpl;
import models.Credential;
import models.Employee;

import java.sql.SQLException;

public class EmployeeService {
	private static EmployeeService instance;
	private static EmployeeDaoImpl edi = new EmployeeDaoImpl();
	private static CredentialDaoImpl cdi = new CredentialDaoImpl();

	// Return singleton instance
	public static synchronized EmployeeService getInstance() {
		if (instance == null) {
			instance  = new EmployeeService();
		}
		return instance;
	}

	// Check user credentials, returns a JSON string
	public String login(String email, String password)
	throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();

		// Get matching credential from db
		Credential c = null;
		try {
			c = cdi.getPassword(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Validate credential
		Employee e = null;
		if (c != null && c.getPassword().equals(password)) {
			try {
				e = edi.getEmployee(email);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} else {
			return ("{\"error\":" + "\"Invalid email or password\"}");
		}

		// Return JSON
		if (e != null) {
			return om.writeValueAsString(e);
		} else {
			return ("{\"error\":" + "\"Invalid email or password\"}");
		}
	}
}
