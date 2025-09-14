package hien.project.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import hien.project.dao.impl.UserDaoImpl;
import hien.project.models.UserModel;

@WebServlet(name = "LoginController", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final UserDaoImpl userDao = new UserDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		UserModel u = userDao.login(username, password);
		if (u != null) {
			HttpSession session = req.getSession();
			session.setAttribute("user", u);
			resp.sendRedirect(req.getContextPath() + "/home");
		} else {
			req.setAttribute("error", "Sai username hoặc password");
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		}
	}
}
