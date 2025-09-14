package hien.project.controllers;

import hien.project.dao.impl.UserDaoImpl;
import hien.project.models.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@WebServlet(name = "ProfileController", urlPatterns = { "/profile" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024) // 1MB
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserDaoImpl userDao = new UserDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession(false);
		if (s == null || s.getAttribute("user") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		req.getRequestDispatcher("/views/user/my-account.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession(false);
		if (s == null || s.getAttribute("user") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		UserModel current = (UserModel) s.getAttribute("user");
		String username = current.getUserName();
		String fullname = req.getParameter("fullname");
		String phone = req.getParameter("phone");

		Part avatarPart = req.getPart("avatar");
		String savedFileName = current.getAvatar(); // giữ avatar cũ nếu không upload mới

		if (avatarPart != null && avatarPart.getSize() > 0 && avatarPart.getSubmittedFileName() != null) {
			// 1) xác định uploads path (thử getRealPath trước)
			String uploadsPath = getServletContext().getRealPath("/uploads");
			if (uploadsPath == null) {
				String context = getServletContext().getContextPath();
				if (context != null && context.startsWith("/"))
					context = context.substring(1);
				String catalinaBase = System.getProperty("catalina.base");
				if (catalinaBase == null)
					catalinaBase = System.getProperty("user.home");
				uploadsPath = catalinaBase + File.separator + "webapp" + File.separator + context + File.separator
						+ "uploads";
			}

			File uploadDir = new File(uploadsPath);
			if (!uploadDir.exists())
				uploadDir.mkdirs();

			String original = Paths.get(avatarPart.getSubmittedFileName()).getFileName().toString();
			String ext = "";
			int idx = original.lastIndexOf('.');
			if (idx > 0)
				ext = original.substring(idx);
			savedFileName = UUID.randomUUID().toString() + ext;

			File storeFile = new File(uploadDir, savedFileName);
			try (InputStream in = avatarPart.getInputStream()) {
				Files.copy(in, storeFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("message", "Upload file lỗi: " + e.getMessage());
				req.getRequestDispatcher("/views/user/my-account.jsp").forward(req, resp);
				return;
			}
			// log đường dẫn thực tế để kiểm tra
			System.out.println("Avatar saved to: " + storeFile.getAbsolutePath());
		}

		boolean ok = userDao.updateProfile(username, fullname, phone, savedFileName);
		if (ok) {
			UserModel updated = userDao.getByUsername(username);
			s.setAttribute("user", updated);
			req.setAttribute("message", "Cập nhật thành công");
		} else {
			req.setAttribute("message", "Cập nhật thất bại");
		}
		req.getRequestDispatcher("/views/user/my-account.jsp").forward(req, resp);
	}
}
