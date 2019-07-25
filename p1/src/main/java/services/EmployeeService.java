package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import daos.CredentialDaoImpl;
import daos.EmployeeDaoImpl;
import models.Credential;
import models.Employee;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
	private static EmployeeService instance;
	private static EmployeeDaoImpl edi = new EmployeeDaoImpl();
	private static CredentialDaoImpl cdi = new CredentialDaoImpl();
	private final static Logger logger = Logger.getLogger(EmployeeService.class);

	// Return singleton instance
	public static synchronized EmployeeService getInstance() {
		if (instance == null) {
			instance  = new EmployeeService();
		}
		return instance;
	}

	// Return an employee by ID
	public String getEmployee(int eId) throws JsonProcessingException {
		// Get employee
		Employee e = null;
		try {
			e = edi.getEmployee(eId);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		if (e == null) {
			return ("{\"error\":" + "\"User doesn't exist\"}");
		}

		ObjectMapper om = new ObjectMapper();
		return om.writeValueAsString(e);
	}

	// Get all employees
	public String getEmployees() throws JsonProcessingException {
		List<Employee> el = null;
		try {
			el = edi.getEmployees();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ObjectMapper om = new ObjectMapper();
		if (el != null) {
			return om.writeValueAsString(el);
		} else {
			return ("{\"error\":" + "\"Error getting employees\"}");
		}
	}

	// Check user credentials, returns a JSON string
	public String login(String email, String password)
	throws JsonProcessingException {
		// Get matching credential from db
		Credential c = null;
		try {
			c = cdi.getCredential(email);
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
			if (c != null) {
				logger.warn("FAILED LOGIN ATTEMPT FOR ID " + c.geteId());
			}
			return ("{\"error\":" + "\"Invalid email or password\"}");
		}

		// Return JSON
		ObjectMapper om = new ObjectMapper();
		if (e != null) {
			logger.info("ID " + e.getId() + " has logged in.");
			return om.writeValueAsString(e);
		} else {
			return ("{\"error\":" + "\"Invalid email or password\"}");
		}
	}
}
