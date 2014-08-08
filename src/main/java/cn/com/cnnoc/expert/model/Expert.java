package cn.com.cnnoc.expert.model;

public class Expert extends BaseEntity {
	private String expertName;
	private String major;
	private String expertDesc;

	public String getExpertName() {
		return expertName;
	}

	public void setExpertName(String expertName) {
		this.expertName = expertName;
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
}
