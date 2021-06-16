package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Service.UserService;
import Utils.PasswordHasher;

@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		Connection db = (Connection) request.getServletContext().getAttribute("databaseConnection");
		
		UserService service = new UserService(db);
		PrintWriter out = response.getWriter();
		
		int id = service.getIdByUsername(request.getParameter("username"));
		
		if(id == -1){
			if(request.getParameter("password").equals(request.getParameter("password2"))) {
				service.insertUser(request.getParameter("username"), PasswordHasher.hash(request.getParameter("password")), request.getParameter("email"));
				
				out.print("Utente creato con successo!");
				System.out.println("# SignInServlet > Creazione utente effettuata con successo (username: " + request.getParameter("username"));
				
				response.setStatus(200);
				return;
			} else {
				out.print("Le password non coincidono!");
				System.out.println("# SignInServlet > E' stato effettuato un tentativo fallimentare di registrazione.");
				
				response.setStatus(400);
				return;
			}
		} else {
			out.print("L'utente esiste!");
			System.out.println("# SignInServlet > E' stato effettuato un tentativo di creazione di un utente già esistente.");
			
			response.setStatus(400);
			return;
		}
	}

}
