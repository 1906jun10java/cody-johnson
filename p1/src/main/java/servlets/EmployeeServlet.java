package servlets;

import services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmployeeServlet extends HttpServlet {
	private EmployeeService employeeService = EmployeeService.getInstance();

	// GET
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException {
		String json = employeeService.getEmployee(req);
		res.getWriter().write(json);
	}
}
