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
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException {
		req.getRequestDispatcher("index.html").forward(req, res);
	}

	// POST
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		String jsonResponse = employeeService.login(req);
		pw.write(jsonResponse);
	}
}
