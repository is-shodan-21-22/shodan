package Control;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.User;
import Service.UserService;
import Utils.PasswordHasher;

@WebServlet("/SettingsServlet")
public class SettingsServlet extends HttpServlet {

	private static final long serialVersionUID = -3000288672809209195L;

	protected void doPost(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		System.out.println("# SettingsServlet > Session: " + request.getSession().getId());
		
		Connection db = (Connection) request.getServletContext().getAttribute("databaseConnection");
		
		User user;
		
		if(request.getParameter("cookie").equals("false")) {
			user = new UserService(db).getUserBySession(request.getParameter("jsession"));
		} else
			user = (User) request.getSession().getAttribute("user_metadata");
		
		switch(request.getParameter("action")) {
			case "updateEmail":
				String email = request.getParameter("email");
				
				System.out.println("# SettingsServlet > POST > Aggiorna l'email (" + email + ") dell'utente ID " + user.getId());
				
				user.setEmail(email);
				
				if(new UserService(db).updateUser(user)) {
					response.getWriter().println("Email modificata con successo!");
					request.getSession().setAttribute("user_metadata", user);
				} else
					response.getWriter().println("Non &egrave; stato possibile modificare l'email. Ricontrolla i dati!");
				
				break;
		
			case "updatePassword":
				String old_password = PasswordHasher.hash(request.getParameter("old_password"));
				String new_password = request.getParameter("new_password");
				String new_password_again = request.getParameter("new_password_again");
				
				System.out.println("# SettingsServlet > POST > Aggiorna la password dell'utente ID " + user.getId());
				
				if(!old_password.equals(user.getPassword())) {
					response.getWriter().println("Non hai inserito correttamente la tua password attuale!");
					return;
				}
				
				if(!new_password.equals(new_password_again)) {
					response.getWriter().println("Le due password inserite non coincidono!");
					return;
				}
				
				user.setPassword(PasswordHasher.hash(new_password));
				
				if(new UserService(db).updateUser(user)) {
					response.getWriter().println("Password modificata con successo!");
					request.getSession().setAttribute("user_metadata", user);
				} else
					response.getWriter().println("Non &egrave; stato possibile modificare la password. Ricontrolla i dati!");
				
				break;	
		
			default:
				System.out.println("# SettingsServlet > POST > Nessuna azione specificata");
			
			break;
		}
	}
}
