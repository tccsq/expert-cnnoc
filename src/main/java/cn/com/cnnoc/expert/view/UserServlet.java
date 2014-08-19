package cn.com.cnnoc.expert.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.cnnoc.expert.dao.ArticleDao;
import cn.com.cnnoc.expert.dao.UserDao;
import cn.com.cnnoc.expert.model.Article;
import cn.com.cnnoc.expert.model.User;
import cn.com.cnnoc.expert.util.CommonUtil;
import cn.com.cnnoc.expert.vo.PagerVO;

public class UserServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private ArticleDao articleDao;
	private UserDao userDao;

	@Override
	protected void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()
				+ "/user/index.jsp");
	}

	public void getUserList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int start = 0;
		int rows = 10;
		try {
			start = Integer.parseInt(request.getParameter("start"));
			rows = Integer.parseInt(request.getParameter("rows"));
		} catch (Exception e) {

		}

		PagerVO<User> pagerVO = userDao.findPaged(User.class, start, rows);

		toJSON(response, pagerVO);
	}

	public void addArticle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String title = request.getParameter("title");

		String content = request.getParameter("content");

		Article article = new Article();

		article.setTitle(title);

		article.setContent(content);

		articleDao.addArticle(article);

		request.getRequestDispatcher("/backend/article/add_article_success.jsp")
				.forward(request, response);

	}

	public void delArticle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String[] ids = request.getParameterValues("id");
		for (int i = 0; i < ids.length; i++) {

			if (ids[i] == null) {
				request.setAttribute("error", "ID列表为空！");
				request.getRequestDispatcher("/backend/common/error.jsp")
						.forward(request, response);
				return;
			}

			articleDao.deleteArticle(Integer.parseInt(ids[i]));

		}

		response.sendRedirect(request.getContextPath()
				+ "/backend/ArticleServlet");

	}

	public void loadArticle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		Article article = new Article();

		if (id == null) {
			request.setAttribute("error", "ID不能为空");
			request.getRequestDispatcher("/backend/common/error.jsp").forward(
					request, response);
			return;
		}

		article = articleDao.findArticleById(Integer.parseInt(id));

		request.setAttribute("article", article);

		request.getRequestDispatcher("/backend/article/open_input_article.jsp")
				.forward(request, response);

	}

	public void updateArticle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		if (id == null) {
			request.setAttribute("error", "用户Id为空");

			request.getRequestDispatcher("/backend/common/error.jsp").forward(
					request, response);
			return;
		}

		Article article = new Article();
		article.setTitle(title);
		article.setContent(content);

		articleDao.updateArticle(article);

		request.getRequestDispatcher(
				"/backend/article/update_article_success.jsp").forward(request,
				response);

	}

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
