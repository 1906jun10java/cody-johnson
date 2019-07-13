package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends HttpServlet {
	public IndexServlet() {}

	// GET
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		res.sendRedirect("index.html");
	}
}
