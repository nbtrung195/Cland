package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.news;
import model.dao.newsDAO;

/**
 * Servlet implementation class PublicSearchController
 */
@WebServlet("/search")
public class PublicSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private newsDAO NewsDAO;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicSearchController() {
        super();
        NewsDAO = new newsDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String search = request.getParameter("search");
		int NumberOfItems = NewsDAO.NumberOfItems(search);
		ArrayList<news> ListItems = NewsDAO.getItems(search);
		request.setAttribute("NumberOfItems", NumberOfItems);
		request.setAttribute("ListItems", ListItems);
		RequestDispatcher rd= request.getRequestDispatcher("/public/search.jsp");
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
