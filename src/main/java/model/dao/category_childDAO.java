package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.category;
import model.bean.category_child;
import util.ConnectDBLibrary;

public class category_childDAO {
	
	private ConnectDBLibrary connectDBLibrary;
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public category_childDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}
	
	public ArrayList<category_child> getItems() {
		ArrayList<category_child> ListItems = new ArrayList<category_child>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT category_child.id as childId, category_child.name as childName, id_cat, category.name,category.id"
				+ " FROM category_child INNER JOIN category ON id_cat=category.id ORDER BY childId DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("childId");
				String name = rs.getString("childName");
				category Category = new category(rs.getInt("category.id"),rs.getString("category.name"));
				category_child Category_child = new category_child(id,name,Category);
				ListItems.add(Category_child);
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
	
	public ArrayList<category_child> getItems(int obj) {
		ArrayList<category_child> ListItems = new ArrayList<category_child>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT category_child.id as childId, category_child.name as childName, id_cat, category.name,category.id"
				      + " FROM category_child INNER JOIN category ON id_cat=category.id WHERE id_cat = ? ORDER BY childId";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, obj);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("childId");
				String name = rs.getString("childName");
				category Category = new category(rs.getInt("category.id"),rs.getString("category.name"));
				category_child Category_child = new category_child(id,name,Category);
				ListItems.add(Category_child);
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
	
	public ArrayList<category_child> getItemss(int obj) {
		ArrayList<category_child> ListItems = new ArrayList<category_child>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT category_child.id as childId, category_child.name as childName, id_cat, category.name,category.id"
				      + " FROM category_child INNER JOIN category ON id_cat=category.id WHERE id_cat = ? ORDER BY childId LIMIT 5";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, obj);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("childId");
				String name = rs.getString("childName");
				category Category = new category(rs.getInt("category.id"),rs.getString("category.name"));
				category_child Category_child = new category_child(id,name,Category);
				ListItems.add(Category_child);
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

	public int addItem(String cat_child,int id) {
		int result = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "INSERT INTO category_child(name,id_cat) VALUES(?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, cat_child);
			pst.setInt(2, id);
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

	public category_child getItem(int id) {
		category_child cat_child = null;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT category_child.id, category_child.name, category_child.id_cat , category.id, category.name "
				+ "FROM category_child INNER JOIN category ON category_child.id_cat = category.id WHERE category_child.id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				category Cat = new category(rs.getInt("category.id"), rs.getString("category.name"));
				cat_child = new category_child(0,name,Cat);
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
		return cat_child;
	}

	public int editItem(int id,String category_child, int category) {
		int result = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "UPDATE `category_child` SET name = ?, id_cat = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, category_child);
			pst.setInt(2,category);
			pst.setInt(3, id);
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
		int result = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "DELETE FROM category_child WHERE id = ?";
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

	public int NumberOfItems() {
		int number = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT COUNT(*) FROM category_child";
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
