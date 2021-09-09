package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.category_child;
import model.bean.news;
import model.dao.category_childDAO;
import model.dao.newsDAO;
import util.DefineUtil;

/**
 * Servlet implementation class PublicCategoryController
 */
@WebServlet("/category")
public class PublicCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private newsDAO NewsDAO; 
    private category_childDAO Cat_childDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicCategoryController() {
        super();
        NewsDAO = new newsDAO();
        Cat_childDAO = new category_childDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		int NumberOfItems = NewsDAO.NumberOfItems(id);
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			currentPage = 1;
		}
		int NumberOfPages = (int)Math.ceil((float)NumberOfItems/DefineUtil.NUMBER_PER_PAGE);
		if(currentPage<=0||currentPage>NumberOfPages) {
			currentPage = 1;
		}
		int offset = (currentPage - 1)*DefineUtil.NUMBER_PER_PAGE;
		
		category_child cat_child = Cat_childDAO.getItem(id);
		if(cat_child == null) {
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		ArrayList<news> ListNewsOfCatChild = NewsDAO.getItemsOfCatChild(offset,id);
		request.setAttribute("cat_child", cat_child);
		request.setAttribute("NumberOfPages", NumberOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("ListNewsOfCatChild", ListNewsOfCatChild);
		RequestDispatcher rd = request.getRequestDispatcher("/public/category.jsp");
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
