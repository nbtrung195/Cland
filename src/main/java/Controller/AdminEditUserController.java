package Controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.role;
import model.bean.user;
import model.dao.roleDAO;
import model.dao.userDAO;
import util.AuthUtil;
import util.StringUtil;

/**
 * Servlet implementation class AdminEditUserController
 */
@WebServlet("/admin/user/edit")
public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private userDAO UserDAO;
	private roleDAO RoleDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminEditUserController() {
		super();
		UserDAO = new userDAO();
		RoleDAO = new roleDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/signin");
			return;
		}
		HttpSession session = request.getSession();
		user who = (user)session.getAttribute("Login");		
		int id = 0; 
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/user?err=2");
			return;
		}
		user Item = UserDAO.getItems(id);
		if(Item == null) {
			response.sendRedirect(request.getContextPath()+"/admin/user?err=2");
			return;
		}
		if(who.getRole().getId()==3) {
			if(who.getId()==id) {
				ArrayList<role> ListItems = RoleDAO.getItems();
				request.setAttribute("ListItems", ListItems);
				request.setAttribute("Item", Item);
				request.setAttribute("who", who);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp");
				rd.forward(request, response);
				return;
			}
			response.sendRedirect(request.getContextPath()+"/admin/user");
			return;
		}
		if(who.getRole().getId()==2) {
			if(Item.getRole().getId()==1) {
				response.sendRedirect(request.getContextPath()+"/admin/user");
				return;
			}
		}
		ArrayList<role> ListItems = RoleDAO.getItems();
		request.setAttribute("ListItems", ListItems);
		request.setAttribute("Item", Item);
		request.setAttribute("who", who);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		password = StringUtil.md5(password);
		String email = request.getParameter("email");
		String birth = request.getParameter("date");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		try {
			date = sdf1.parse(birth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date sqlStartDate = new Date(date.getTime());
		int role = Integer.parseInt(request.getParameter("role"));
		role Role = new role(role, "");
		user User = new user(id, name, password, sqlStartDate, email, Role);
		int result = UserDAO.editItem(User);
		if(result>0){
			response.sendRedirect(request.getContextPath()+"/admin/user?msg=3");
			return;
		}else{
			response.sendRedirect(request.getContextPath()+"/admin/user/edit?err=1");
			return;
		}
	}
}
