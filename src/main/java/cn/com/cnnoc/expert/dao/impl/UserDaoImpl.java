package cn.com.cnnoc.expert.dao.impl;

import org.apache.ibatis.session.SqlSession;

import cn.com.cnnoc.expert.dao.UserDao;
import cn.com.cnnoc.expert.model.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	
//	@Override
//	public void addUser(User user) {
//
//		SqlSession session = getSessionFactory().openSession();
//		try {
//			session.insert(User.class.getName() + ".insert", user);
//			session.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.rollback();
//		} finally {
//			session.close();
//		}
//
//	}
//	
//	@Override
//	public void deleteUser(int id) {
//		SqlSession session = getSessionFactory().openSession();
//		try {
//
//			session.selectOne(User.class.getName() + ".delete", id);
//			session.commit();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.rollback();
//		} finally {
//			session.close();
//		}
//
//	}
//	
	@Override
	public User findUserByUsername(String username) {
		SqlSession session = getSessionFactory().openSession();
		User user = null;
		try {
			user = (User) session.selectOne(User.class.getName()
					+ ".findUserByUsername",username);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}
//	
//	@Override
//	public void updateUser(User user) {
//
//		SqlSession session = getSessionFactory().openSession();
//		try {
//			session.insert(User.class.getName() + ".update", user);
//			session.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.rollback();
//		} finally {
//			session.close();
//		}
//
//	}
}
