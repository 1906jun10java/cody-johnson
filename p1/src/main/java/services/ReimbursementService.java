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
}
