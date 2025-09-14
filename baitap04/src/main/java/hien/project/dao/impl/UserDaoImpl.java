package hien.project.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import hien.project.configs.ConnectDB;
import hien.project.dao.UserDao;
import hien.project.models.UserModel;

public class UserDaoImpl implements UserDao {

	public UserModel login(String username, String password) {
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (Connection conn = new ConnectDB().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapUser(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserModel getByUsername(String username) {
        String sql = "SELECT * FROM Users WHERE username = ?";
        try (Connection conn = new ConnectDB().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapUser(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateProfile(String username, String fullname, String phone, String avatarFileName) {
        String sql = "UPDATE Users SET fullname = ?, phone = ?, avatar = ? WHERE username = ?";
        try (Connection conn = new ConnectDB().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fullname);
            ps.setString(2, phone);
            ps.setString(3, avatarFileName);
            ps.setString(4, username);
            int n = ps.executeUpdate();
            return n > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private UserModel mapUser(ResultSet rs) throws SQLException {
        UserModel u = new UserModel();
        u.setId(rs.getInt("id"));
        u.setEmail(rs.getString("email"));
        u.setUserName(rs.getString("username"));
        u.setFullName(rs.getString("fullname"));
        u.setPassWord(rs.getString("password"));
        u.setAvatar(rs.getString("avatar"));
        u.setRoleid(rs.getInt("roleid"));
        u.setPhone(rs.getString("phone"));
        u.setCreatedDate(rs.getDate("createdDate"));
        return u;
    }

	@Override
	public UserModel get(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
