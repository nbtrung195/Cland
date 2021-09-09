package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.bean.comment;
import model.bean.news;
import model.bean.user;
import util.ConnectDBLibrary;

public class commentDAO {
	
	private Connection conn;
	private ConnectDBLibrary connectDBLibrary;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public commentDAO(){
		connectDBLibrary = new ConnectDBLibrary();
	}

	public ArrayList<comment> getItems() {
		ArrayList<comment> ListItem = new ArrayList<comment>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT comment.id, comment.content,comment.date,user.name,news.name FROM comment INNER JOIN user"
				+ "  ON comment.id_reader = user.id INNER JOIN news ON comment.id_news = news.id ORDER BY id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("comment.id");
				String name_reader = rs.getString("user.name"); 
				user reader = new user(0, name_reader, "", null, "", null);
				String content = rs.getString("comment.content");
				Timestamp time = rs.getTimestamp("comment.date");
				String name_news = rs.getString("news.name");
				news News = new news(0, name_news, 0, null, "", "", null, null);
				comment cmt = new comment(id, reader, content, time, News);
				ListItem.add(cmt);
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
		String sql = "DELETE FROM `comment` WHERE id = ?";
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
		String sql = "SELECT COUNT(*) FROM comment";
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

	public int NumberOfComment(int id) {
		int number = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT COUNT(*) FROM comment WHERE id_news = ?";
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

	public ArrayList<comment> getCommentById(int id) {
		ArrayList<comment> ListComment = new ArrayList<comment>();
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "SELECT comment.id, comment.id_reader,comment.content, comment.date, comment.id_news, user.name"
				      + " FROM comment INNER JOIN user ON comment.id_reader = user.id WHERE comment.id_news = ?;";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id_cmt = rs.getInt("comment.id");
				String content = rs.getString("comment.content");
				Timestamp date = rs.getTimestamp("comment.date");
				String name = rs.getString("user.name");
				user User = new user(0, name, "", null, "", null);
				comment cmt = new comment(id_cmt, User, content, date, null);
				ListComment.add(cmt);
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
		return ListComment;
	}

	public int addComment(comment bl) {
		int result = 0;
		conn = connectDBLibrary.getConnectDBLibrary();
		String sql = "INSERT INTO `comment`(`id_reader`, `content`, `id_news`) VALUES (?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, bl.getReader().getId());
			pst.setString(2, bl.getContent());
			pst.setInt(3, bl.getNews().getId());
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
		return result ;
	}
	
	
}
