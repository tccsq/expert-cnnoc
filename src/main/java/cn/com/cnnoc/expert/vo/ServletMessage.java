package cn.com.cnnoc.expert.vo;

public class ServletMessage {
	private String status;
	private String msg;

	public ServletMessage() {
	}

	public ServletMessage(String status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	
	public static ServletMessage createSuccessMessageInstance(){
		return new ServletMessage("success", "请求成功！");
	}
	
	public static ServletMessage createErrorMessageInstance(){
		return new ServletMessage("error", "请求失败！");
	}
	
	public static ServletMessage createErrorMessageInstance(String errorMsg){
		return new ServletMessage("error", errorMsg);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
