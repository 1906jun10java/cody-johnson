package servlets;

import services.ReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ReimbursementServlet extends HttpServlet {
	private static ReimbursementService rs = ReimbursementService.getInstance();

	// GET
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException {
		String uri = req.getRequestURI();

		if (uri.equals("/reimbursement/create")) {
			String html = "/html/createReimbursement.html";
			req.getRequestDispatcher(html).forward(req, res);
		}
	}

	// POST
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
	throws IOException {
		// Get requested endpoint
		String uri = req.getRequestURI();

		if (uri.equals("/reimbursement/create")) {
			String json = rs.handlePostCreate(req);
			PrintWriter pw = res.getWriter();
			pw.write(json);
		}
	}
}
