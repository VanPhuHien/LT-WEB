package hien.project.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import hien.project.models.UserModel;
import hien.project.services.UserService;
import hien.project.services.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginController
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_USERNAME = "username";
	public static final String COOKIE_REMEMBER = "username";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");

		boolean isRememberMe = "on".equals(remember);

		String alertMsg = "";

		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
			alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			return;
		}

		UserService service = new UserServiceImpl();
		UserModel user = service.login(username, password);
		if (user != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("account", user);


			if (isRememberMe) {
				saveRememberMe(response, username);
			}

			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}

		else {
			alertMsg = "Tài khoản hoặc mật khẩu không đúng";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		}
	}

	private void saveRememberMe(HttpServletResponse response, String username) {
		Cookie cookie = new Cookie(COOKIE_REMEMBER, username);
		cookie.setMaxAge(30 * 60);
		response.addCookie(cookie);
	}

}
