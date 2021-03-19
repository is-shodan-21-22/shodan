package Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
				request.getSession().setAttribute("games", new GameService().getAllGames(5));
				
				System.out.println("# GameServlet > GET > Ultimi 5 giochi del negozio");
				
				break;
				
			case "library":
				int user_id = request.getParameter("user_id") != null
						      ? Integer.parseInt(request.getParameter("user_id"))
						      : 0;
				
				if(user_id == 0) {
					System.out.println("# GameServlet > GET > Nessun ID utente specificato");
					return;
				}
			
				request.getSession().setAttribute("games", new GameService().getAllGamesByUser(user_id));
				
				System.out.println("# GameServlet > GET > Libreria personale dell'utente" + user_id);
				
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
	
}
