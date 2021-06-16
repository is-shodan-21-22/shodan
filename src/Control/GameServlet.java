package Control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
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
		User user;
		Connection db = (Connection) request.getServletContext().getAttribute("databaseConnection");
		String endpoint = request.getParameter("endpoint");
		
		switch(request.getParameter("action")) {
			case "shop":
				int limit = (request.getParameter("limit") != null) 
							? Integer.parseInt(request.getParameter("limit"))
							: 0;
				
				ArrayList<Game> ascending = new GameService(db).getAllAscendingGames(limit);
				ArrayList<Game> descending = new GameService(db).getAllDescendingGames(limit);
				
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
					user = new UserService(db).getUserBySession(request.getParameter("jsession"));
				else
					user = (User) request.getSession().getAttribute("user_metadata");
				
				ArrayList<Game> games = new GameService(db).getAllGamesByUser(user.getId());
				
				if(games != null) {
					request.setAttribute("games", games);
					request.getRequestDispatcher(endpoint).forward(request, response);
					response.setStatus(200);
				} else
					response.setStatus(400);
					
				
				System.out.println("# GameServlet > GET > Libreria personale dell'utente");
				
				break;
				
			case "game":
				int game_id;
				
				try {
					game_id = Integer.parseInt(request.getParameter("game_id"));
				} catch(NumberFormatException e) {
					response.setStatus(400);
					return;
				}
				
				Game game = new GameService(db).getGame(game_id);
				
				if(game == null) {
					System.out.println("# GameServlet > GET > ID non valido");
					
					response.setStatus(400);
				} else {
					System.out.println("# GameServlet > GET > Pagina del gioco ID " + game_id);
					
					response.setStatus(200);
					request.setAttribute("game", game);
					request.getRequestDispatcher(endpoint).forward(request, response);	
				}
				
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
		
		Connection db = (Connection) request.getServletContext().getAttribute("databaseConnection");
		
		switch(request.getParameter("action")) {
			case "addGame":
				String fileName;
				
				Date rawDate = Date.valueOf(request.getParameter("game-date"));
				int parsedDate = Integer.valueOf(rawDate.toString().split("-")[0]);
				
				int lesserYear = 1970;
				int greaterYear = 2000;
				
				if(parsedDate < lesserYear || parsedDate > greaterYear) {
					request.setAttribute("errorMessageGameAdd", "La data inserita non &egrave; valida.");
					request.getRequestDispatcher("admin.jsp").forward(request, response);
					response.setStatus(400);
					
					return;
				}
				
				try {
					Part filePart = request.getPart("game-image");
					fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
					InputStream fileContent = filePart.getInputStream();
					File filePath = new File(getServletContext().getRealPath("Static/GamePictures"));
					File file = new File(filePath, fileName);
					
					filePath.mkdir();
					
					if(!file.exists())
						Files.copy(fileContent, file.toPath());
					else {
						request.setAttribute("errorMessageGameAdd", "Impossibile processare la richiesta.");
						request.getRequestDispatcher("admin.jsp").forward(request, response);
						response.setStatus(400);
						
						return;
					}
				} catch(IOException e) {
					e.printStackTrace();
					
					request.setAttribute("errorMessageGameAdd", "Non &egrave; stato possibile aggiungere il gioco.");
					request.getRequestDispatcher("admin.jsp").forward(request, response);
					response.setStatus(400);
					
					return;
				}
				
				try {		
					new GameService(db).addGame(
						request.getParameter("game-name"), 
						fileName, 
						Integer.valueOf(request.getParameter("game-price")),
						rawDate,
						request.getParameter("game-description")
					);
				} catch(IllegalArgumentException e) {
					e.printStackTrace();
					
					request.setAttribute("messageGameAdd", "I dati forniti non sono validi.");
					response.setStatus(400);
					return;
				}
				
				request.setAttribute("messageGameAdd", "Gioco aggiunto con successo");
				request.getRequestDispatcher("admin.jsp").forward(request, response);
				response.setStatus(200);
			
				System.out.println("# GameServlet > POST > Gioco aggiunto > " + request.getParameter("game-name"));
			
				break;
			
			case "deleteGame":
				Game game = new GameService(db).getGame(Integer.valueOf(request.getParameter("game-id")));
				
				if(game != null) {
					new GameService(db).deleteGame(Integer.valueOf(request.getParameter("game-id")));
				
					request.setAttribute("messageGameDelete", "Gioco eliminato con successo");
					request.getRequestDispatcher("admin.jsp").forward(request, response);
					
					System.out.println("# GameServlet > POST > Gioco eliminato > " + game.getName());
				} else {
					request.setAttribute("errorMessageGameDelete", "Il gioco non � presente");
					
					System.out.println("# GameServlet > POST > Gioco insistente > " + request.getParameter("game-name"));
				}
			
			default:
				System.out.println("# GameServlet > POST > Nessuna azione specificata");
				
				break;
		}
		
	}
	
}
