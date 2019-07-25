package daos;

import models.Credential;

import java.sql.SQLException;

public interface CredentialDao {
	Credential getCredential(String email) throws SQLException;
}
