import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import daos.CredentialDaoImpl;
import daos.EmployeeDaoImpl;
import models.Credential;
import models.Employee;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import services.EmployeeService;

import java.sql.SQLException;

public class p1Tests {
	private static EmployeeService es;
	private static CredentialDaoImpl cdi;
	private static EmployeeDaoImpl edi;

	@BeforeClass
	public static void beforeClass() {
		es = EmployeeService.getInstance();
		cdi = new CredentialDaoImpl();
		edi = new EmployeeDaoImpl();
	}

	@Test
	public void testCredential() throws SQLException {
		Employee e = edi.getEmployee(45);
		Credential c = cdi.getCredential(e.getEmail());
		Assert.assertEquals(e.getId(), c.geteId());
	}

	@Test
	public void testLogin() throws SQLException, JsonProcessingException {
		Employee e = edi.getEmployee(45);
		ObjectMapper om = new ObjectMapper();
		String e1 = om.writeValueAsString(e);

		Credential c = cdi.getCredential(e.getEmail());
		String e2 = es.login(e.getEmail(), c.getPassword());

		Assert.assertEquals(e1, e2);
	}
}
