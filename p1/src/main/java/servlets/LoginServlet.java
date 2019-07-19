package servlets;

import org.apache.commons.fileupload.FileItem;
import services.EmployeeService;
import utilities.MultipartFormUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class LoginServlet extends HttpServlet {
	private EmployeeService employeeService = EmployeeService.getInstance();

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
		List<FileItem> items = MultipartFormUtility.getItems(req);

		Map<String,String> data = MultipartFormUtility.parseFormData(items);
		String email = data.get("email");
		String password = data.get("password");
		String json = employeeService.login(email, password);

		PrintWriter pw = res.getWriter();
		pw.write(json);
	}
}
