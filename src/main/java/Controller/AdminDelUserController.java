package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.user;
import model.dao.userDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminDelUserController
 */
@WebServlet("/admin/user/del")
public class AdminDelUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private userDAO UserDAO;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelUserController() {
        super();
        UserDAO = new userDAO();
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
		if(who.getRole().getId()==3) {
			response.sendRedirect(request.getContextPath()+"/admin/user");
			return;
		}
		int id = 0; 
		try {
			id = Integer.parseInt(request.getParameter("id")) ;
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/user?err=1");
			return;
		}
		user Item = UserDAO.getItems(id);
		if(who.getRole().getId()==2) {
			if(Item.getRole().getId()==1) {
				response.sendRedirect(request.getContextPath()+"/admin/user");
				return;
			}
		}
		int result = UserDAO.delItem(id);
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/admin/user?msg=2");
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/user?err=1");
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
