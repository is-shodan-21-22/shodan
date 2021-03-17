package db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gameServlet")
public class GameServlet extends HttpServlet {
	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response
	) throws ServletException, IOException {
		String html = "";
		
		String query = "SELECT * FROM games WHERE game_id = " + request.getParameter("game");
		
		System.out.println(query);
		
		try {
			Connection con = DBConnectionPool.getConnection();
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				result.previous();
				while(result.next()) {
					html += "<h1><i class=\"fas fa-gamepad\"></i>"
						 + result.getString("game_name") + "</h1>"
						 + "<div class='game-flex'>"
						 + "<div class='game-info-container'>"
						 + "<p class='game-price'>" + result.getInt("game_price") + "€</p>"
						 + "<p class='game-release-date'>Pubblicato il " + result.getDate("game_release") + "</p>"
						 + "<p class='game-description'>" + result.getString("game_description") + "</p>"
						 + "<div class='button button--submit' id='add-to-cart'>Aggiungi al carrello</div>"
						 + "</div>"
						 + "<div class='game-image-container'>"
						 + "<div data-game-id="+ result.getInt("game_id") + " style=\"background-image: url('static/games/" + result.getString("game_image") + "')\" class=\"game-image\">"
						 + "</div>"
						 + "</div>";		
				}
			} else
				html += "<div class=\"empty-collection\">\r\n"
						+ "<span>Nessun gioco disponibile...</span>\r\n"
						+ "<i class=\"far fa-folder-open\"></i>\r\n"
						+ "</div>";
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/plain"); 
	    response.setCharacterEncoding("UTF-8");
		response.getWriter().write(html);
	}
}
