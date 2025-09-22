package hien.project.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import hien.project.dao.IUserDAO;
import hien.project.dao.impl.UserDAOImpl;
import hien.project.entity.User;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/admin/user", "/admin/user/edit", "/admin/user/create", "/admin/user/update",
		"/admin/user/delete" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserDAO userDAO = new UserDAOImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/admin/user":
			listUsers(req, resp);
			break;
		case "/admin/user/edit":
			editUser(req, resp);
			break;
		case "/admin/user/delete":
			deleteUser(req, resp);
			break;
		default:
			resp.sendRedirect(req.getContextPath() + "/admin/user");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/admin/user/create":
			createUser(req, resp);
			break;
		case "/admin/user/update":
			updateUser(req, resp);
			break;
		default:
			resp.sendRedirect(req.getContextPath() + "/admin/user");
		}
	}

	private void listUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String keyword = req.getParameter("search");
		List<User> users = (keyword != null && !keyword.isEmpty()) ? userDAO.findByKeyword(keyword) : userDAO.findAll();
		req.setAttribute("users", users);
		RequestDispatcher rd = req.getRequestDispatcher("/admin/user-list.jsp");
		rd.forward(req, resp);
	}

	private void editUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		User user = userDAO.findById(id);
		req.setAttribute("user", user);
		RequestDispatcher rd = req.getRequestDispatcher("/admin/user-form.jsp");
		rd.forward(req, resp);
	}

	private void createUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		User user = new User();
		user.setUsername(req.getParameter("username"));
		user.setFullname(req.getParameter("fullname"));
		user.setPassword(req.getParameter("password"));
		user.setRoleid(Integer.parseInt(req.getParameter("roleid")));
		userDAO.create(user);
		resp.sendRedirect(req.getContextPath() + "/admin/user");
	}

	private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		User user = userDAO.findById(id);
		user.setUsername(req.getParameter("username"));
		user.setFullname(req.getParameter("fullname"));
		user.setPassword(req.getParameter("password"));
		user.setRoleid(Integer.parseInt(req.getParameter("roleid")));
		userDAO.update(user);
		resp.sendRedirect(req.getContextPath() + "/admin/user");
	}

	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		userDAO.remove(id);
		resp.sendRedirect(req.getContextPath() + "/admin/user");
	}
}