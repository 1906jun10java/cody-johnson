package servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import services.ReimbursementService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
		System.out.println(uri);

		if (uri.equals("/reimbursement/create")) {
			boolean isMultiPart = ServletFileUpload.isMultipartContent(req);
			if (!isMultiPart) {
				System.out.println("Form Error");
			}

			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletContext servletContext = this.getServletConfig().getServletContext();
			File repository = (File)servletContext.getAttribute("javax.servlet.context.tempdir");
			factory.setRepository(repository);
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = new ArrayList<>();
			try {
				items = upload.parseRequest(req);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}

			Map<String,String> formData = new HashMap<>();
			String fileName = null;
			String fileContentType = null;
			boolean fileIsInMemory = false;
			long fileSize = 0L;
			byte[] file = null;

			for (FileItem item : items) {
				if (item.isFormField()) {
					formData.put(item.getFieldName(), item.getString());
				}
				if (!item.isFormField()){
					fileName = item.getName();
					fileContentType = item.getContentType();
					fileIsInMemory = item.isInMemory();
					fileSize = item.getSize();
					file = item.get();
				}
			}

			for (String key : formData.keySet()) {
				System.out.println(key + ": " + formData.get(key));
			}
			System.out.println(fileName);
			System.out.println(fileContentType);
			System.out.println(fileIsInMemory);
			System.out.println(fileSize);
			if (file != null) {
				for (byte b : file) {
					System.out.print(rs.byteToHex(b) + " ");
				}
			}
		}
	}
}
