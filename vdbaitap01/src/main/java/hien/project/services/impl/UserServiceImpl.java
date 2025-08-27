package hien.project.services.impl;

import hien.project.dao.UserDao;
import hien.project.dao.impl.UserDaoImpl;
import hien.project.models.UserModel;
import hien.project.services.UserService;

public class UserServiceImpl implements UserService {

	UserDao userDao = new UserDaoImpl();

	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.get(username);
		if (user != null && password.equals(user.getPassWord())) {
			return user;
		}

		return null;
	}

	@Override
	public UserModel get(String username) {
		return userDao.get(username);
	}

}
