package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.bean.category_child;
import model.bean.news;
import model.bean.role;
import model.bean.user;
import util.ConnectDBLibrary;
import util.DefineUtil;

public class newsDAO {
	
	private Connection conn;
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public newsDAO() {
		connectDBLibrary = new ConnectDBLibrary();
	}

	public ArrayList<news> getItems() {
		ArrayList<news> ListItem = new ArrayList<news>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT news.id, news.name,news.date_create,news.view,category_child.name,user.name,role.name,role.id,user.id "
				+ "FROM `news` INNER JOIN category_child ON news.id_cat=category_child.id"
				+ " INNER JOIN user ON news.id_author=user.id INNER JOIN role ON user.id_role=role.id ORDER BY news.id DESC ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("news.id");
				String name_news = rs.getString("news.name");
				Timestamp date_create = rs.getTimestamp("news.date_create");
				int view = rs.getInt("news.view");
				String name_cat = rs.getString("category_child.name");
				category_child cat_child = new category_child(0, name_cat, null);
				int id_author = rs.getInt("user.id");
				String author = rs.getString("user.name");
				int role_id = rs.getInt("role.id");
				String role_name = rs.getString("role.name");
				role Role = new role(role_id, role_name);
				user User = new user(id_author, author, "", null, "", Role);
				news News = new news(id, name_news, view, date_create, "", "", cat_child, User);
				ListItem.add(News);	
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
		return ListItem;
	}

	public int delItem(int id) {
		int result = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "	DELETE FROM news WHERE id = ?";
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

	public news getItems(int id) {
		news Item = null;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT * FROM news WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				String picture =rs.getString("picture");
				String detail = rs.getString("detail");
				int id_cat = rs.getInt("id_cat");
				int id_author = rs.getInt("id_author");
				user author = new user(id_author, "", "", null, "", null);
				category_child cat_child = new category_child(id_cat, "", null);
				Item = new news(id, name, 0, null, picture, detail, cat_child, author);
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
		return Item;
	}

	public int editItem(news news) {
		int result = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "UPDATE news SET name = ? , picture = ?, detail = ?, id_cat = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, news.getName());
			pst.setNString(2, news.getPicture());
			pst.setString(3, news.getDetail());
			pst.setInt(4, news.getCat().getId());
			pst.setInt(5, news.getId());
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

	public news addItems() {
		// TODO Auto-generated method stub
		return null;
	}

	public int addItem(news news) {
		int result = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "INSERT INTO `news`(`name`, `picture`, `detail`, `id_cat`, `id_author`) "
				       + "VALUES (?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, news.getName());
			pst.setString(2, news.getPicture());
			pst.setString(3, news.getDetail());
			pst.setInt(4, news.getCat().getId());
			pst.setInt(5, news.getAuthor().getId());
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
		String sql = "SELECT COUNT(*) FROM news";
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

	public int delNewsOfCat(int id) {
		int result = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql= "DELETE FROM news WHERE id_cat = ?";
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

	public news getLastestItems() {
		news Item = null;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT * FROM `news` ORDER BY id DESC LIMIT 1";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String picture =rs.getString("picture");
				String detail = rs.getString("detail");
				int id_cat = rs.getInt("id_cat");
				int id_author = rs.getInt("id_author");
				user author = new user(id_author, "", "", null, "", null);
				category_child cat_child = new category_child(id_cat, "", null);
				Item = new news(id, name, 0, null, picture, detail, cat_child, author);
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
		return Item;
	}

	public ArrayList<news> getItems(int i, int j) {
		ArrayList<news> ListItem = new ArrayList<news>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT * FROM `news` ORDER BY id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, i);
			pst.setInt(2, j);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Timestamp date_create = rs.getTimestamp("date_create");
				String picture = rs.getString("picture");
				String detail = rs.getString("detail");
				int view = rs.getInt("view");
				news News = new news(id, name, view, date_create, picture, detail, null, null);
				ListItem.add(News);
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
		return ListItem;
	}
	
	public ArrayList<news> getItemsOfCat(int id){
		ArrayList<news> ListItems = new ArrayList<news>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT * FROM `news` INNER JOIN category_child ON news.id_cat = category_child.id "
				+ "INNER JOIN category ON category_child.id_cat = category.id WHERE category.id = ? ORDER BY news.id DESC LIMIT 2";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id_news = rs.getInt("news.id");
				String name = rs.getString("news.name");
				String picture = rs.getString("news.picture");
				String detail = rs.getString("news.detail");
				news News = new news(id_news, name, 0, null, picture, detail, null, null);
				ListItems.add(News);
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
	
	public ArrayList<news> getTrendding(){
		ArrayList<news> ListItem = new ArrayList<news>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT * FROM news ORDER BY view DESC LIMIT 5";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				news News = new news(id, name, 0, null, "", "", null, null);
				ListItem.add(News);
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
		return ListItem;
	}

	public ArrayList<news> getItemsOfCatChild(int offset, int id) {
		ArrayList<news> ListItem = new ArrayList<news>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT * FROM news WHERE id_cat = ? ORDER BY id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				int idNew = rs.getInt("id");
				String name = rs.getString("name");
				String picture = rs.getString("picture");
				String detail = rs.getString("detail");
				news News = new news(idNew, name, 0, null, picture, detail, null, null);
				ListItem.add(News);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}	
		
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ListItem;
	}

	public int NumberOfItems(int id) {
		int number = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT COUNT(*) FROM news WHERE id_cat = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
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

	public news getItem(int id) {
		news News = null;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT * FROM news WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				String detail = rs.getString("detail");
				String picture = rs.getString("picture");
				int view = rs.getInt("view");
				int id_cat = rs.getInt("id_cat");
				category_child cat_child = new category_child(id_cat, "", null);
				Timestamp date_create = rs.getTimestamp("date_create");
				News = new news(id, name, view, date_create, picture, detail, cat_child, null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return News;
	}

	public ArrayList<news> getRelateItems(int id_cat, int id) {
		ArrayList<news> ListItem = new ArrayList<news>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT * FROM news WHERE id_cat = ? AND id != ? ORDER BY id DESC LIMIT 2";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id_cat);
			pst.setInt(2, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id_news = rs.getInt("id");
				String name = rs.getString("name");
				news News = new news(id_news, name, 0, null, "", "", null, null);
				ListItem.add(News);
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
		return ListItem;
	}

	public void IncreaseView(int id) {
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "UPDATE `news` SET view = view + 1 WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
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
	}

	public int NumberOfItems(String search) {
		int number = 0;
		String name = "%"+search+"%";
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT COUNT(*) FROM news WHERE name LIKE ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
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

	public ArrayList<news> getItems(String search) {
		ArrayList<news> ListItems = new ArrayList<news>();
		String name = "%"+search+"%";
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT * FROM news WHERE name LIKE ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String Name = rs.getString("name");
				String detail = rs.getString("detail");
				String picture = rs.getString("picture");
				news News = new news(id, Name, 0, null, picture, detail, null, null);
				ListItems.add(News);
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
