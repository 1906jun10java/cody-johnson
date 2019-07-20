package daos;

import models.Reimbursement;
import utilities.ConnectionUtility;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReimbursementDaoImpl implements ReimbursementDao {
	private static ConnectionUtility cu = ConnectionUtility.getInstance();
	private static Connection connection = cu.getConnection();

	@Override
	public Reimbursement getReimbursement(int id) throws SQLException {
		return null;
	}

	@Override
	public void addReimbursement(Reimbursement r) throws SQLException {
		String sql = "{ call INSERT_REIMBURSEMENT(?,?,?,?,?,?,?)";
		CallableStatement stmt = connection.prepareCall(sql);
		stmt.setInt(1, r.getEmployeeId());
		stmt.setInt(2, r.getTypeId());
		stmt.setInt(3, r.getStatusId());
		stmt.setDouble(4, r.getAmount().doubleValue());
		stmt.setLong(5, r.getUnixTs());
		stmt.setString(6, r.getDescription());
		Blob blob = connection.createBlob();
		blob.setBytes(1, r.getReceiptImgFile());
		stmt.setBlob(7, blob);

		stmt.execute();
	}

	@Override
	public void updateReimbursement(Reimbursement r) throws SQLException {

	}

	@Override
	public List<Reimbursement> getReimbursements(int eId) throws SQLException {
		return null;
	}
}
