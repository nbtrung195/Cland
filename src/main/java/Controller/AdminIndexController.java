package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.category_childDAO;
import model.dao.commentDAO;
import model.dao.newsDAO;
import model.dao.userDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminIndexController
 */
@WebServlet("/admin/index")
public class AdminIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private userDAO UserDAO;   
    private newsDAO NewsDAO;
    private category_childDAO Cat_ChildDAO;
    private commentDAO CommentDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexController() {
        super();
        UserDAO = new userDAO();
        NewsDAO = new newsDAO();
        Cat_ChildDAO = new category_childDAO();
        CommentDAO = new commentDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/signin");
			return;
		}
		int NumNews = NewsDAO.NumberOfItems();
		int NumUser = UserDAO.NumberOfItems();
		int NumCat = Cat_ChildDAO.NumberOfItems();
		int NumCmt = CommentDAO.NumberOfItems();
		request.setAttribute("NumNews", NumNews);
		request.setAttribute("NumUser", NumUser);
		request.setAttribute("NumCat", NumCat);
		request.setAttribute("NumCmt", NumCmt);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
