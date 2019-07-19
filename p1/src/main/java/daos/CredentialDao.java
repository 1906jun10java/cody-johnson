package daos;

import models.Credential;

import java.sql.SQLException;

public interface CredentialDao {
	Credential getPassword(String email) throws SQLException;
}
