package cn.com.cnnoc.expert.dao.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.com.cnnoc.expert.dao.BaseDao;
import cn.com.cnnoc.expert.vo.PagerVO;

public class BaseDaoImpl<T> implements BaseDao<T> {

	public SqlSessionFactory getSessionFactory() {
		SqlSessionFactory factory = null;
		try {
			Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return factory;
	}

	@Override
	public void add(T t) {
		SqlSession session = getSessionFactory().openSession();
		try {
			session.insert(t.getClass().getName() + ".insert", t);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public void update(T t) {
		SqlSession session = getSessionFactory().openSession();
		try {
			session.insert(t.getClass().getName() + ".update", t);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public T findById(Class<T> clz, int id) {
		SqlSession session = getSessionFactory().openSession();
		T t = null;
		try {
			t = (T) session.selectOne(clz.getName() + ".findById", id);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return t;
	}

	@Override
	public void deleteById(Class<T> clz, int id) {
		SqlSession session = getSessionFactory().openSession();
		try {
			session.selectOne(clz.getName() + ".delete", id);
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public PagerVO<T> findPaged(Class<T> clz, int page, int rows) {
		SqlSession session = getSessionFactory().openSession();
		PagerVO<T> pv = new PagerVO<T>();
		try {
			int start = (page - 1) * rows;

			Integer total = session.selectOne(clz.getName() + ".findTotal");
			pv.setTotal(total);

			Map<String, Integer> params = new HashMap<String, Integer>();
			params.put("start", start);
			params.put("rows", rows);
			List<T> list = session.selectList(clz.getName() + ".findPaged",
					params);

			pv.setRows(list);

		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}

		return pv;
	}

	@Override
	public PagerVO<T> findPaged(Class<T> clz, Map<String, Object> params,
			int page, int rows) {
		SqlSession session = getSessionFactory().openSession();
		PagerVO<T> pv = new PagerVO<T>();
		try {
			int start = (page - 1) * rows;
			if (params == null)
				params = new HashMap<String, Object>();
			params.put("start", start);
			params.put("rows", rows);
			
			//查询数量
			Integer total = session.selectOne(clz.getName()
					+ ".findParamsTotal", params);
			pv.setTotal(total);

			
			
			List<T> list = session.selectList(clz.getName()
					+ ".findParamsPaged", params);

			pv.setRows(list);

		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}

		return pv;
	}

}
