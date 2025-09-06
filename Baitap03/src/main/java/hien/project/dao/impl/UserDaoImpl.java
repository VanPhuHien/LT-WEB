package hien.project.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Connection;

import hien.project.connection.ConnectDB;
import hien.project.dao.UserDao;
import hien.project.models.UserModel;

public class UserDaoImpl implements UserDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	
	@Override
	public UserModel get(String username) {
		String sql = "SELECT * FROM [Users] WHERE userName = ? ";
		try {
		conn = new ConnectDB().getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		rs = ps.executeQuery();
		while (rs.next()) {
		UserModel user = new UserModel();
		user.setId(rs.getInt("id"));
		user.setEmail(rs.getString("email"));
		user.setUserName(rs.getString("username"));
		user.setFullName(rs.getString("fullname"));
		user.setPassWord(rs.getString("password"));
		user.setAvatar(rs.getString("avatar"));
		user.setRoleid(Integer.parseInt(rs.getString("roleid")));
		user.setPhone(rs.getString("phone"));
		user.setCreatedDate(rs.getDate("createdDate"));
		return user; }
		} catch (Exception e) {e.printStackTrace(); }
		return null;
	}

}
