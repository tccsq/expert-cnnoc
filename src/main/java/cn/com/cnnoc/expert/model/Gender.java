package cn.com.cnnoc.expert.model;

public enum Gender {
	MALE(0), FEMALE(1);
	private int value;

	private Gender(int value) {
		this.value = value;
	}

	public static Gender getGender(int i) {
		for (Gender g : Gender.values()) {
			if (g.getValue() == i)
				return g;
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
