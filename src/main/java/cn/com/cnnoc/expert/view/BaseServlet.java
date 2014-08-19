package cn.com.cnnoc.expert.view;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.cnnoc.expert.model.User;
import cn.com.cnnoc.expert.util.BeanFactory;
import cn.com.cnnoc.expert.vo.ServletMessage;

import com.sdicons.json.mapper.JSONMapper;

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {

		BeanFactory factory = (BeanFactory) getServletContext().getAttribute(
				InitBeanFactory.INIT_BEAN_FACTORY);

		Method[] methods = this.getClass().getMethods();

		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().startsWith("set")) {

				String beanName = methods[i].getName().substring(3);

				Object bean = factory.getBean(beanName);

				try {
					methods[i].invoke(this, bean);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		super.service(arg0, arg1);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}

	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String method = request.getParameter("method");

		if (method == null || method.trim().equals("")) {
			execute(request, response);
		} else {

			try {
				// Method m =
				// this.getClass().getMethod(method,HttpServletRequest.class,HttpServletResponse.class);
				Method m = this.getClass().getMethod(method,
						HttpServletRequest.class, HttpServletResponse.class);
				m.invoke(this, request, response);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

		}

	}

	protected void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * 获取当前登录用户
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	protected User getCurrentUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		return (User) request.getSession()
				.getAttribute(LoginServlet.USER_ADMIN);
	}

	/**
	 * 把对象转换成json返回页面
	 * 
	 * @param response
	 * @param obj
	 */
	protected void toJSON(HttpServletResponse response, Object obj) {
		try {
			response.setCharacterEncoding("UTF-8");
			//response.setContentType("text/json;charset=utf-8");
			System.out.println(JSONMapper.toJSON(obj));
			response.getWriter().println(JSONMapper.toJSON(obj).render(false));

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("在将对象" + obj + "转换为JSON格式字符串的时候发生异常！");
		}
	}

	protected void errorMsg(String msg, HttpServletResponse response)
			throws ServletException, IOException {
		toJSON(response, ServletMessage.createErrorMessageInstance(msg));
	}
	
	protected void errorMsg(HttpServletResponse response)
			throws ServletException, IOException {
		toJSON(response, ServletMessage.createErrorMessageInstance());
	}

	protected void successMsg(HttpServletResponse response)
			throws ServletException, IOException {
		toJSON(response, ServletMessage.createSuccessMessageInstance());
	}

}
