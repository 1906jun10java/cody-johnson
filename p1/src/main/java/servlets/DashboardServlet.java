package servlets;

import services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DashboardServlet extends HttpServlet {
	private EmployeeService employeeService = EmployeeService.getInstance();

	public DashboardServlet() {}

	// GET
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException {
		req.getRequestDispatcher("/html/dashboard.html").forward(req, res);
	}
}
