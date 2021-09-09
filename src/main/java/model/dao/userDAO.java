package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.role;
import model.bean.user;
import util.ConnectDBLibrary;

public class userDAO {
	
	private Connection conn;
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public userDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}

	public ArrayList<user> getItems() {
		ArrayList<user> ListItems = new ArrayList<user>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT user.id, user.name, user.pass,user.email,user.birth,user.id_role, role.id,role.name "
				+ "FROM `user` INNER JOIN role ON user.id_role=role.id ORDER BY user.id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("user.id");
				String name = rs.getString("user.name");
				String pass = rs.getString("user.pass");
				String email = rs.getString("user.email");
				Date birth = rs.getDate("user.birth");
				role Role = new role(rs.getInt("role.id"), rs.getString("role.name"));
				user User = new user(id, name, pass, birth, email, Role);
				ListItems.add(User);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ListItems;
	}

	public int addItem(user user) {
		int result = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "INSERT INTO user(`name`, `pass`, `email`, `birth`, `id_role`) VALUES(?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1,user.getName());
			pst.setString(2,user.getPass());
			pst.setString(3,user.getEmail());
			pst.setDate(4,user.getBirth());
			pst.setInt(5,user.getRole().getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int delItem(int id) {
		int result =0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql ="DELETE FROM user WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public user getItems(int id) {
		user User = null;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT user.id, user.name,user.pass,user.email,user.birth,user.id_role, role.name "
				+ "FROM `user` INNER JOIN role ON user.id_role = role.id WHERE user.id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				String name = rs.getString("user.name");
				String pass = rs.getString("user.pass");
				String email = rs.getString("user.email");
				Date date = rs.getDate("user.birth");
				role Role = new role(rs.getInt("user.id_role"), rs.getString("role.name"));
				User = new user(id, name, pass, date, email, Role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return User;
	}

	public int editItem(user user) {
		int result = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "UPDATE user SET name = ? , pass = ?, email = ?, birth = ?, id_role = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getName());
			pst.setString(2, user.getPass());
			pst.setString(3, user.getEmail());
			pst.setDate(4, user.getBirth());
			pst.setInt(5, user.getRole().getId());
			pst.setInt(6, user.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int hasUser(String name) {
		int hasUser = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT * FROM user WHERE name = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
			if(rs.next()) {
				hasUser++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return hasUser;
	}

	public user checkUser(String name, String pass) {
		user User = null;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT user.id, user.name, user.id_role, role.name FROM `user` INNER JOIN role ON user.id_role=role.id "
				       + "WHERE user.name = ?  AND user.pass = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, pass);
			rs = pst.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("user.id_role");
				String role_name = rs.getString("role.name");
				role Role = new role(id, role_name);
				String user_name = rs.getString("user.name");
				int userId = rs.getInt("user.id");
				User = new user(userId, user_name, "", null, "", Role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return User;
	}

	public int NumberOfItems() {
		int number = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT COUNT(*) FROM user";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				number = rs.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return number;
	}
}
