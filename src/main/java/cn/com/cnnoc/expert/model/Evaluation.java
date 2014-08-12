package cn.com.cnnoc.expert.model;

public class Evaluation extends BaseEntity {
	private int projectId;
	private int expertId;
	private String evaluationContent;
	private String avgGrade;
	
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getExpertId() {
		return expertId;
	}

	public void setExpertId(int expertId) {
		this.expertId = expertId;
	}

	public String getEvaluationContent() {
		return evaluationContent;
	}

	public void setEvaluationContent(String evaluationContent) {
		this.evaluationContent = evaluationContent;
	}

	public String getAvgGrade() {
		return avgGrade;
	}

	public void setAvgGrade(String avgGrade) {
		this.avgGrade = avgGrade;
	}

}
