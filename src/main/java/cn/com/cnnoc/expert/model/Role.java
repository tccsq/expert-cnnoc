package cn.com.cnnoc.expert.model;

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
