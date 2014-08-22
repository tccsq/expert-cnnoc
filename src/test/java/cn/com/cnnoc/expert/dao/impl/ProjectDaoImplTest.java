package cn.com.cnnoc.expert.dao.impl;

import junit.framework.TestCase;
import cn.com.cnnoc.expert.dao.ProjectDao;
import cn.com.cnnoc.expert.model.Project;
import cn.com.cnnoc.expert.util.PropertiesBeanFactory;
import cn.com.cnnoc.expert.vo.PagerVO;

public class ProjectDaoImplTest extends TestCase {
	ProjectDao projectDao = (ProjectDao) new PropertiesBeanFactory()
			.getBean("ProjectDao");

	public void testAdd() {
		for (int i = 1; i < 31; i++) {
			Project p = new Project();
			p.setProjectName("projectName" + i);
			p.setProjectLocation("projectLocation" + i);
			p.setProjectDesc("projectDesc" + i);
			p.setStartDate("2014-09-"+i);
			p.setEndDate("2014-08-"+i);
			p.setCreateBy(1);
			projectDao.add(p);
		}
	}

	public void testUpdate() {
		Project p = projectDao.findById(Project.class, 210);
		p.setProjectName("projectName-updated");
		projectDao.update(p);
	}

	public void testFindById() {
		Project p = projectDao.findById(Project.class, 210);
		System.out.println(p.getProjectName());
	}

	public void testDeleteById() {
		projectDao.deleteById(Project.class, 211);
		Project p = projectDao.findById(Project.class, 211);
		assertNull(p);
	}

	public void testFindPaged() {
		PagerVO<Project> pv = projectDao.findPaged(Project.class, 10, 20);
		System.out.println(pv.getTotal());
		assertEquals(49, pv.getTotal());
	}

}
