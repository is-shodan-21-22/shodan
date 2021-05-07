package Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Game;
import Model.User;
import Service.GameService;
import Service.HasGameService;
import Service.UserService;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = -4587622200104894945L;

	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		System.out.println("# UserServlet > Session: " + request.getSession().getId());

		User user = (User) request.getSession().getAttribute("user_metadata");
		
		switch(request.getParameter("action")) {
			case "info":
				System.out.println("# UserSerlvet > GET > Accesso ai dati personali di " + user.getName());
					
				request.getSession().setAttribute("user", user);
				
				break;
		
			case "purchase":
				System.out.println("# UserSerlvet > GET > Pagamento in corso da " + user.getName());
				
				int price = Integer.parseInt(
					request.getParameter("price")
				);
				
				System.out.println("# UserServlet > Transizione > Prezzo: " + price + "€ / Utente: " + user.getMoney() + "€");
				
				if(user.getMoney() < price) {
					System.out.println("# UserServlet > GET > L'utente non ha abbastanza fondi per l'acquisto");		
					response.getWriter().println("Non hai abbastanza soldi!");
					break;	
				}
				
				String[] gameIds = request.getParameter("games").split("-");
				
				for(String gameId : gameIds) {
					Game game = new GameService().getGame(Integer.parseInt(gameId));
					new HasGameService().addGame(
						user,
						game
					);
				}
				
				user.setMoney(user.getMoney() - price);
				
				new UserService().updateUser(user);
				
				System.out.println("# UserServlet > GET > Pagamento concluso con successo");
				
				response.getWriter().println("Pagamento concluso con successo!");
				request.getSession().setAttribute("user_metadata", user);
				
				break;
			
			default:
				System.out.println("#UserServlet > GET > Nessuna azione specificata");
				
				break;
		}
	}
	
	protected void doPost(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		
		switch(request.getParameter("action")) {
			case "removeUser":
				User user = new UserService().getUser(Integer.valueOf(request.getParameter("user-id")));
	
				// Servlet di rimozione dell'utente
				
				if(user != null ) {
					if(!user.isAdmin()) {
					new UserService().deleteUser(Integer.valueOf(request.getParameter("user-id")));
				
					request.setAttribute("messageUserDelete", "Utente eliminato con successo");
					request.getRequestDispatcher("admin.jsp").forward(request, response);
					
					System.out.println("# UserServlet > POST > Utente eliminato > " + user.getId());
					}
					else {
						request.setAttribute("errorMessageUserDelete", "Un admin non può essere cancellato");
						request.getRequestDispatcher("admin.jsp").forward(request, response);
						
						System.out.println("# UserServlet > POST > Impossibile eliminare utente > " + user.getId());
					}
				}else {
					request.setAttribute("errorMessageUserDelete", "Utente inesistente");
					request.getRequestDispatcher("admin.jsp").forward(request, response);
					
					System.out.println("# UserServlet > POST > Utente inesistente > " + Integer.valueOf(request.getParameter("user-id")));
				}
			
			case "logged_in_app":
				if(request.getSession().getAttribute("user_metadata") == null)
					response.sendRedirect("index.jsp");
				
			case "logged_in_index":
				if(request.getSession().getAttribute("user_metadata") != null)
					response.sendRedirect("app.jsp");
			
		}
	}
	
}
