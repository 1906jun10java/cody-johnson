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
		PrintWriter pw = res.getWriter();

		// Get requested endpoint
		String uri = req.getRequestURI();
		if (uri.equals("/reimbursement")) {
			String json = rs.getReimbursement(req);
			pw.write(json);
		}
		else if (uri.equals("/reimbursement/view")) {
			String html = "/html/viewReimbursement.html";
			req.getRequestDispatcher(html).forward(req, res);
		}
		else if (uri.equals("/reimbursement/employee")) {
			String json = rs.getEmployeeReimbursements(req);
			pw.write(json);
		}
		else if (uri.equals("/reimbursement/create")) {
			String html = "/html/createReimbursement.html";
			req.getRequestDispatcher(html).forward(req, res);
		}
		else if (uri.equals("/reimbursement/subordinatesof")) {
			String json = rs.getSubordinateReimbursements(req);
			pw.write(json);
		}
	}

	// POST
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
	throws IOException {
		PrintWriter pw = res.getWriter();

		// Get requested endpoint
		String uri = req.getRequestURI();
		if (uri.equals("/reimbursement/create")) {
			String json = rs.addReimbursement(req);
			pw.write(json);
		}
		else if (uri.equals("/reimbursement/update")) {
			String json = rs.updateReimbursement(req);
			pw.write(json);
		}
	}
}
