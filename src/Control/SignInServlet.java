package Control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.User;
import Service.UserService;

@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response
		) throws ServletException, IOException {
		
		UserService service = new UserService();
		PrintWriter out = response.getWriter();
		
		int id = service.getIdByUsername(request.getParameter("username"));
		
		if(id == -1){
			System.out.println(request.getParameter("password"));
			System.out.println(request.getParameter("password2"));
			if(request.getParameter("password").equals(request.getParameter("password2"))) {
				service.insertUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"));
				response.setStatus(200);
				out.print("Utente creato con successo!");
				return;
			} else {
				out.print("Le password non coincidono!");
				response.setStatus(400);
				return;
			}
		} else {
			out.print("L'utente esiste!");
			response.setStatus(400);
			return;
		}
	}

}
