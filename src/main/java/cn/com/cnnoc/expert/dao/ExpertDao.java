package cn.com.cnnoc.expert.dao;

import java.util.List;

import cn.com.cnnoc.expert.model.Expert;

public interface ExpertDao extends BaseDao<Expert>{
	
	public Expert findExpertByIdNumber(String idNumber);
	
	public List<Expert> findAllExpert();
}
