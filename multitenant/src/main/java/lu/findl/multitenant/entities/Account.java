package lu.findl.multitenant.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Account extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(name = "database_name", nullable = false, unique = true)
	private String databaseName;
	@Column(name = "database_pwd", nullable = false)
	private String databasePwd;

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", databaseName=" + databaseName
				+ ", databasePwd=" + databasePwd + "]";
	}

	public Account() {
	}

	public Account(String username, String password, String databaseName, String databasePwd) {
		this.username = username;
		this.password = password;
		this.databaseName = databaseName;
		this.databasePwd = databasePwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getDatabasePwd() {
		return databasePwd;
	}

	public void setDatabasePwd(String databasePwd) {
		this.databasePwd = databasePwd;
	}

}
