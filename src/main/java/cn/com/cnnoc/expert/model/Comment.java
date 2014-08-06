package cn.com.cnnoc.expert.model;

public class Comment {
	private int id;
	private int articelId;
	private String content;
	private String name;
	private String website;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArticelId() {
		return articelId;
	}

	public void setArticelId(int articelId) {
		this.articelId = articelId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
}
