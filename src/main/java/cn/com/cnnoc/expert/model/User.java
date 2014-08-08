package cn.com.cnnoc.expert.model;

public class User extends BaseEntity {
	private String username;
	private String password;
	private String empno;
	private Role role;

	public enum Role {
		USER(0), ADMIN(1);
		private int value;

		private Role(int value) {
			this.value = value;
		}

		public static Role getRole(int i) {
			for (Role r : Role.values()) {
				if (r.getValue() == i)
					return r;
			}
			return null;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
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
}
