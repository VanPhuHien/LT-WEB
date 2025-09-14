package hien.project.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import hien.project.configs.ConnectDB;

/**
 * Servlet implementation class ForgotPasswordServlet
 */
@WebServlet(urlPatterns = { "/forgot" })
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForgotPasswordController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Hiển thị form quên mật khẩu
		request.getRequestDispatcher("/views/forgot.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");
		String newpass = request.getParameter("newpass");
		String confirm = request.getParameter("confirm");

		if (email == null || email.trim().isEmpty()) {
			request.setAttribute("error", "Vui lòng nhập email.");
			request.getRequestDispatcher("/views/forgot.jsp").forward(request, response);
			return;
		}

		try (Connection conn = new ConnectDB().getConnection()) {
			// Kiểm tra email có tồn tại không
			String checkSql = "SELECT userName FROM Users WHERE email = ?";
			String username = null;
			try (PreparedStatement ps = conn.prepareStatement(checkSql)) {
				ps.setString(1, email);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						username = rs.getString("userName");
					}
				}
			}

			if (username == null) {
				request.setAttribute("error", "Email không tồn tại trong hệ thống!");
				request.setAttribute("email", email);
				request.getRequestDispatcher("/views/forgot.jsp").forward(request, response);
				return;
			}

			// Nếu chưa, nhập newpass.
			if (newpass == null || confirm == null || newpass.trim().isEmpty() || confirm.trim().isEmpty()) {
				request.setAttribute("email", email);
				request.setAttribute("info", "Email tồn tại. Vui lòng nhập mật khẩu mới.");
				request.getRequestDispatcher("/views/forgot.jsp").forward(request, response);
				return;
			}

			// Kiểm tra confirm
			if (!newpass.equals(confirm)) {
				request.setAttribute("error", "Mật khẩu xác nhận không khớp!");
				request.setAttribute("email", email);
				request.getRequestDispatcher("/views/forgot.jsp").forward(request, response);
				return;
			}

			// Cập nhật mật khẩu
			String updateSql = "UPDATE Users SET password = ? WHERE email = ?";
			try (PreparedStatement ps = conn.prepareStatement(updateSql)) {
				ps.setString(1, newpass);
				ps.setString(2, email);
				int updated = ps.executeUpdate();
				if (updated <= 0) {
					request.setAttribute("error", "Không thể cập nhật mật khẩu. Vui lòng thử lại.");
					request.getRequestDispatcher("/views/forgot.jsp").forward(request, response);
					return;
				}
			}

			// Thành công, trở về trang login
			request.setAttribute("message", "Đổi mật khẩu thành công. Vui lòng đăng nhập.");
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Lỗi server: " + e.getMessage());
			request.getRequestDispatcher("/views/forgot.jsp").forward(request, response);
		}
	}

}
