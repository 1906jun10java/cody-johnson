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
import java.util.Map;

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

	// PUT
	protected void doPut(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException {

	}

	// POST
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException {
		String uri = req.getRequestURI();

		if (uri.equals("/reimbursement/create")) {
			boolean isMultiPart = ServletFileUpload.isMultipartContent(req);
			if (!isMultiPart) {
				System.out.println("Form Error");
			}

			List<FileItem> items = MultipartFormUtility.getItems(req);

			Map<String,String> data = MultipartFormUtility.parseFormData(items);
			for (String key : data.keySet()) {
				System.out.println(key + ": " + data.get(key));
			}

			byte[] file = MultipartFormUtility.parseFile(items);
			if (file != null) {
				for (byte b : file) {
					System.out.print(rs.byteToHex(b) + " ");
				}
			}
		}
	}
}
