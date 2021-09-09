package Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.bean.category_child;
import model.bean.news;
import model.bean.user;
import model.dao.category_childDAO;
import model.dao.newsDAO;
import util.AuthUtil;
import util.FileUtil;

/**
 * Servlet implementation class AdminEditNewsController
 */
@MultipartConfig
@WebServlet("/admin/news/edit")
public class AdminEditNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private newsDAO NewDAO;   
    private category_childDAO cat_childDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditNewsController() {
        super();
        NewDAO = new newsDAO();
        cat_childDAO = new category_childDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/signin");
			return;
		}
		HttpSession session = request.getSession();
		user who = (user)session.getAttribute("Login");
		int id = 0; 
		try {
			id =Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/news?err=2");
			return;
		}
		news News = NewDAO.getItems(id);
		if(News == null) {
			response.sendRedirect(request.getContextPath()+"/admin/news?err=2");
			return;
		}
		if(who.getRole().getId()==3) {
			if(who.getId()==News.getAuthor().getId()) {
				ArrayList<category_child> ListItem = cat_childDAO.getItems();
				request.setAttribute("News", News);
				request.setAttribute("who", who);
				request.setAttribute("ListItem", ListItem);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/news/edit.jsp");
				rd.forward(request, response);
				return;
			}
			else {
				response.sendRedirect(request.getContextPath()+"/admin/news");
				return;
			}
		}
		if(who.getRole().getId()==2) {
			if(News.getAuthor().getId()==1) {
				response.sendRedirect(request.getContextPath()+"/admin/news");
				return;
			}
		}
		ArrayList<category_child> ListItem = cat_childDAO.getItems();
		request.setAttribute("News", News);
		request.setAttribute("who", who);
		request.setAttribute("ListItem", ListItem);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/edit.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		String news = request.getParameter("news");
		int category = Integer.parseInt(request.getParameter("category_new"));
		category_child cat_child = new category_child(category, "", null);
		String detail = request.getParameter("detail");
		Part filePart = request.getPart("picture");
		news News = NewDAO.getItems(id);
		final String dirPartName = request.getServletContext().getRealPath("files");
		File dirFile = new File(dirPartName);
	    if(!dirFile.exists()) {
	    	dirFile.mkdir();
	    }
	    String fileName = FileUtil.getName(filePart);
	    String picture = "";
	    if(fileName.isEmpty()) {
	    	picture = News.getPicture();
	    }
	    else {
	    	picture = FileUtil.rename(fileName);
	    }
	    String filePartName = dirPartName + File.separator + picture;
		news Newss = new news(id, news, 0, null, picture, detail, cat_child, null);
		int result = NewDAO.editItem(Newss);
		if(result>0) {
			if(!fileName.isEmpty()) {
	    		String oldFliePathName = dirPartName + File.separator + News.getPicture();
	    		File oldFile = new File(oldFliePathName);
	    		if(oldFile.exists()) {
	    			oldFile.delete();
	    		}
	    		filePart.write(filePartName);
	    	}
			response.sendRedirect(request.getContextPath()+"/admin/news?msg=2");
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/news/edit?err=1");
			return;
		}
	}

}
