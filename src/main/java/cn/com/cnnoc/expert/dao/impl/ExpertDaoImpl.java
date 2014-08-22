package cn.com.cnnoc.expert.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.com.cnnoc.expert.dao.ExpertDao;
import cn.com.cnnoc.expert.model.Expert;

public class ExpertDaoImpl extends BaseDaoImpl<Expert> implements ExpertDao {

	@Override
	public Expert findExpertByIdNumber(String idNumber) {
		SqlSession session = getSessionFactory().openSession();
		Expert expert = null;
		try {
			expert = (Expert) session.selectOne(Expert.class.getName()
					+ ".findExpertByIdNumber", idNumber);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return expert;
	}

	@Override
	public List<Expert> findAllExpert() {
		SqlSession session = getSessionFactory().openSession();

		List<Expert> experts = null;
		try {
			experts = session.selectList(Expert.class.getName() + ".findAll");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return experts;
	}

}
