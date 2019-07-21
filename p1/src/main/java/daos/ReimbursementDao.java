package daos;

import models.Reimbursement;

import java.sql.SQLException;
import java.util.List;

public interface ReimbursementDao {
	Reimbursement getReimbursement(int id) throws SQLException;
	void addReimbursement(Reimbursement r) throws SQLException;
	void updateReimbursement(int rId, int statusId) throws SQLException;
	List<Reimbursement> getReimbursements(int eId) throws SQLException;
	List<Reimbursement> getSubordinateReimbursements(int eId) throws SQLException;
}
