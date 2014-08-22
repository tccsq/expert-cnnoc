package cn.com.cnnoc.expert.model;

public class Project extends BaseEntity {
	private String projectName;
	private String projectLocation;
	private String startDate;
	private String endDate;
	private String projectDesc;

	public Project() {
	}

	public Project(String projectName, String projectLocation,
			String startDate, String endDate, String projectDesc) {
		super();
		this.projectName = projectName;
		this.projectLocation = projectLocation;
		this.startDate = startDate;
		this.endDate = endDate;
		this.projectDesc = projectDesc;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectLocation() {
		return projectLocation;
	}

	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}
}
