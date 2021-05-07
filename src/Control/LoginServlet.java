package Control;

import java.io.IOException;
import java.io.PrintWriter;
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
				
				System.out.println("# LoginServlet > Login effettuato con successo da " + user.getName());
				System.out.println("# LoginServlet > Nuovo cookie: [user_session = " + request.getSession().getId() + "]");
				
				return;
			}else {
				out.print("La password è errata!");
				System.out.println("# LoginServlet > Tentativo di login fallito (password errata).");
				
				response.setStatus(400);
				return;
			}
		} else {
			out.print("L'username è errato o inesistente!");
			System.out.println("# LoginServlet > Tentativo di login fallito (username errato o inesistente).");
			
			response.setStatus(400);
			return;
		}
		
	}
}
