package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.comment;
import model.bean.news;
import model.bean.user;
import model.dao.commentDAO;
import model.dao.newsDAO;

/**
 * Servlet implementation class PublicDetailController
 */
@WebServlet("/detail")
public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private newsDAO NewsDAO; 
    private commentDAO CommentDAO;
    private ArrayList<comment> list = new ArrayList<>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicDetailController() {
        super();
        NewsDAO = new newsDAO();
        CommentDAO = new commentDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		list.clear();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		if(session.getAttribute("HasSession"+id) == null) {
			NewsDAO.IncreaseView(id);
			session.setAttribute("HasSession"+id, "createSession"+id);
			session.setMaxInactiveInterval(300);
		}
		news News = NewsDAO.getItem(id);
		if(News == null) {
			response.sendRedirect(request.getContextPath()+"home");
			return;
		}		
		ArrayList<comment> ListComment = CommentDAO.getCommentById(id);
		int NumberOfComment = CommentDAO.NumberOfComment(id);
		ArrayList<news> RelateNews = NewsDAO.getRelateItems(News.getCat().getId(), News.getId());
		request.setAttribute("News", News);
		request.setAttribute("ListComment", ListComment);
		request.setAttribute("NumberOfComment", NumberOfComment);
		request.setAttribute("RelateNews", RelateNews);
		RequestDispatcher rd = request.getRequestDispatcher("/public/detail.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = formatter.format(date);
		news News = NewsDAO.getItem(id);
		user reader = (user)session.getAttribute("Login2");
		PrintWriter out = response.getWriter();
		String cmt = request.getParameter("cmt");
		comment bl = new comment(0, reader, cmt, null, News);
		int result = CommentDAO.addComment(bl);
		list.add(bl);
		session.setAttribute("list", list);
		for(comment obj : list) {
			out.print("<p><span style=\"font-weight:800;\">"+obj.getReader().getName()+"</span>("+strDate+"):"+obj.getContent()+"</p>");					
			}
		}

}
