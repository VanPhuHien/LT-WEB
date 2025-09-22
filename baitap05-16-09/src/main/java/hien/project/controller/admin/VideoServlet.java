package hien.project.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import hien.project.dao.ICategoryDAO;
import hien.project.dao.IUserDAO;
import hien.project.dao.IVideoDAO;
import hien.project.dao.impl.CategoryDAOImpl;
import hien.project.dao.impl.UserDAOImpl;
import hien.project.dao.impl.VideoDAOImpl;
import hien.project.entity.Category;
import hien.project.entity.User;
import hien.project.entity.Video;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/admin/video", "/admin/video/edit", "/admin/video/create", "/admin/video/update",
		"/admin/video/delete" })
public class VideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IVideoDAO videoDAO = new VideoDAOImpl();
	private ICategoryDAO categoryDAO = new CategoryDAOImpl();
	private IUserDAO userDAO = new UserDAOImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/admin/video":
			listVideos(req, resp);
			break;
		case "/admin/video/edit":
			editVideo(req, resp);
			break;
		case "/admin/video/delete":
			deleteVideo(req, resp);
			break;
		default:
			resp.sendRedirect(req.getContextPath() + "/admin/video");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/admin/video/create":
			createVideo(req, resp);
			break;
		case "/admin/video/update":
			updateVideo(req, resp);
			break;
		default:
			resp.sendRedirect(req.getContextPath() + "/admin/video");
		}
	}

	private void listVideos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String keyword = req.getParameter("search");
		List<Video> videos = (keyword != null && !keyword.isEmpty()) ? videoDAO.findByKeyword(keyword)
				: videoDAO.findAll();
		req.setAttribute("videos", videos);
		RequestDispatcher rd = req.getRequestDispatcher("/admin/video-list.jsp");
		rd.forward(req, resp);
	}

	private void editVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Video video = videoDAO.findById(id);
		List<Category> categories = categoryDAO.findAll();
		List<User> users = userDAO.findAll();
		req.setAttribute("video", video);
		req.setAttribute("categories", categories);
		req.setAttribute("users", users);
		RequestDispatcher rd = req.getRequestDispatcher("/admin/video-form.jsp");
		rd.forward(req, resp);
	}

	private void createVideo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Video video = new Video();
		video.setTitle(req.getParameter("title"));
		video.setDescription(req.getParameter("description"));
		video.setUrl(req.getParameter("url"));
		video.setViews(Integer.parseInt(req.getParameter("views")));
		int catId = Integer.parseInt(req.getParameter("categoryId"));
		int userId = Integer.parseInt(req.getParameter("userId"));
		video.setCategory(categoryDAO.findById(catId));
		video.setUser(userDAO.findById(userId));
		videoDAO.create(video);
		resp.sendRedirect(req.getContextPath() + "/admin/video");
	}

	private void updateVideo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("videoId"));
		Video video = videoDAO.findById(id);
		video.setTitle(req.getParameter("title"));
		video.setDescription(req.getParameter("description"));
		video.setUrl(req.getParameter("url"));
		video.setViews(Integer.parseInt(req.getParameter("views")));
		int catId = Integer.parseInt(req.getParameter("categoryId"));
		int userId = Integer.parseInt(req.getParameter("userId"));
		video.setCategory(categoryDAO.findById(catId));
		video.setUser(userDAO.findById(userId));
		videoDAO.update(video);
		resp.sendRedirect(req.getContextPath() + "/admin/video");
	}

	private void deleteVideo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		videoDAO.remove(id);
		resp.sendRedirect(req.getContextPath() + "/admin/video");
	}
}