package Control;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Game;
import Model.User;
import Service.HasCartService;
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
		
		if(request.getParameter("cookie").equals("false")) {
			user = new UserService().getUserBySession(request.getParameter("jsession"));
		} else
			user = (User) request.getSession().getAttribute("user_metadata");
		
		endpoint = request.getParameter("endpoint");
		
		HasCartService service = new HasCartService();
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
	
}