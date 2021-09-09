package util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthUtil {
	
	public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		if (session.getAttribute("Login") == null) {
			return false;
		}
		return true;
	}
	
	public static boolean checkLogin2(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		if (session.getAttribute("Login2") == null) {
			return false;
		}
		return true;
	}
}
