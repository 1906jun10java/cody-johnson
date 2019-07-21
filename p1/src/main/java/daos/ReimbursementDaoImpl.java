package daos;

import models.Reimbursement;
import utilities.ConnectionUtility;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDaoImpl implements ReimbursementDao {
	private static ConnectionUtility cu = ConnectionUtility.getInstance();
	private static Connection connection = cu.getConnection();

	@Override
	public Reimbursement getReimbursement(int id) throws SQLException {
		String sql = "SELECT * FROM REIMBURSEMENT WHERE R_ID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();
		if (!rs.isBeforeFirst()) {
			return null;
		}

		Reimbursement r = new Reimbursement();
		while (rs.next()) {
			r = populateData(rs);
		}
		return r;
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
		String sql = "SELECT * FROM REIMBURSEMENT WHERE E_ID = ? ORDER BY R_UNIX_TS";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, eId);

		ResultSet rs = stmt.executeQuery();
		if (!rs.isBeforeFirst()) {
			return null;
		}

		List<Reimbursement> rl = new ArrayList<>();
		while (rs.next()) {
			Reimbursement r = populateData(rs);
			rl.add(r);
		}
		return rl;
	}

	private Reimbursement populateData(ResultSet rs)
	throws SQLException {
		Reimbursement r = new Reimbursement();
		r.setId(rs.getInt("R_ID"));
		r.setEmployeeId(rs.getInt("E_ID"));
		r.setTypeId(rs.getInt("R_TYPE_ID"));
		r.setStatusId(rs.getInt("R_STATUS_ID"));
		r.setAmount(BigDecimal.valueOf(rs.getDouble("R_AMOUNT")));
		r.setUnixTs(rs.getLong("R_UNIX_TS"));
		r.setDescription(rs.getString("R_DESCRIPTION"));
		r.setReceiptImgFile(rs.getBytes("R_RECEIPT_IMG"));
		return r;
	}
}
