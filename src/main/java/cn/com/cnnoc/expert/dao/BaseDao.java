package cn.com.cnnoc.expert.dao;

import org.apache.ibatis.session.SqlSessionFactory;


public interface BaseDao {
	public SqlSessionFactory getSessionFactory();
}
