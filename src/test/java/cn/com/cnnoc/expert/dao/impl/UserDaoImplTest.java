package cn.com.cnnoc.expert.dao.impl;

import junit.framework.TestCase;
import cn.com.cnnoc.expert.dao.UserDao;
import cn.com.cnnoc.expert.model.User;
import cn.com.cnnoc.expert.model.User.Role;
import cn.com.cnnoc.expert.util.PropertiesBeanFactory;

public class UserDaoImplTest extends TestCase {
	UserDao userDao = (UserDao) new PropertiesBeanFactory().getBean("UserDao");

	public void testAddUser() {
		User u = new User();
		u.setEmpno("123456");
		u.setPassword("123");
		u.setUsername("user2");
		u.setRole(Role.ADMIN);
		u.setCreateBy(1);
		u.setModifyBy(1);

		userDao.add(u);
	}

	public void testUpdateUser() {
		User u = userDao.findUserByUsername("user2");
		System.out.println(u.getId());
		u.setEmpno("65421");
		u.setPassword("123");
		u.setUsername("user1");
		u.setCreateBy(2);
		u.setModifyBy(1);
		userDao.update(u);
	}

	public void testFindUserByUsername() {
		User u = userDao.findUserByUsername("user1");
		System.out.println(u.getId());
	}

	public void testDeleteUser() {
		User u = userDao.findUserByUsername("user1");
		userDao.deleteById(User.class, u.getId());
		u = userDao.findUserByUsername("user1");
		assertNull(u);
	}

}
