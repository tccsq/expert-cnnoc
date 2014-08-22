package cn.com.cnnoc.expert.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.cnnoc.expert.dao.EvaluationDao;
import cn.com.cnnoc.expert.model.Evaluation;
import cn.com.cnnoc.expert.model.User;
import cn.com.cnnoc.expert.util.CommonUtil;
import cn.com.cnnoc.expert.vo.EvaluationVO;
import cn.com.cnnoc.expert.vo.PagerVO;

public class EvaluationServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private EvaluationDao evaluationDao;

	/**
	 * 根据获取评价列表
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getEvaluationList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		int rows = 10;
		try {
			page = Integer.parseInt(request.getParameter("page"));
			rows = Integer.parseInt(request.getParameter("rows"));
		} catch (Exception e) {
			// 转换异常不处理，使用默认分页参数
		}
		if(page <= 0) 
			page = 1;
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		String projectIdStr = request.getParameter("projectId");
		if (!CommonUtil.isNullOrBlank(projectIdStr)) {
			int projectId = Integer.parseInt(projectIdStr);
			
			params.put("projectId", projectId);
		}
		
		PagerVO pagerVO = evaluationDao.findPaged(params, page, rows);

		toJSON(response, pagerVO);
	}

	/**
	 * 添加评价
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addEvaluation(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 获取参数
		String projectId = request.getParameter("projectId");
		String expertId = request.getParameter("expert");
		String evaluationContent = request.getParameter("evaluationContent");

		Evaluation e = new Evaluation(Integer.parseInt(projectId),
				Integer.parseInt(expertId), evaluationContent);
		// 从session中取出当前用户
		User currentUser = getCurrentUser(request, response);
		e.setCreateBy(currentUser.getId());
		// 保存
		evaluationDao.add(e);

		successMsg(response);
	}

	/**
	 * 更新评价
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateEvaluation(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("evaluationId");
		// 获取参数
		String projectId = request.getParameter("projectId");
		String expertId = request.getParameter("expertId");
		String evaluationContent = request.getParameter("evaluationContent");

		Evaluation e = new Evaluation(Integer.parseInt(projectId),
				Integer.parseInt(expertId), evaluationContent);
		e.setId(Integer.parseInt(id));
		// 从session中取出当前用户
		User currentUser = getCurrentUser(request, response);
		e.setCreateBy(currentUser.getId());
		// 保存
		evaluationDao.update(e);

		successMsg(response);
	}

	/**
	 * 删除评价
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delEvaluation(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO 对的删除需要确认，是否需要做假删除
		String idstr = request.getParameter("id");
		evaluationDao.deleteById(Evaluation.class, Integer.parseInt(idstr));

		successMsg(response);
	}

	public void setEvaluationDao(EvaluationDao evaluationDao) {
		this.evaluationDao = evaluationDao;
	}
}
