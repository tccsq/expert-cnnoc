package cn.com.cnnoc.expert.dao.impl;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.com.cnnoc.expert.dao.BaseDao;

public class BaseDaoImpl implements BaseDao {

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

}
