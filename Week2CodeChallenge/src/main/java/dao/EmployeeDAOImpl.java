package dao;

import beans.Employee;
import services.ConnectionService;

import java.sql.*;
import java.text.DecimalFormat;

public class EmployeeDAOImpl implements EmployeeDAO {
    private static ConnectionService cs = ConnectionService.getInstance();
    private static Connection connection = cs.getConnection();
    private static DecimalFormat df = new DecimalFormat("#.00");

    @Override
    public void insertEmployee(Employee e) throws SQLException {
        String sql = "{ call INSERT_EMPLOYEE(?,?,?,?,?,?)";
        CallableStatement stmt = connection.prepareCall(sql);
        stmt.setNull(1, Types.VARCHAR);
        stmt.setString(2, e.getEmployee_firstname());
        stmt.setString(3, e.getEmployee_lastname());
        stmt.setString(4, e.getEmployee_email());
        stmt.setDouble(5, e.getSalary());
        stmt.setInt(6, e.getDepartment_id());
        stmt.execute();
    }

    @Override
    public Employee getEmployee(int id) throws SQLException {
        String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        Employee e = new Employee();
        if (!rs.isBeforeFirst()) {
            return null;
        }
        while (rs.next()) {
            e.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
            e.setEmployee_firstname(rs.getString("EMP_FIRSTNAME"));
            e.setEmployee_lastname(rs.getString("EMP_LASTNAME"));
            e.setEmployee_email(rs.getString("EMP_EMAIL"));
            e.setSalary(rs.getDouble("SALARY"));
            e.setDepartment_id(rs.getInt("DEPARTMENT_ID"));
        }

        return e;
    }

    @Override
    public String getAvgDeptSalary(int deptId) throws SQLException {
        String sql = "SELECT * FROM EMPLOYEE INNER JOIN DEPARTMENT " +
                "ON EMPLOYEE.DEPARTMENT_ID = DEPARTMENT.DEPARTMENT_ID " +
                "WHERE EMPLOYEE.DEPARTMENT_ID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, deptId);

        ResultSet rs = stmt.executeQuery();
        int count = 0;
        double salary = 0.0;
        String deptName = "";
        if (!rs.isBeforeFirst()) {
            return null;
        }
        while (rs.next()) {
            deptName = rs.getString("DEPARTMENT_NAME");
            salary += rs.getDouble("SALARY");
            count++;
        }

        double avg = salary/(double)count;
        return (
            "Department: " + deptName + "\n" +
            "Average Salary: $" + df.format(avg) + "\n"
        );
    }
}
