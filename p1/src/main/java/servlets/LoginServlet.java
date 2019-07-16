package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import daos.EmployeeDaoImpl;
import models.Employee;
import utilities.RequestUtility;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -6193286177757516310L;

	public LoginServlet() {}

	// GET
	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("GET");
	}

	// POST
	protected void doPost(HttpServletRequest req, HttpServletResponse res) {
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		try {
			PrintWriter pw = res.getWriter();
			ObjectMapper om = new ObjectMapper();
			String json = RequestUtility.bodyToJson(req);

			Employee e1 = om.readValue(json, Employee.class);
			Employee e2 = edi.getEmployee(e1.getEmail());

			if (e2 == null) {
				res.setStatus(401);
				pw.write("{\"error\":" + "\"Invalid email or password\"}");
				return;
			}
			if (e1.getPassword().equals(e2.getPassword())) {
				res.setStatus(200);
				pw.write("{\"id\":" + "\"" + e2.getId() + "\"}");
			} else {
				res.setStatus(401);
				pw.write("{\"error\":" + "\"Invalid email or password\"}");
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
}
