package servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import services.ReimbursementService;
import utilities.MultipartFormUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReimbursementServlet extends HttpServlet {
	private static ReimbursementService rs = ReimbursementService.getInstance();

	// GET
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException {
		// Get requested endpoint
		String uri = req.getRequestURI();
		if (uri.equals("/reimbursement")) {
			// Get reimbursement ID parameter from request
			String param = req.getParameter("id");
			String json;
			if (param != null ) {
				json = rs.getReimbursement(Integer.parseInt(param));
			} else {
				json = "{\"error\":" + "\"Error processing data\"}";
			}
			res.getWriter().write(json);
		}
		else if (uri.equals("/reimbursement/view")) {
			String html = "/html/viewReimbursement.html";
			req.getRequestDispatcher(html).forward(req, res);
		}
		else if (uri.equals("/reimbursement/employee")) {
			// Get employee ID parameter from request
			String param = req.getParameter("eId");
			String json;
			if (param != null) {
				json = rs.getEmployeeReimbursements(Integer.parseInt(param));
			} else {
				json = "{\"error\":" + "\"User doesn't exist\"}";
			}
			res.getWriter().write(json);
		}
		else if (uri.equals("/reimbursement/create")) {
			String html = "/html/createReimbursement.html";
			req.getRequestDispatcher(html).forward(req, res);
		}
		else if (uri.equals("/reimbursement/subordinatesof")) {
			// Get employee ID parameter from request
			String json;
			String param = req.getParameter("eId");
			if (param != null) {
				json = rs.getSubordinateReimbursements(Integer.parseInt(param));
			} else {
				json = "{\"error\":" + "\"User doesn't exist\"}";
			}
			res.getWriter().write(json);
		}
	}

	// POST
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
	throws IOException {
		// Get requested endpoint
		String uri = req.getRequestURI();
		String json;
		if (uri.equals("/reimbursement/create")) {
			// Check if form is multi-part
			boolean isMultiPart = ServletFileUpload.isMultipartContent(req);
			if (!isMultiPart) {
				json = "{\"error\":" + "\"Error processing data\"}";
				res.getWriter().write(json);
				return;
			}

			// Get items from formData
			List<FileItem> items = MultipartFormUtility.getItems(req);

			json = rs.addReimbursement(items);
			res.getWriter().write(json);
		}
		else if (uri.equals("/reimbursement/update")) {
			// Get reimbursement and status IDs from request
			String rIdStr = req.getParameter("rId");
			String statusIdStr = req.getParameter("statusId");
			if (rIdStr != null && statusIdStr != null) {
				int rId = Integer.parseInt(rIdStr);
				int statusId = Integer.parseInt(statusIdStr);
				json = rs.updateReimbursement(rId, statusId);
			} else {
				json = "{\"error\":" + "\"User doesn't exist\"}";
			}
			res.getWriter().write(json);
		}
	}
}
