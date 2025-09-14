package hien.project.services;

import hien.project.models.UserModel;

public interface UserService {

	UserModel login(String username, String password);
	UserModel get(String username);
	
}
