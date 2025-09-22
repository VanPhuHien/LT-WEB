package hien.project.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import hien.project.dao.ICategoryDAO;
import hien.project.dao.impl.CategoryDAOImpl;
import hien.project.entity.Category;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(urlPatterns = { "/admin/category", "/admin/category/edit", "/admin/category/create",
		"/admin/category/update", "/admin/category/delete" })
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICategoryDAO categoryDAO = new CategoryDAOImpl();
	private static final String UPLOAD_DIR = "uploads";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/admin/category":
			listCategories(req, resp);
			break;
		case "/admin/category/edit":
			editCategory(req, resp);
			break;
		case "/admin/category/delete":
			deleteCategory(req, resp);
			break;
		default:
			resp.sendRedirect(req.getContextPath() + "/admin/category");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/admin/category/create":
			createCategory(req, resp);
			break;
		case "/admin/category/update":
			updateCategory(req, resp);
			break;
		default:
			resp.sendRedirect(req.getContextPath() + "/admin/category");
		}
	}

	private void listCategories(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String keyword = req.getParameter("search");
		List<Category> categories = (keyword != null && !keyword.isEmpty()) ? categoryDAO.findByKeyword(keyword)
				: categoryDAO.findAll();
		req.setAttribute("categories", categories);
		RequestDispatcher rd = req.getRequestDispatcher("/admin/category-list.jsp");
		rd.forward(req, resp);
	}

	private void editCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("cateId"));
		Category category = id == 0 ? new Category() : categoryDAO.findById(id);
		req.setAttribute("category", category);
		RequestDispatcher rd = req.getRequestDispatcher("/admin/category-form.jsp");
		rd.forward(req, resp);
	}

	private void createCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Category category = new Category();
		category.setCateName(req.getParameter("cateName"));

		// Xử lý upload file
		Part filePart = req.getPart("iconFile"); 
		String fileName = processUpload(filePart);
		category.setIcons(fileName != null ? fileName : "");

		categoryDAO.create(category);
		resp.sendRedirect(req.getContextPath() + "/admin/category");
	}

	private void updateCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(req.getParameter("cateId"));
		Category category = categoryDAO.findById(id);
		category.setCateName(req.getParameter("cateName"));

		// Xử lý upload file (có thể giữ file cũ nếu không upload mới)
		Part filePart = req.getPart("iconFile");
		String fileName = processUpload(filePart);
		if (fileName != null) {
			category.setIcons(fileName);
		}

		categoryDAO.update(category);
		resp.sendRedirect(req.getContextPath() + "/admin/category");
	}

	private void deleteCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("cateId"));
		categoryDAO.remove(id);
		resp.sendRedirect(req.getContextPath() + "/admin/category");
	}

	private String processUpload(Part filePart) throws IOException {
		if (filePart != null && filePart.getSize() > 0) {
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();

			filePart.write(uploadPath + File.separator + fileName);
			return fileName;
		}
		return null;
	}
}
