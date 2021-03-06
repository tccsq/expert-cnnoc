package cn.com.cnnoc.expert.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;

import cn.com.cnnoc.expert.vo.PagerVO;

public interface BaseDao<T> {
	public SqlSessionFactory getSessionFactory();

	public void add(T t);

	public void update(T t);

	public T findById(Class<T> clz, int id);

	public void deleteById(Class<T> clz, int id);

	/**
	 * 分页查询
	 * 
	 * @param start
	 * @param rows
	 * @return
	 */
	public PagerVO<T> findPaged(Class<T> clz, int start, int rows);

	/**
	 * 根据条件分页查询
	 * 
	 * @param clz
	 * @param params
	 * @param start
	 * @param rows
	 * @return
	 */
	public PagerVO<T> findPaged(Class<T> clz, Map<String, Object> params,
			int start, int rows);

}
