package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.user;
import model.dao.userDAO;
import util.AuthUtil;
import util.StringUtil;

/**
 * Servlet implementation class SigninController
 */
@WebServlet("/signin")
public class SigninController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private userDAO UserDAO;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SigninController() {
        super();
        UserDAO = new userDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(AuthUtil.checkLogin(request, response)) {
				response.sendRedirect(request.getContextPath()+"/admin/index");
				return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/signin.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = request.getParameter("inputUsername");
		String pass = request.getParameter("inputPassword");
		pass = StringUtil.md5(pass);
		user User = UserDAO.checkUser(name,pass);
		if(User != null) {		
			if(User.getRole().getId() == 4) {				
				response.sendRedirect(request.getContextPath()+"/signin?err=1");
				return;
			}else {
				session.setAttribute("Login", User);
				response.sendRedirect(request.getContextPath()+"/admin/index");
				return;
			}
		}else {
			response.sendRedirect(request.getContextPath()+"/signin?err=1");
			return;
		}
	}

}
