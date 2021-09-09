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
 * Servlet implementation class AdminAddNewsController
 */
@WebServlet("/admin/news/add")
@MultipartConfig
public class AdminAddNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private newsDAO NewsDAO;  
    private category_childDAO cat_childDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddNewsController() {
        super();
        NewsDAO = new newsDAO();
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
		ArrayList<category_child> ListItem = cat_childDAO.getItems();
		request.setAttribute("ListItem", ListItem);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/add.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text-html");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		user author = (user)session.getAttribute("Login");
		String news = request.getParameter("news");
		int category = Integer.parseInt(request.getParameter("category"));
		category_child cat_child = new category_child(category, "", null);
		String detail = request.getParameter("detail");
		Part filePart = request.getPart("picture");
		final String dirPartName = request.getServletContext().getRealPath("files");
		File dirFile = new File(dirPartName);
	    if(!dirFile.exists()) {
	    	dirFile.mkdir();
	    }
	    String fileName = FileUtil.getName(filePart);
	    String picture = FileUtil.rename(fileName);
	    String filePartName = dirPartName + File.separator + picture;
		news News = new news(0, news, 0, null, picture, detail, cat_child, author);
		int result = NewsDAO.addItem(News);
		if(result > 0) {
			if(!fileName.isEmpty()) {
	    		filePart.write(filePartName);
	    	}
			response.sendRedirect(request.getContextPath()+"/admin/news?msg=3");
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/news/add?msg=1");
			return;
		}
	}

}
