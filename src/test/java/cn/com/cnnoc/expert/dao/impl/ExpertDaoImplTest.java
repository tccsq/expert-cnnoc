package cn.com.cnnoc.expert.dao.impl;

import junit.framework.TestCase;
import cn.com.cnnoc.expert.dao.ExpertDao;
import cn.com.cnnoc.expert.model.Expert;
import cn.com.cnnoc.expert.model.Gender;
import cn.com.cnnoc.expert.util.PropertiesBeanFactory;
import cn.com.cnnoc.expert.vo.PagerVO;

public class ExpertDaoImplTest extends TestCase {

	ExpertDao expertDao = (ExpertDao) new PropertiesBeanFactory()
			.getBean("ExpertDao");

	/**
	 * 
	 */
	public void testAdd() {

		for (int i = 0; i < 50; i++) {
			Expert expert = new Expert();
			expert.setExpertName("expert" + i);
			expert.setExpertDesc("desc" + i);
			expert.setMajor("major" + i);
			expert.setIdNumber("987654" + i);
			if (i % 2 == 0)
				expert.setGender(Gender.MALE);
			else
				expert.setGender(Gender.FEMALE);
			expert.setCreateBy(1);
			expertDao.add(expert);
		}

	}

	public void testUpdate() {
		Expert expert = expertDao.findById(Expert.class, 210);
		expert.setModifyBy(2);
		expert.setExpertName("expert-updated");
		expertDao.update(expert);
	}

	public void testFindById() {
		Expert expert = expertDao.findById(Expert.class, 210);
		System.out.println(expert);
	}

	public void testDeleteById() {
		expertDao.deleteById(Expert.class, 1);
		Expert expert = expertDao.findById(Expert.class, 0);
		assertNull(expert);
	}

	public void testFindPaged() {
		PagerVO<Expert> pv = expertDao.findPaged(Expert.class, 10, 10);
		System.out.println(pv.getTotal());
		assertEquals(50, pv.getTotal());

	}

}
