package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import daos.CredentialDaoImpl;
import daos.EmployeeDaoImpl;
import models.Credential;
import models.Employee;
import org.apache.commons.fileupload.FileItem;
import utilities.MultipartFormUtility;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

	// Return an employee by ID
	public String getEmployee(HttpServletRequest req)
	throws JsonProcessingException {
		String eIdStr = req.getParameter("eId");
		if (eIdStr == null) {
			return ("{\"error\":" + "\"User doesn't exist\"}");
		}
		int eId = Integer.parseInt(eIdStr);

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
	public String login(HttpServletRequest req) throws JsonProcessingException {
		// Parse form data
		List<FileItem> items = MultipartFormUtility.getItems(req);
		Map<String,String> data = MultipartFormUtility.parseFormData(items);
		String email = data.get("email");
		String password = data.get("password");

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
		ObjectMapper om = new ObjectMapper();
		if (e != null) {
			return om.writeValueAsString(e);
		} else {
			return ("{\"error\":" + "\"Invalid email or password\"}");
		}
	}
}
