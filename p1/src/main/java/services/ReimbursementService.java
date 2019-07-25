package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import daos.ReimbursementDaoImpl;
import models.Reimbursement;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import utilities.MultipartFormUtility;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ReimbursementService {
	private static ReimbursementService instance;
	private static ReimbursementDaoImpl rdi = new ReimbursementDaoImpl();
	private final static Logger logger = Logger.getLogger(ReimbursementService.class);

	// Return singleton instance
	public static synchronized ReimbursementService getInstance() {
		if (instance == null) {
			instance  = new ReimbursementService();
		}
		return instance;
	}

	// Return JSON of reimbursement
	public String getReimbursement(int id)
	throws JsonProcessingException {
		Reimbursement r = null;
		try {
			r = rdi.getReimbursement(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ObjectMapper om = new ObjectMapper();
		if (r != null) {
			return om.writeValueAsString(r);
		}

		return ("{\"error\":" + "\"Error processing data\"}");
	}

	// Return JSON of subordinate reimbursements
	public String getSubordinateReimbursements(int eId)
	throws JsonProcessingException {
		List<Reimbursement> rl = null;
		try {
			rl = rdi.getSubordinateReimbursements(eId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Return JSON
		ObjectMapper om = new ObjectMapper();
		if (rl == null) {
			return ("{\"status\":" + "\"Nothing to display\"}");
		}
		return om.writeValueAsString(rl);
	}

	// Return JSON of employee reimbursements
	public String getEmployeeReimbursements(int eId)
	throws JsonProcessingException {
		// Get employee reimbursements
		List<Reimbursement> rl = null;
		try {
			rl = rdi.getReimbursements(eId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Return JSON
		ObjectMapper om = new ObjectMapper();
		if (rl == null) {
			return ("{\"status\":" + "\"Nothing to display\"}");
		}
		return om.writeValueAsString(rl);
	}

	// Update reimbursement status
	public String updateReimbursement(int rId, int statusId) {
		// Update reimbursement
		try {
			rdi.updateReimbursement(rId, statusId);
		} catch (SQLException e) {
			e.printStackTrace();
			return ("{\"error\":" + "\"Update unsuccessful\"}");
		}
		logger.info("Reimbursement ID " + rId + " updated");

		return ("{\"status\":" + "\"Update successful\"}");
	}

	// Handle a post request to /reimbursement/create
	public String addReimbursement(List<FileItem> items) {
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
		logger.info("Reimbursement created by ID " + r.getEmployeeId());

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
