package hien.project.services.impl;
import java.io.File;
import java.util.List;
import hien.project.dao.CategoryDAO;
import hien.project.dao.impl.CategoryDAOImpl;
import hien.project.models.Category;
import hien.project.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	CategoryDAO categoryDao = new CategoryDAOImpl();
	
	@Override
	public void insert(Category category) {
		categoryDao.insert(category);
		
	}

	@Override
	public void edit(Category newCategory) {
		Category oldCategory = categoryDao.get(newCategory.getCateid());
		oldCategory.setCatename(newCategory.getCatename());
		if (newCategory.getIcon() != null) {
		// XOA ANH CU DI
		String fileName = oldCategory.getIcon();
		final String dir = "E:\\upload";
		File file = new File(dir + "/category" + fileName);
		if (file.exists()) {
		file.delete();
		}
		oldCategory.setIcon(newCategory.getIcon());
		}
		categoryDao.edit(oldCategory);
		
	}

	@Override
	public void delete(int id) {
		categoryDao.delete(id);
		
	}

	@Override
	public Category get(int id) {
		return categoryDao.get(id);
	}

	@Override
	public Category get(String name) {
		return categoryDao.get(name);
	}

	@Override
	public List<Category> getAll() {
		return categoryDao.getAll();

	}

	@Override
	public List<Category> search(String keyword) {
		return categoryDao.search(keyword);
	}

}
