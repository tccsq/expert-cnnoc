package cn.com.cnnoc.expert.model;

public class Expert extends BaseEntity {
	private String expertName;
	private String idNumber;
	private Gender gender;
	private String major;
	private String expertDesc;
	
	

	public Expert() {
	}

	public Expert(String expertName, String idNumber, Gender gender,
			String major, String expertDesc) {
		super();
		this.expertName = expertName;
		this.idNumber = idNumber;
		this.gender = gender;
		this.major = major;
		this.expertDesc = expertDesc;
	}

	public String getExpertName() {
		return expertName;
	}

	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getExpertDesc() {
		return expertDesc;
	}

	public void setExpertDesc(String expertDesc) {
		this.expertDesc = expertDesc;
	}

	@Override
	public String toString() {
		return "Expert [expertName=" + expertName + ", idNumber=" + idNumber
				+ ", gender=" + gender + ", major=" + major + ", expertDesc="
				+ expertDesc + "]";
	}
	
	
}
