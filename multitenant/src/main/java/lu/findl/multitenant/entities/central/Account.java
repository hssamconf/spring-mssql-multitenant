package lu.findl.multitenant.entities.central;

import lu.findl.multitenant.entities.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "account")
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
	@Column(name = "role_name", nullable = false)
	private String roleName;

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", databaseName=" + databaseName
				+ ", databasePwd=" + databasePwd + ", roleName=" + roleName + "]";
	}

	public Account() {
	}

	public Account(String username, String password, String databaseName, String databasePwd, String roleName) {
		this.username = username;
		this.password = password;
		this.databaseName = databaseName;
		this.databasePwd = databasePwd;
		this.roleName = roleName;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
