package Controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.role;
import model.bean.user;
import model.dao.userDAO;
import util.StringUtil;

/**
 * Servlet implementation class PublicCreateAccController
 */
@WebServlet("/createAcc")
public class PublicCreateAccController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private userDAO UserDAO;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicCreateAccController() {
        super();
        UserDAO = new userDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/public/createAcc.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		if(UserDAO.hasUser(username)>0) {
			response.sendRedirect(request.getContextPath()+"/createAcc?err=1");
			return;
		}
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
		int role = 4;
		role Role = new role(role, "");
		user User = new user(0, username, password, sqlStartDate, email, Role);
		int result = UserDAO.addItem(User);
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/home?msg=1");
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/home?err=1");
			return;
		}
	}

}
