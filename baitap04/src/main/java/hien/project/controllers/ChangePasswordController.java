package hien.project.controllers;

import hien.project.models.UserModel;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import hien.project.configs.ConnectDB;

@WebServlet("/change-password")
public class ChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object o = session.getAttribute("user");

		if (o == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		UserModel user = (UserModel) o;

		String oldPass = request.getParameter("oldpass");
		String newPass = request.getParameter("newpass");
		String confirmPass = request.getParameter("confirmpass");

		if (oldPass == null || newPass == null || confirmPass == null || oldPass.isEmpty() || newPass.isEmpty()
				|| confirmPass.isEmpty()) {
			request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
			request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
			return;
		}

		if (!newPass.equals(confirmPass)) {
			request.setAttribute("error", "Mật khẩu xác nhận không khớp!");
			request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
			return;
		}

		try (Connection conn = new ConnectDB().getConnection()) {
			// 1. Kiểm tra mật khẩu cũ
			String sqlCheck = "SELECT * FROM users WHERE username = ? AND password = ?";
			PreparedStatement stCheck = conn.prepareStatement(sqlCheck);
			stCheck.setString(1, user.getUserName());
			stCheck.setString(2, oldPass);
			ResultSet rs = stCheck.executeQuery();

			if (!rs.next()) {
				request.setAttribute("error", "Mật khẩu cũ không chính xác!");
				request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
				return;
			}

			// 2. Update mật khẩu mới
			String sqlUpdate = "UPDATE users SET password = ? WHERE username = ?";
			PreparedStatement stUpdate = conn.prepareStatement(sqlUpdate);
			stUpdate.setString(1, newPass);
			stUpdate.setString(2, user.getUserName());
			int rows = stUpdate.executeUpdate();

			if (rows > 0) {
				request.setAttribute("message", "Đổi mật khẩu thành công!");
			} else {
				request.setAttribute("error", "Có lỗi xảy ra, vui lòng thử lại!");
			}

			request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Lỗi hệ thống: " + e.getMessage());
			request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
		}
	}
}
