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
import model.bean.category_child;
import model.bean.user;
import model.dao.categoryDAO;
import model.dao.category_childDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminEditCategoryController
 */
@WebServlet("/admin/category/edit")
public class AdminEditCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private category_childDAO cat_chilDAO;   
    private categoryDAO catDAO; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditCategoryController() {
        super();
        cat_chilDAO = new category_childDAO();
        catDAO = new categoryDAO();
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
		int id = 0; 
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/category?err=2");
			return;
		}
		category_child cat_child = cat_chilDAO.getItem(id);
		if(cat_child == null) {
			response.sendRedirect(request.getContextPath()+"/admin/category?err=2");
			return;
		}
		request.setAttribute("cat_child", cat_child);
		ArrayList<category> ListItems = catDAO.getItems();
        request.setAttribute("ListItems", ListItems);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/category/edit.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String category_child = request.getParameter("category_child");
		int id = Integer.parseInt(request.getParameter("id"));
		int category = Integer.parseInt(request.getParameter("category")) ;
		int result = 0;
		result = cat_chilDAO.editItem(id,category_child,category);
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/admin/category?msg=2");
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/category/edit?err=1");
			return;
		}
	}

}
