package cn.com.cnnoc.expert.dao;

import java.util.Map;

import cn.com.cnnoc.expert.model.Evaluation;
import cn.com.cnnoc.expert.vo.PagerVO;

public interface EvaluationDao extends BaseDao<Evaluation>{
	public PagerVO findPaged(Map<String, Object> params, int page, int rows);
}
