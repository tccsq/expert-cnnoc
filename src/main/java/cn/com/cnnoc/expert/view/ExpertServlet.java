package cn.com.cnnoc.expert.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.cnnoc.expert.dao.ExpertDao;
import cn.com.cnnoc.expert.model.Expert;
import cn.com.cnnoc.expert.model.Gender;
import cn.com.cnnoc.expert.model.User;
import cn.com.cnnoc.expert.util.CommonUtil;
import cn.com.cnnoc.expert.vo.PagerVO;

public class ExpertServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private ExpertDao expertDao;

	@Override
	protected void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/expert/index.jsp");
	}

	/**
	 * 获取专家列表
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getExpertList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int page = 0;
		int rows = 10;
		try {
			page = Integer.parseInt(request.getParameter("page"));
			rows = Integer.parseInt(request.getParameter("rows"));
		} catch (Exception e) {
			// 转换异常不处理，使用默认分页参数
		}

		PagerVO<Expert> pagerVO = expertDao.findPaged(Expert.class, page, rows);

		toJSON(response, pagerVO);
	}

	/**
	 * 添加专家
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addExpert(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 获取参数
		String gender = request.getParameter("gender");
		String expertName = request.getParameter("expertName");
		String idNumber = request.getParameter("idNumber");
		String major = request.getParameter("major");
		String expertDesc = request.getParameter("expertDesc");

		Expert temp;
		// 判断当前用户名是否存在
		temp = expertDao.findExpertByIdNumber(idNumber);
		if (temp != null) {
			errorMsg("专家证件号：" + idNumber + "已存在！", response);
			return;
		}

		int genderValue = 0;
		if (!CommonUtil.isNullOrBlank(gender))
			genderValue = Integer.parseInt(gender);

		Expert e = new Expert(expertName, idNumber,
				Gender.getGender(genderValue), major, expertDesc);
		// 从session中取出当前用户
		User currentUser = getCurrentUser(request, response);
		e.setCreateBy(currentUser.getId());
		// 保存
		expertDao.add(e);

		successMsg(response);
	}

	/**
	 * 更新专家
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateExpert(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 获取参数
		String gender = request.getParameter("gender");
		String expertName = request.getParameter("expertName");
		String idNumber = request.getParameter("idNumber");
		String major = request.getParameter("major");
		String expertDesc = request.getParameter("expertDesc");
		String id = request.getParameter("id");

		Expert temp;
		// 判断当前用户名是否存在
		temp = expertDao.findExpertByIdNumber(idNumber);
		if (temp != null && temp.getId() != Integer.parseInt(id)) {
			errorMsg("专家证件号：" + idNumber + "已存在！", response);
			return;
		}

		int genderValue = 0;
		if (!CommonUtil.isNullOrBlank(gender))
			genderValue = Integer.parseInt(gender);

		Expert e = new Expert(expertName, idNumber,
				Gender.getGender(genderValue), major, expertDesc);
		e.setId(Integer.parseInt(id));

		// 从session中取出当前用户
		User currentUser = getCurrentUser(request, response);
		e.setModifyBy(currentUser.getId());
		// 保存
		expertDao.update(e);

		successMsg(response);
	}
	
	/**
	 * 查询专家
	 * 主要在显示专家简历时使用
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void loadExpert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO 对的删除需要确认，是否需要做假删除
		String idstr = request.getParameter("id");
		Expert expert = expertDao.findById(Expert.class, Integer.parseInt(idstr));

		toJSON(response, expert);
	}

	/**
	 * 删除专家
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delExpert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO 对的删除需要确认，是否需要做假删除
		String idstr = request.getParameter("id");
		expertDao.deleteById(Expert.class, Integer.parseInt(idstr));

		successMsg(response);
	}

	public void setExpertDao(ExpertDao expertDao) {
		this.expertDao = expertDao;
	}

}
