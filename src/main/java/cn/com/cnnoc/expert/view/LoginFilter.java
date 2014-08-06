package cn.com.cnnoc.expert.view;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {
	
	private String param;  
	
	public void destroy() {


	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String userInfo = (String) request.getSession().getAttribute("USER_ADMIN");
		
		
		String url = request.getRequestURI();
		String page = url.substring(request.getContextPath().length());
		
		if(url.matches(param)){
			
			//如果不是登录页面
			if(!page.equals("/backend/LoginServlet") && !page.equals("/backend/login.jsp")){
				if(userInfo == null){
					response.sendRedirect(request.getContextPath()+"/backend/login.jsp");
					return;
				}
			}
		}

		chain.doFilter(req, resp);
		
	}

	public void init(FilterConfig config) throws ServletException {
		param = config.getInitParameter("param");
	}

}
