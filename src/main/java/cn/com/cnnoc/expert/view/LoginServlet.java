package cn.com.cnnoc.expert.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.cnnoc.expert.dao.UserDao;
import cn.com.cnnoc.expert.model.User;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class LoginServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	private int number;
	private String codes;
	private UserDao userDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		width = Integer.parseInt(config.getInitParameter("width"));
		height = Integer.parseInt(config.getInitParameter("height"));
		number = Integer.parseInt(config.getInitParameter("number"));
		codes = config.getInitParameter("codes");
	}

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String safecode = request.getParameter("checkcode");
		// if(!CommonUtil.isNullOrBlank(safecode)){
		//
		// if(!safecode.equalsIgnoreCase(safecodeFromSession)){
		// request.setAttribute("error", "��֤�벻��ȷ��");
		// request.getRequestDispatcher("/backend/login.jsp").forward(request,
		// response);
		// return;
		// }
		//
		// }else{
		// request.setAttribute("error", "��֤�벻��Ϊ�գ�");
		// request.getRequestDispatcher("/backend/login.jsp").forward(request,
		// response);
		// return;
		// }

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDao.findUserByUsername(username);
		if (user == null) {
			writeJson(response, "{\"status\":\"error\",\"msg\":\"用户名" + username + "不存在!\"}");
			return;
		} else if (!password.equals(user.getPassword())) {
			writeJson(response,  "{\"status\":\"error\",\"msg\":\"用户名" + username + "密码错误!\"}");
			return;
		}

		request.getSession().setAttribute("USER_ADMIN", username);
		writeJson(response,  "{\"status\":\"ok\"}");
		//response.sendRedirect(request.getContextPath() + "/backend/main.jsp");

	}

	@SuppressWarnings("restriction")
	public void checkcode(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("image/jpeg");

		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width - 1, height - 1);

		Random random = new Random();

		int x = (width - 1) / number;
		int y = height - 4;

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < number; i++) {
			String code = String.valueOf(codes.charAt(random.nextInt(codes
					.length())));
			sb.append(code);
			int red = random.nextInt(255);
			int green = random.nextInt(255);
			int blue = random.nextInt(255);
			g.setColor(new Color(red, green, blue));

			Font font = new Font("Arial", Font.PLAIN,
					random(height / 2, height));
			g.setFont(font);

			g.drawString(code, i * x + 1, y);
		}

		request.getSession().setAttribute("safecode", sb.toString());
		System.out.println(sb.toString());

		for (int i = 0; i < 50; i++) {
			int red = random.nextInt(255);
			int green = random.nextInt(255);
			int blue = random.nextInt(255);
			g.setColor(new Color(red, green, blue));
			g.drawOval(random.nextInt(width), random.nextInt(height), 1, 1);
		}

		OutputStream out = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image);

		out.flush();
		out.close();

	}

	public void logOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getSession().invalidate();

		response.sendRedirect(request.getContextPath() + "/backend/login.jsp");
	}

	/**
	 * @param min
	 * @param max
	 * @return
	 */
	private int random(int min, int max) {
		int m = new Random().nextInt(999999) % (max - min);
		return m + min;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
