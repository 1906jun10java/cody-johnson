package daos;

import models.Reimbursement;
import utilities.ConnectionUtility;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDaoImpl implements ReimbursementDao {
	private static ConnectionUtility cu = ConnectionUtility.getInstance();

	@Override
	public Reimbursement getReimbursement(int id) throws SQLException {
		String sql = "SELECT * FROM REIMBURSEMENT R\n" +
		"JOIN REIMBURSEMENT_STATUS S ON R.R_STATUS_ID = S.R_STATUS_ID\n" +
		"JOIN REIMBURSEMENT_TYPE T ON R.R_TYPE_ID = T.R_TYPE_ID\n" +
		"WHERE R_ID = ?";
		Connection connection = cu.getConnection();
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
		Connection connection = cu.getConnection();
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
		String sql = "SELECT * FROM REIMBURSEMENT R\n" +
		"JOIN REIMBURSEMENT_STATUS S ON R.R_STATUS_ID = S.R_STATUS_ID\n" +
		"JOIN REIMBURSEMENT_TYPE T ON R.R_TYPE_ID = T.R_TYPE_ID\n" +
		"WHERE E_ID = ? ORDER BY R_UNIX_TS DESC";
		Connection connection = cu.getConnection();
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

	@Override
	public List<Reimbursement> getSubordinateReimbursements(int eId) throws SQLException {
		String sql = "SELECT * FROM REIMBURSEMENT R\n" +
		"JOIN REIMBURSEMENT_STATUS S ON R.R_STATUS_ID = S.R_STATUS_ID\n" +
		"JOIN REIMBURSEMENT_TYPE T ON R.R_TYPE_ID = T.R_TYPE_ID\n" +
		"WHERE R.E_ID IN (\n" +
		"\tSELECT E_ID FROM EMPLOYEE E1\n" +
		"\tWHERE E1.E_ID IN (\n" +
		"\t\tSELECT E_ID FROM EMPLOYEE E2\n" +
		"\t\tWHERE E2.E_REPORTS_TO IN (\n" +
		"\t\t\tSELECT E_ID FROM EMPLOYEE E3\n" +
		"\t\t\tWHERE E3.E_REPORTS_TO IN (\n" +
		"\t\t\t\tSELECT E_ID FROM EMPLOYEE E4\n" +
		"\t\t\t\tWHERE E4.E_REPORTS_TO = ?\n" +
		"\t\t\t)\n" +
		"\t\t)\n" +
		"\t)\n" +
		"\tUNION\n" +
		"\tSELECT E_ID FROM EMPLOYEE E1\n" +
		"\tWHERE E1.E_ID IN (\n" +
		"\t\tSELECT E_ID FROM EMPLOYEE E2\n" +
		"\t\tWHERE E2.E_REPORTS_TO IN (\n" +
		"\t\t\tSELECT E_ID FROM EMPLOYEE E3\n" +
		"\t\t\tWHERE E3.E_REPORTS_TO = ?\n" +
		"\t\t)\n" +
		"\t)\n" +
		"\tUNION\n" +
		"\tSELECT E_ID FROM EMPLOYEE E1\n" +
		"\tWHERE E1.E_ID IN (\n" +
		"\t\tSELECT E_ID FROM EMPLOYEE E2\n" +
		"\t\tWHERE E2.E_REPORTS_TO = ?\n" +
		"\t)\n" +
		")\n" +
		"ORDER BY R.R_UNIX_TS";
		Connection connection = cu.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, eId);
		stmt.setInt(2, eId);
		stmt.setInt(3, eId);

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
		r.setStatusName(rs.getString("R_STATUS_NAME"));
		r.setTypeName(rs.getString("R_TYPE_NAME"));
		return r;
	}
}
