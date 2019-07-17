package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import daos.EmployeeDaoImpl;
import models.Employee;
import utilities.RequestUtility;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class EmployeeService {
	private static EmployeeService instance;
	private static EmployeeDaoImpl edi = new EmployeeDaoImpl();

	// Return singleton instance
	public static synchronized EmployeeService getInstance() {
		if (instance == null) {
			instance  = new EmployeeService();
		}
		return instance;
	}

	// Check user credentials, returns a JSON string
	public String login(HttpServletRequest req) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		Employee e1 = null;
		Employee e2 = null;
		try {
			String json = RequestUtility.bodyToJson(req);
			e1 = om.readValue(json, Employee.class);
			e2 = edi.getEmployee(e1.getEmail());
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}

		if (e2 == null) {
			return ("{\"error\":" + "\"Invalid email or password\"}");
		}
		if (e1.getPassword().equals(e2.getPassword())) {
			return om.writeValueAsString(e2);
		} else {
			return ("{\"error\":" + "\"Invalid email or password\"}");
		}
	}
}
