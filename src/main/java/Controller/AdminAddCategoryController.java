package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.category;
import model.bean.user;
import model.dao.categoryDAO;
import model.dao.category_childDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminAddCategoryController
 */
@WebServlet("/admin/category/add")
public class AdminAddCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private categoryDAO catDAO; 
    private category_childDAO cat_childDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddCategoryController() {
        super();
       catDAO = new categoryDAO();
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
		if(who.getRole().getId()==2||who.getRole().getId()==3) {
			response.sendRedirect(request.getContextPath()+"/admin/category");
			return;
		}
		ArrayList<category> ListItems = catDAO.getItems();
		request.setAttribute("ListItems", ListItems);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/category/add.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String cat_child = request.getParameter("cat_child");
		int id_cat = Integer.parseInt(request.getParameter("category")) ;
		int result = cat_childDAO.addItem(cat_child,id_cat);
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/admin/category?msg=1");
			return;
		}
		else {
			response.sendRedirect(request.getContextPath()+"/admin/category/add?err=1");
			return;
		}
	}

}
