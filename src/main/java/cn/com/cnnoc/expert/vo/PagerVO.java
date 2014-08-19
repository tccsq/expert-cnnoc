package cn.com.cnnoc.expert.vo;

import java.util.List;

public class PagerVO<T> {
	private int total;
	private List<T> rows;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> datas) {
		this.rows = datas;
	}

}
