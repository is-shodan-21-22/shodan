package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Service.*;
import Model.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 5201749135928085764L;

	protected void doPost(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		
		UserService service = new UserService();
		PrintWriter out = response.getWriter();
		int id = service.getIdByUsername(request.getParameter("username"));
		
		if(id != -1) {
			User user = service.getUser(id); 
			
			if(user.getPassword().equals(request.getParameter("password"))) {
				response.setStatus(200);
				response.addCookie(new Cookie("user_session", request.getSession().getId()));
				request.getSession().setAttribute("user_metadata", user);
				
				System.out.println("# UserSession > " + request.getSession().getAttribute("user_metadata"));
				out.print("Login effettuato con successo");
				return;
			}else {
				out.print("La password e' errata");
				response.setStatus(400);
				return;
			}
		}else {
			out.print("Username errato");
			response.setStatus(400);
			return;
		}

		
		
	}
}
