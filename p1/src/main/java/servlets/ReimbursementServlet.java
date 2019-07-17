package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReimbursementServlet extends HttpServlet {
	// GET
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException {
		req.getRequestDispatcher("/html/reimbursement.html").forward(req, res);
	}

	// PUT
	protected void doPut(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

	}

	// POST
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

	}
}
