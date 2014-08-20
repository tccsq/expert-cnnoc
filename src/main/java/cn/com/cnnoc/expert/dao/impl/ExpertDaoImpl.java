package cn.com.cnnoc.expert.dao.impl;

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

}
