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
					html += result.getString("game_name");
				}
			} else
				html += "No";
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/plain"); 
	    response.setCharacterEncoding("UTF-8");
		response.getWriter().write(html);
	}
}
