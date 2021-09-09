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

import model.bean.user;
import model.dao.userDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminUserController
 */
@WebServlet("/admin/user")
public class AdminUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private userDAO UserDAO;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUserController() {
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
		ArrayList<user> ListItems = UserDAO.getItems();
		request.setAttribute("ListItems", ListItems);
		request.setAttribute("who", who);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/user/index.jsp");
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
