package cn.com.cnnoc.expert.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.cnnoc.expert.dao.UserDao;
import cn.com.cnnoc.expert.model.Role;
import cn.com.cnnoc.expert.model.User;
import cn.com.cnnoc.expert.util.CommonUtil;
import cn.com.cnnoc.expert.vo.PagerVO;

public class UserServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	@Override
	protected void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/user/index.jsp");
	}

	/**
	 * 获取用户列表
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getUserList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int page = 0;
		int rows = 10;
		try {
			page = Integer.parseInt(request.getParameter("page"));
			rows = Integer.parseInt(request.getParameter("rows"));
		} catch (Exception e) {

		}

		PagerVO<User> pagerVO = userDao.findPaged(User.class, page, rows);

		toJSON(response, pagerVO);
	}

	/**
	 * 添加用户
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取参数
		String role = request.getParameter("role");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String empno = request.getParameter("empno");

		User temp;
		// 判断当前用户名是否存在
		temp = userDao.findUserByUsername(username);
		if (temp != null) {
			errorMsg("用户名：" + username + "已存在！", response);
			return;
		}
		// 判断当前员工号是否存在
		temp = userDao.findUserByEmpno(empno);
		if (temp != null) {
			errorMsg("员工号" + empno + "已被使用！", response);
			return;
		}

		int roleValue = 0;
		if (!CommonUtil.isNullOrBlank(role))
			roleValue = Integer.parseInt(role);
		// 新建用户
		User u = new User(username, password, empno, Role.getRole(roleValue));
		// 从session中取出当前用户
		User currentUser = getCurrentUser(request, response);
		u.setCreateBy(currentUser.getId());
		// 保存
		userDao.add(u);

		successMsg(response);
	}

	/**
	 * 更新用户
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取参数
		String role = request.getParameter("role");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String empno = request.getParameter("empno");
		String id = request.getParameter("id");

		int roleValue = 0;
		if (CommonUtil.isNullOrBlank(role))
			roleValue = Integer.parseInt(role);
		// 新建用户
		User u = new User(username, password, empno, Role.getRole(roleValue));
		u.setId(Integer.parseInt(id));

		User temp;
		// 判断当前用户名是否存在
		temp = userDao.findUserByUsername(username);
		if (temp != null && temp.getId() != u.getId()) {
			errorMsg("用户名：" + username + "已存在！", response);
			return;
		}

		// 判断当前员工号是否存在
		temp = userDao.findUserByEmpno(empno);
		if (temp != null && temp.getId() != u.getId()) {
			errorMsg("员工号" + empno + "已被使用！", response);
			return;
		}

		// 从session中取出当前用户
		User currentUser = getCurrentUser(request, response);
		u.setModifyBy(currentUser.getId());
		// 保存
		userDao.update(u);

		successMsg(response);
	}

	/**
	 * 删除用户
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO 对用户的删除需要确认，是否需要做假删除
		String idstr = request.getParameter("id");
		userDao.deleteById(User.class, Integer.parseInt(idstr));

		successMsg(response);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
