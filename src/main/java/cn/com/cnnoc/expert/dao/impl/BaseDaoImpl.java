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
import cn.com.cnnoc.expert.model.User;
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
	public T findById(Class<T> clz,int id) {
		SqlSession session = getSessionFactory().openSession();
		T t = null;
		try {
			t = session.selectOne(clz.getName() + ".findById", id);

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
	public PagerVO<T> findPaged(Class<T> clz,int start, int rows) {
		SqlSession session = getSessionFactory().openSession();
		PagerVO<T> pv = new PagerVO<T>();
		try {
			
			int total = session.selectOne(clz.getName() + ".findTotal");
			pv.setTotal(total);
			
			Map<String,Integer> params = new HashMap<String,Integer>();
			params.put("start", start);
			params.put("rows", rows);
			List<T> list = session.selectList(clz.getName() + ".findPaged", params);
			
			pv.setDatas(list);

		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		
		return pv;
	}

}
