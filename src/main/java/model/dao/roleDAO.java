package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.role;
import util.ConnectDBLibrary;

public class roleDAO {
	
	private Connection conn;
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public roleDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}

	public ArrayList<role> getItems() {
		ArrayList<role> ListItems = new ArrayList<role>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT * FROM role";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				role Role = new role(id, name);
				ListItems.add(Role);
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
		return ListItems;
	}
}
