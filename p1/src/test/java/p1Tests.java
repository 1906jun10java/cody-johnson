import daos.CredentialDaoImpl;
import daos.EmployeeDaoImpl;
import models.Credential;
import models.Employee;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

public class p1Tests {
	private static CredentialDaoImpl cdi;
	private static EmployeeDaoImpl edi;

	@BeforeClass
	public static void beforeClass() {
		cdi = new CredentialDaoImpl();
		edi = new EmployeeDaoImpl();
	}

	@Test
	public void testCredential() throws SQLException {
		Employee e = edi.getEmployee(45);
		Credential c = cdi.getPassword(e.getEmail());
		Assert.assertEquals(e.getId(), c.geteId());
	}
}
