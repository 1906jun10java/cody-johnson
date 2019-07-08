package beans;

public class Employee {
    private Integer employee_id;
    private String employee_firstname;
    private String employee_lastname;
    private String employee_email;
    private double salary;
    private int department_id;

    public Employee() {}

    public Employee(String employee_firstname, String employee_lastname,
    String employee_email, double salary, int department_id) {
        this.employee_firstname = employee_firstname;
        this.employee_lastname = employee_lastname;
        this.employee_email = employee_email;
        this.salary = salary;
        this.department_id = department_id;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }
    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }
    public String getEmployee_firstname() {
        return employee_firstname;
    }
    public void setEmployee_firstname(String employee_firstname) {
        this.employee_firstname = employee_firstname;
    }
    public String getEmployee_lastname() {
        return employee_lastname;
    }
    public void setEmployee_lastname(String employee_lastname) {
        this.employee_lastname = employee_lastname;
    }
    public String getEmployee_email() {
        return employee_email;
    }
    public void setEmployee_email(String employee_email) {
        this.employee_email = employee_email;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public int getDepartment_id() {
        return department_id;
    }
    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }
}
