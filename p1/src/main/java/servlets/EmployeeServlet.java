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
		// Get requested endpoint
		String uri = req.getRequestURI();
		if (uri.equals("/employee")) {
			String eIdStr = req.getParameter("eId");
			String json;
			if (eIdStr != null) {
				json = employeeService.getEmployee(Integer.parseInt(eIdStr));
			} else {
				json = "{\"error\":" + "\"User doesn't exist\"}";
			}
			res.getWriter().write(json);
		}
		else if (uri.equals("/employee/all")) {
			String json = employeeService.getEmployees();
			res.getWriter().write(json);
		}
		else if (uri.equals("/employee/profile")) {
			String html = "/html/profile.html";
			req.getRequestDispatcher(html).forward(req, res);
		}
		else if (uri.equals("/employee/directory")) {
			String html = "/html/directory.html";
			req.getRequestDispatcher(html).forward(req, res);
		}
	}
}
