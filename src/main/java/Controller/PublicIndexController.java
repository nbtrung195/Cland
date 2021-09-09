package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.category;
import model.bean.news;
import model.dao.categoryDAO;
import model.dao.newsDAO;

/**
 * Servlet implementation class PublicIndexController
 */
@WebServlet("/home")
public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private newsDAO NewsDAO;   
    private categoryDAO CategoryDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicIndexController() {
        super();
        NewsDAO = new newsDAO();
        CategoryDAO = new categoryDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		news LastestItem = NewsDAO.getLastestItems();
		ArrayList<news> ListItem1 = NewsDAO.getItems(1,3);
		ArrayList<news> ListItem2 = NewsDAO.getItems(4,7);
        ArrayList<category> ListCategory = CategoryDAO.getItems();
		request.setAttribute("LastestItem", LastestItem);
		request.setAttribute("ListItem1", ListItem1);
		request.setAttribute("ListItem2", ListItem2);
		request.setAttribute("ListCategory", ListCategory);
		RequestDispatcher rd = request.getRequestDispatcher("/public/index.jsp");
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
