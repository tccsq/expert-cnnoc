package cn.com.cnnoc.expert.vo;

import java.util.List;

public class PagerVO<T> {
	private int total;
	private List<T> datas;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

}
