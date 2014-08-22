package cn.com.cnnoc.expert.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cn.com.cnnoc.expert.dao.EvaluationDao;
import cn.com.cnnoc.expert.model.Evaluation;
import cn.com.cnnoc.expert.vo.EvaluationVO;
import cn.com.cnnoc.expert.vo.PagerVO;

public class EvaluationDaoImpl extends BaseDaoImpl<Evaluation> implements
		EvaluationDao {

	@Override
	public PagerVO findPaged(Map<String, Object> params, int page, int rows) {
		SqlSession session = getSessionFactory().openSession();
		PagerVO<EvaluationVO> pv = new PagerVO<EvaluationVO>();
		try {
			int start = (page - 1) * rows;
			if (params == null)
				params = new HashMap<String, Object>();
			
			params.put("start", start);
			params.put("rows", rows);
			// 查询数量
			Integer total = session.selectOne(Evaluation.class.getName()
					+ ".findParamsTotal", params);
			pv.setTotal(total);

			

			List<EvaluationVO> list = session.selectList(
					Evaluation.class.getName() + ".findParamsPaged", params);

			pv.setRows(list);

		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}

		return pv;
	}

}
