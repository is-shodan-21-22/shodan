package Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.User;
import Service.UserService;

@WebServlet("/SettingsServlet")
public class SettingsServlet extends HttpServlet {

	private static final long serialVersionUID = -3000288672809209195L;

	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response
	) throws ServletException, IOException {
		System.out.println("# SettingsServlet > Session: " + request.getSession().getId());
		
		User user = new UserService().getUser(
			Integer.parseInt(
				request.getParameter("user_id")
			)
		);
		
		switch(request.getParameter("action")) {
			case "updateEmail":
				String email = request.getParameter("email");
				System.out.println("# SettingsServlet > POST > Aggiorna l'email (" + email + ") dell'utente ID " + user.getId());
				
				if(!email.contains("@") && !email.contains("."))
					response.getWriter().println("Non è stato possibile modificare l'email. Ricontrolla i dati!");
				
				user.setEmail(email);
				
				if(new UserService().updateUser(user))
					response.getWriter().println("Email modificata con successo!");
				else
					response.getWriter().println("Non è stato possibile modificare l'email. Ricontrolla i dati!");
				
				break;
		
			case "updatePassword":
				String old_password = request.getParameter("old_password");
				String new_password = request.getParameter("new_password");
				String new_password_again = request.getParameter("new_password_again");
				System.out.println("# SettingsServlet > POST > Aggiorna la password dell'utente ID " + user.getId());
				
				if(!new_password.equals(new_password_again)) {
					response.getWriter().println("Le due password inserite non coincidono!");
					return;
				}
				
				if(!old_password.equals(user.getPassword())) {
					response.getWriter().println("Non hai inserito correttamente la tua password attuale!");
					return;
				}
				
				user.setPassword(new_password);
				
				if(new UserService().updateUser(user))
					response.getWriter().println("Password modificata con successo!");
				else
					response.getWriter().println("Non è stato possibile modificare la password. Ricontrolla i dati!");
					
				
				break;	
		
			default:
				System.out.println("# SettingsServlet > POST > Nessuna azione specificata");
			
			break;
		}
	}
}
