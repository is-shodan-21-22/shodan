package Control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import Model.Game;
import Model.User;
import Service.GameService;
import Service.UserService;

@WebServlet("/GameServlet")
@MultipartConfig
public class GameServlet extends HttpServlet {

	private static final long serialVersionUID = -8724190928795580877L;
	
	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		System.out.println("# GameServlet > Session: " + request.getSession().getId());
		
		User user;
		String endpoint = request.getParameter("endpoint");
		
		switch(request.getParameter("action")) {
			case "shop":
				int limit = (request.getParameter("limit") != null) 
							? Integer.parseInt(request.getParameter("limit"))
							: 0;
				
				ArrayList<Game> ascending = new GameService().getAllAscendingGames(limit);
				ArrayList<Game> descending = new GameService().getAllDescendingGames(limit);
				
				if(request.getParameter("order") == null) {
					if(ascending != null) {
						request.setAttribute("games", ascending);
						request.getRequestDispatcher(endpoint).forward(request, response);
						response.setStatus(200);
					} else 
						response.setStatus(400);
				} else {
					if(descending != null) {
						request.setAttribute("games", descending);
						request.getRequestDispatcher(endpoint).forward(request, response);
						response.setStatus(200);
					} else
						response.setStatus(400);
				}
					
				System.out.println("# GameServlet > GET > Ultimi 5 giochi del negozio");
				
				break;
				
			case "library":
				if(request.getParameter("cookie").equals("false"))
					user = new UserService().getUserBySession(request.getParameter("jsession"));
				else
					user = (User) request.getSession().getAttribute("user_metadata");
				
				ArrayList<Game> games = new GameService().getAllGamesByUser(user.getId());
				
				if(games != null) {
					request.setAttribute("games", games);
					request.getRequestDispatcher(endpoint).forward(request, response);
					response.setStatus(200);
				} else
					response.setStatus(400);
					
				
				System.out.println("# GameServlet > GET > Libreria personale dell'utente");
				
				break;
				
			case "game":
				int game_id = request.getParameter("game_id") != null
			      ? Integer.parseInt(request.getParameter("game_id"))
			      : 0;
	
				if(game_id == 0) {
					System.out.println("# GameServlet > GET > Nessun ID del gioco specificato");
					return;
				}
			
				request.setAttribute("game", new GameService().getGame(game_id));
				request.getRequestDispatcher(endpoint).forward(request, response);
				
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
		
		Part filePart = request.getPart("game-image");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		
		try {
			InputStream fileContent = filePart.getInputStream();
			File filePath = new File(getServletContext().getRealPath("Static/GamePictures"));
			File file = new File(filePath, fileName);
			
			filePath.mkdir();
			
			Files.copy(fileContent, file.toPath());
		} catch(AccessDeniedException | FileAlreadyExistsException e) {
			e.printStackTrace();
			
			request.setAttribute("messageGameAdd", "Non &erave; stato possibile aggiungere il gioco.");
			response.setStatus(400);
		}
		
		switch(request.getParameter("action")) {
		
			case "addGame":
				new GameService().addGame(
					request.getParameter("game-name"), 
					fileName, 
					Integer.valueOf(request.getParameter("game-price"))
				);
				
				request.setAttribute("messageGameAdd", "Gioco aggiunto con successo");
				request.getRequestDispatcher("admin.jsp").forward(request, response);
				response.setStatus(200);
			
				System.out.println("# GameServlet > POST > Gioco aggiunto > " + request.getParameter("game-name"));
			
				break;
			
			case "deleteGame":
				Game game = new GameService().getGame(Integer.valueOf(request.getParameter("game-id")));
				
				if(game != null) {
					new GameService().deleteGame(Integer.valueOf(request.getParameter("game-id")));
				
					request.setAttribute("messageGameDelete", "Gioco eliminato con successo");
					request.getRequestDispatcher("admin.jsp").forward(request, response);
					
					System.out.println("# GameServlet > POST > Gioco eliminato > " + game.getName());
				} else {
					request.setAttribute("errorMessageGameDelete", "Il gioco non è presente");
					request.getRequestDispatcher("admin.jsp").forward(request, response);
					
					System.out.println("# GameServlet > POST > Gioco insistente > " + request.getParameter("game-name"));
				}
			
			default:
				System.out.println("# GameServlet > POST > Nessuna azione specificata");
				
				break;
		}
		
	}
	
}
