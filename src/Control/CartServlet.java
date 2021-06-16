package Control;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Game;
import Model.HasCart;
import Model.User;
import Service.HasCartService;
import Service.HasGameService;
import Service.UserService;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

	private static final long serialVersionUID = 1166145472899719871L;
	
	User user;
	String endpoint;
	
	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		System.out.println("# CartServlet > Session: " + request.getSession().getId());
		
		Connection db = (Connection) request.getServletContext().getAttribute("databaseConnection");
		
		if(request.getParameter("cookie").equals("false")) {
			user = new UserService(db).getUserBySession(request.getParameter("jsession"));
		} else
			user = (User) request.getSession().getAttribute("user_metadata");
		
		endpoint = request.getParameter("endpoint");
		
		HasCartService service = new HasCartService(db);
		ArrayList<Game> cart = service.selectCart(user);
		
		if(!cart.isEmpty()) {
			int total = 0;
			
			for(Game game : cart)
				total += game.getPrice();
			
			response.setStatus(200);
			request.setAttribute("games", cart);
			request.setAttribute("total", total);
			request.getRequestDispatcher(endpoint).forward(request, response);
		} else
			response.setStatus(400);
	}	
	
	protected void doPost(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		System.out.println("# CartServlet > Session: " + request.getSession().getId());
		
		Connection db = (Connection) request.getServletContext().getAttribute("databaseConnection");
		
		if(request.getParameter("cookie").equals("false")) {
			user = new UserService(db).getUserBySession(request.getParameter("jsession"));
		} else
			user = (User) request.getSession().getAttribute("user_metadata");
		
		switch(request.getParameter("action")) {
			case "delete":
				new HasCartService(db).dropCart(user);
				break;
				
			case "pay":
				if(user.getMoney() >= Integer.valueOf(request.getParameter("total"))) {
					HasCartService service = new HasCartService(db);
					ArrayList<Game> games = service.selectCart(user);
					
					for(Game game : games)
						new HasGameService(db).addGame(user, game);
					
					System.out.println("# CartServlet > Pagamento > Saldo utente: " + user.getMoney() + " - Totale: " + Integer.valueOf(request.getParameter("total")));
					
					user.setMoney(
						user.getMoney() - Integer.valueOf(request.getParameter("total"))
					);
					
					new UserService(db).updateUser(user);
					request.getSession().setAttribute("user_metadata", user);
					
					service.dropCart(user);
					response.setStatus(200);
				} else
					response.setStatus(400);
					
				break;
			
			case "addGame":
				if(new HasCartService(db).addItem(
					new HasCart(
						user.getId(),
						Integer.valueOf(request.getParameter("game_id"))
					)
				))
					response.setStatus(200);
				else
					response.setStatus(400);
				
				break;
				
		}
	}
	
}
