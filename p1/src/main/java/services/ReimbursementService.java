package services;

public class ReimbursementService {
	private static ReimbursementService instance;

	// Return singleton instance
	public static synchronized ReimbursementService getInstance() {
		if (instance == null) {
			instance  = new ReimbursementService();
		}
		return instance;
	}

	// Test for viewing bytes of files (e.g. JPEG begins with "ff d8" and ends
	// with "ff d9)
	public String byteToHex(byte num) {
		char[] hexDigits = new char[2];
		hexDigits[0] = Character.forDigit((num >> 4) & 0xf, 16);
		hexDigits[1] = Character.forDigit((num & 0xf), 16);
		return new String(hexDigits);
	}

	// Handle a post request to /reimbursement/create
	//public String handlePostCreate(HttpServletRequest req) {}
}
