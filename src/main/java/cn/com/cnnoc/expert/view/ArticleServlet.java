package cn.com.cnnoc.expert.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.cnnoc.expert.dao.ArticleDao;
import cn.com.cnnoc.expert.model.Article;
import cn.com.cnnoc.expert.util.CommonUtil;
import cn.com.cnnoc.expert.vo.PagerVO;

public class ArticleServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private ArticleDao articleDao;

	@Override
	protected void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int offset = 0;
		int pagesize = 5;
		int total = 0;

		String title = request.getParameter("title");
		String createTime = request.getParameter("createTime");

		try {
			offset = Integer.parseInt(request.getParameter("pager.offset"));
		} catch (Exception e) {
		}

		if (!CommonUtil.isNullOrBlank(request.getParameter("pagesize"))) {
			pagesize = Integer.parseInt(request.getParameter("pagesize"));
			request.getSession().setAttribute("pagesize", pagesize);
		}
		Integer ps = (Integer) request.getSession().getAttribute("pagesize");
		if (ps == null) {
			request.getSession().setAttribute("pagesize", pagesize);
		} else {
			pagesize = ps;
		}

		PagerVO pagerVO = articleDao.findArticleList(title, createTime,
				pagesize, offset);

		request.setAttribute("pv", pagerVO);

		request.getRequestDispatcher("/backend/article/article_list.jsp")
				.forward(request, response);
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

}
