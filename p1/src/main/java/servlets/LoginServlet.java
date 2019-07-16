package servlets;

import services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
	private EmployeeService employeeService = EmployeeService.getInstance();

	public LoginServlet() {}

	// GET
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException {
		System.out.println("GET");
	}

	// POST
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		String jsonResponse = employeeService.login(req);
		pw.write(jsonResponse);
	}
}
