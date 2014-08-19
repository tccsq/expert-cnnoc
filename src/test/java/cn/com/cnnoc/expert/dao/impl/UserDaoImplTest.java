package cn.com.cnnoc.expert.dao.impl;

import junit.framework.TestCase;
import cn.com.cnnoc.expert.dao.UserDao;
import cn.com.cnnoc.expert.model.Role;
import cn.com.cnnoc.expert.model.User;
import cn.com.cnnoc.expert.util.PropertiesBeanFactory;
import cn.com.cnnoc.expert.vo.PagerVO;

public class UserDaoImplTest extends TestCase {
	UserDao userDao = (UserDao) new PropertiesBeanFactory().getBean("UserDao");
	

	public void testAddUserList() {
		for(int i=0;i<50;i++){
			User u = new User();
			u.setEmpno("123456"+i);
			u.setPassword("123");
			u.setUsername("user"+i);
			
			if(i==0)
				u.setRole(Role.ADMIN);
			else
				u.setRole(Role.USER);
			u.setCreateBy(1);
			u.setModifyBy(1);

			userDao.add(u);
		}
		
		
	}
	
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
	
	public void testFindUserById() {
		User u = userDao.findById(User.class, 51);
		System.out.println(u);
	}

	public void testDeleteUser() {
		User u = userDao.findUserByUsername("user1");
		userDao.deleteById(User.class, u.getId());
		u = userDao.findUserByUsername("user1");
		assertNull(u);
	}
	
	public void testPaged(){
		PagerVO<User> pv = userDao.findPaged(User.class, 0, 10);
		for(User u:pv.getRows()){
			System.out.println(u);
		}
	}

}
