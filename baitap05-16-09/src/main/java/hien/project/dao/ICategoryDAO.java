package hien.project.dao;

import hien.project.entity.Category;

import java.util.List;

public interface ICategoryDAO {
	Category create(Category category);

	Category update(Category category);

	Category remove(int id);

	Category findById(int id);

	List<Category> findAll();

	List<Category> findByKeyword(String keyword);
}