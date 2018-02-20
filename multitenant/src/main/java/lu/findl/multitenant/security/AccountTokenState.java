package lu.findl.multitenant.security;

public class AccountTokenState {
	private String access_token;
	private Long expires_in;
	private String role_name;

	public AccountTokenState() {
		this.access_token = null;
		this.expires_in = null;
		this.role_name = null;
	}

	public AccountTokenState(String access_token, long expires_in, String role_name) {
		this.access_token = access_token;
		this.expires_in = expires_in;
		this.role_name = role_name;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public Long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}
}