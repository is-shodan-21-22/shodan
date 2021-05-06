package Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.User;
import Service.GameService;

@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {

	private static final long serialVersionUID = -8724190928795580877L;
	
	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response
	) throws ServletException, IOException {
		System.out.println("# GameServlet > Session: " + request.getSession().getId());
		
		switch(request.getParameter("action")) {
			case "shop":
				int limit = (request.getParameter("limit") != null) 
							? Integer.parseInt(request.getParameter("limit"))
							: 0;
				
				if(request.getParameter("order") == null)
					request.getSession().setAttribute("games", new GameService().getAllAscendingGames(limit));
				else
					request.getSession().setAttribute("desc_games", new GameService().getAllDescendingGames(limit));
				
				System.out.println("# GameServlet > GET > Ultimi 5 giochi del negozio");
				
				break;
				
			case "library":
				/*int user_id = request.getParameter("user_id") != null
						      ? Integer.parseInt(request.getParameter("user_id"))
						      : 0;
				
				if(user_id == 0) {
					System.out.println("# GameServlet > GET > Nessun ID utente specificato");
					return;
				}
				*/
				
				User user = (User) request.getSession().getAttribute("user_metadata");
				
				request.getSession().setAttribute("games", new GameService().getAllGamesByUser(
					user.getId()
				));
				
				System.out.println("# GameServlet > GET > Libreria personale dell'utente" );
				
				break;
				
			case "game":
				int game_id = request.getParameter("game_id") != null
			      ? Integer.parseInt(request.getParameter("game_id"))
			      : 0;
	
				if(game_id == 0) {
					System.out.println("# GameServlet > GET > Nessun ID del gioco specificato");
					return;
				}
			
				request.getSession().setAttribute("game", new GameService().getGame(game_id));
				
				System.out.println("# GameServlet > GET > Pagina del gioco ID " + game_id);
				
				break;
				
			default:
				System.out.println("# GameServlet > GET > Nessuna azione specificata");
				
				break;
				
		}
	}
	
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response
	) throws ServletException, IOException {
		System.out.println("# GameServlet > Session: " + request.getSession().getId());
		
		switch(request.getParameter("action")) {
		
			case "addGame":
			
				new GameService().addGame(request.getParameter("game-name"),
							request.getParameter("game-image"), 
							Integer.valueOf(request.getParameter("game-price")));
			
				request.setAttribute("messageGameAdd", "Gioco aggiunto con successo");
				request.getRequestDispatcher("admin.jsp").forward(request, response);
				response.sendRedirect("admin.jsp");
			
				System.out.println("# GameServlet > POST > Gioco aggiunto > " + request.getParameter("game-name"));
			
				break;
			
			case "deleteGame":
			
				new GameService().deleteGame(Integer.valueOf(request.getParameter("game-id")));
			
				request.setAttribute("messageGameDelete", "Gioco eliminato con successo");
				request.getRequestDispatcher("admin.jsp").forward(request, response);
				response.sendRedirect("admin.jsp");
			
				System.out.println("# GameServlet > POST > Gioco eliminato > " + request.getParameter("game-name"));
			
			default:
				System.out.println("# GameServlet > POST > Nessuna azione specificata");
				
				break;
		}
		
	}
	
}
