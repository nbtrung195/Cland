package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.news;
import model.bean.user;
import model.dao.newsDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminDelNewsController
 */
@WebServlet("/admin/news/del")
public class AdminDelNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private newsDAO NewsDAO;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelNewsController() {
        super();
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
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id")) ;
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/news?err=1");
			return;
		}
		news News = NewsDAO.getItems(id);
		if(who.getRole().getId()==3) {
			if(who.getId()==News.getAuthor().getId()) {
				int result = NewsDAO.delItem(id);
				if(result>0) {
					response.sendRedirect(request.getContextPath()+"/admin/news?msg=1");
					return;
				}else {
					response.sendRedirect(request.getContextPath()+"/admin/news?err=1");
					return;
				}
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
		int result = NewsDAO.delItem(id);
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/admin/news?msg=1");
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/news?err=1");
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
