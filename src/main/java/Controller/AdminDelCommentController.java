package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.user;
import model.dao.commentDAO;
import util.AuthUtil;

/**
 * Servlet implementation class AdminDelCommentController
 */
@WebServlet("/admin/comment/del")
public class AdminDelCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private commentDAO CommentDAO;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelCommentController() {
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
		if(who.getRole().getId()==2||who.getRole().getId()==3) {
			response.sendRedirect(request.getContextPath()+"/admin/comment");
			return;
		}
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/comment?err=1");
			return;
		}
		int result = CommentDAO.delItem(id);
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/admin/comment?msg=1");
			return;
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/comment?err=1");
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
