package hien.project.dao;

import hien.project.models.UserModel;

public interface UserDao {
	UserModel get(String username);

}
