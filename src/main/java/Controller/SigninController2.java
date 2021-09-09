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
@WebServlet("/signinPublic")
public class SigninController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private userDAO UserDAO;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SigninController2() {
        super();
        UserDAO = new userDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(AuthUtil.checkLogin2(request, response)) {
				response.sendRedirect(request.getContextPath()+"/home");
				return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/signin2.jsp");
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
			session.setAttribute("Login2", User);		
				response.sendRedirect(request.getContextPath()+"/home");
				return;
		}else{
			response.sendRedirect(request.getContextPath()+"/signinPublic?err=1");
			return;
		}
	}

}
