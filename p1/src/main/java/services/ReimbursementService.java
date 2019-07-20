package services;

import daos.ReimbursementDaoImpl;
import models.Reimbursement;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import utilities.MultipartFormUtility;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ReimbursementService {
	private static ReimbursementService instance;
	private static ReimbursementDaoImpl rdi = new ReimbursementDaoImpl();

	// Return singleton instance
	public static synchronized ReimbursementService getInstance() {
		if (instance == null) {
			instance  = new ReimbursementService();
		}
		return instance;
	}

	// Handle a post request to /reimbursement/create
	public String handlePostCreate(HttpServletRequest req) {
		boolean isMultiPart = ServletFileUpload.isMultipartContent(req);
		if (!isMultiPart) {
			return ("{\"error\":" + "\"Error processing data\"}");
		}

		// Get items from formData
		List<FileItem> items = MultipartFormUtility.getItems(req);

		// Map item keys to item values
		Map<String,String> data = MultipartFormUtility.parseFormData(items);

		// Parse file from form
		byte[] file = MultipartFormUtility.parseFile(items);

		// Store in database
		Reimbursement r = dataToReimbursement(data, file);
		try {
			rdi.addReimbursement(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ("{\"message\":" + "\"Submission successful\"}");
	}

	// Convert data to reimbursement object
	private Reimbursement dataToReimbursement(Map<String,String> d, byte[] f) {
		Reimbursement r = new Reimbursement();
		r.setEmployeeId(Integer.parseInt(d.get("eId")));
		r.setTypeId(Integer.parseInt(d.get("type")));
		r.setStatusId(1);
		r.setAmount(BigDecimal.valueOf(Double.parseDouble(d.get("amount"))));
		r.setUnixTs(System.currentTimeMillis()/1000L);
		r.setDescription(d.get("description"));
		r.setReceiptImgFile(f);

		return r;
	}

	// Test for viewing bytes of files in hex format
	/*private String byteToHex(byte num) {
		char[] hexDigits = new char[2];
		hexDigits[0] = Character.forDigit((num >> 4) & 0xf, 16);
		hexDigits[1] = Character.forDigit((num & 0xf), 16);
		return new String(hexDigits);
	}*/
}
