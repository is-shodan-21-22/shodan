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
		int id = service.getIdByUsername(request.getParameter("username"));
		
		if(id != -1) {
			User user = service.getUser(id); 
			
			if(user.getPassword().equals(request.getParameter("password"))) {
				response.setStatus(200);
				response.addCookie(new Cookie("user", Integer.toString(id)));
				
				return;
			}
		}

		response.setStatus(400);
		
	}
}
