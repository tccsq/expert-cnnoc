package cn.com.cnnoc.expert.model;

public class User extends BaseEntity {
	private String username;
	private String password;
	private String empno;
	private Role role;


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

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", empno=" + empno + ", role=" + role + "]";
	}
	
	
}
