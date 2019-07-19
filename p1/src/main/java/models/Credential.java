package models;

public class Credential {
	private int id;
	private String password;

	public Credential() {}

	public int geteId() {
		return id;
	}
	public void seteId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
