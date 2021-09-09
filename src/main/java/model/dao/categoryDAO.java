package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.category;
import util.ConnectDBLibrary;

public class categoryDAO {
	
	private Connection conn;
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public categoryDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}

	public ArrayList<category> getItems() {
		ArrayList<category> ListItems = new ArrayList<category>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT * FROM category";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				category Category = new category(id, name);
				ListItems.add(Category);
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
