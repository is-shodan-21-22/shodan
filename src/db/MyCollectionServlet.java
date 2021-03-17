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

@WebServlet("/myCollectionServlet")
public class MyCollectionServlet extends HttpServlet {
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response
	) throws ServletException, IOException {
		String html = "";
		
		String query = "SELECT * FROM games AS G "
				+ "INNER JOIN has_game AS HG "
				+ "ON G.game_id = HG.game_id WHERE user_id = " 
				+ request.getParameter("user_id");
		
		System.out.println(query);
		
		try {
			Connection con = DBConnectionPool.getConnection();
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				result.previous();
				while(result.next()) {
					html += "<a href=\"\"><div style=\"background-image: url('static/games/" + result.getString("game_image") + "')\" class=\"game-container\">";
					html += "<div class=\"overlay\">";
					html += result.getString("game_name");
					html += "</div></div></a>";
				}
			} else
				html += "<div class=\"empty-collection\">\r\n"
						+ "<span>Non hai nessun gioco...</span>\r\n"
						+ "<i class=\"far fa-folder-open\"></i>\r\n"
						+ "</div>";
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PrintWriter writer = response.getWriter();
		
		writer.println(html);
	}
}
