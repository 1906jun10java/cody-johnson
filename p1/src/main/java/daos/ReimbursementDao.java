package daos;

import models.Reimbursement;

import java.sql.SQLException;
import java.util.List;

public interface ReimbursementDao {
	Reimbursement getReimbursement(int id) throws SQLException;
	void addReimbursement(Reimbursement r) throws SQLException;
	void updateReimbursement(Reimbursement r) throws SQLException;
	List<Reimbursement> getReimbursements(int eId) throws SQLException;
}
