package dao;

import beans.Department;
import services.ConnectionService;

import java.sql.*;

public class DepartmentDAOImpl implements DepartmentDAO {
    private static ConnectionService cs = ConnectionService.getInstance();
    private static Connection connection = cs.getConnection();

    @Override
    public void insertDepartment(Department d) throws SQLException {
        String sql = "{ call INSERT_DEPARTMENT(?,?)";
        CallableStatement stmt = connection.prepareCall(sql);
        stmt.setNull(1, Types.VARCHAR);
        stmt.setString(2, d.getDepartment_name());
        stmt.execute();
    }

    @Override
    public Department getDepartment(int id) throws SQLException {
        String sql = "SELECT * FROM DEPARTMENT WHERE DEPARTMENT_ID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        Department d = new Department();
        if (!rs.isBeforeFirst()) {
            return null;
        }
        while (rs.next()) {
            d.setDepartment_id(rs.getInt("DEPARTMENT_ID"));
            d.setDepartment_name(rs.getString(("DEPARTMENT_NAME")));
        }

        return d;
    }
}
