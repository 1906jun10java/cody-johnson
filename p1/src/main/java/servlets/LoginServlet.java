package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -6193286177757516310L;

	public LoginServlet() {}

	// GET
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		System.out.println("GET");
	}

	// POST
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		System.out.println("POST");
	}
}