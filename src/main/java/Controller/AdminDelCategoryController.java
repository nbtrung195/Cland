package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.user;
import model.dao.category_childDAO;
import model.dao.newsDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminDelCategoryController
 */
@WebServlet("/admin/category/delete")
public class AdminDelCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private category_childDAO cat_childDAO;  
    private newsDAO NewsDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelCategoryController() {
        super();
        cat_childDAO = new category_childDAO();
        NewsDAO = new newsDAO();
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
			response.sendRedirect(request.getContextPath()+"/admin/category?err=1");
			return;
		}			
		int result_news = NewsDAO.delNewsOfCat(id);
		//xoa ko dc
		if(result_news>0) {
			cat_childDAO.delItem(id);
			response.sendRedirect(request.getContextPath()+"/admin/category?msg=3");
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/category?err=1");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
