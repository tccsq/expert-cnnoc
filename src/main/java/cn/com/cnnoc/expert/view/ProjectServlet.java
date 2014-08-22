package cn.com.cnnoc.expert.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.cnnoc.expert.dao.ProjectDao;
import cn.com.cnnoc.expert.model.Project;
import cn.com.cnnoc.expert.model.User;
import cn.com.cnnoc.expert.vo.PagerVO;

public class ProjectServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private ProjectDao projectDao;

	@Override
	protected void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/project/index.jsp");
	}

	/**
	 * 获取项目列表
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getProjectList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int page = 0;
		int rows = 10;
		try {
			page = Integer.parseInt(request.getParameter("page"));
			rows = Integer.parseInt(request.getParameter("rows"));
		} catch (Exception e) {

		}

		PagerVO<Project> pagerVO = projectDao.findPaged(Project.class, page,
				rows);

		toJSON(response, pagerVO);
	}

	/**
	 * 添加项目
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addProject(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取参数
		String projectName = request.getParameter("projectName");
		String projectLocation = request.getParameter("projectLocation");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		// 新建
		Project p = new Project(projectName, projectLocation, startDate,
				endDate, null);
		// 从session中取出当前用户
		User currentUser = getCurrentUser(request, response);
		p.setCreateBy(currentUser.getId());
		// 保存
		projectDao.add(p);

		successMsg(response);
	}

	/**
	 * 更新项目
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateProject(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取参数
		String projectName = request.getParameter("projectName");
		String projectLocation = request.getParameter("projectLocation");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String id = request.getParameter("id");

		// 新建
		Project p = new Project(projectName, projectLocation, startDate,
				endDate, null);
		p.setId(Integer.parseInt(id));

		// 从session中取出当前用户
		User currentUser = getCurrentUser(request, response);
		p.setModifyBy(currentUser.getId());
		// 保存
		projectDao.update(p);

		successMsg(response);
	}

	/**
	 * 删除项目
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delProject(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO 对用户的删除需要确认，是否需要做假删除
		String idstr = request.getParameter("id");
		projectDao.deleteById(Project.class, Integer.parseInt(idstr));

		successMsg(response);
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
}
