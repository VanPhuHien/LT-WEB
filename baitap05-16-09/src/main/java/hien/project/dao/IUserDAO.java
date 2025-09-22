package hien.project.dao;

import hien.project.entity.User;

import java.util.List;

public interface IUserDAO {
	User create(User user);

	User update(User user);

	User remove(int id);

	User findById(int id);

	List<User> findAll();

	List<User> findByKeyword(String keyword);
}