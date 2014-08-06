package cn.com.cnnoc.expert.view;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import cn.com.cnnoc.expert.util.BeanFactory;
import cn.com.cnnoc.expert.util.PropertiesBeanFactory;

public class InitBeanFactory extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static final String INIT_BEAN_FACTORY = "_my_bean_factory";
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		System.out.println("初始化BeanFactory...");
		
		String configLocation = config.getInitParameter("configLocation");
		
		BeanFactory factory = null;
		
		if(configLocation == null){
			factory = new PropertiesBeanFactory();
		}else{
			factory = new PropertiesBeanFactory(configLocation);
		}
		
		config.getServletContext().setAttribute(INIT_BEAN_FACTORY, factory);
	}

}
