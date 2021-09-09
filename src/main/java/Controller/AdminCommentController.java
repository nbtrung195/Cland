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

import model.bean.comment;
import model.bean.user;
import model.dao.commentDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminCommentController
 */
@WebServlet("/admin/comment")
public class AdminCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private commentDAO CommentDAO;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCommentController() {
        super();
        CommentDAO = new commentDAO();
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
		request.setAttribute("who", who);
		ArrayList<comment> ListItems = CommentDAO.getItems();
		request.setAttribute("ListItems", ListItems);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/comment.jsp");
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
