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

@WebServlet("/blogServlet")
public class BlogServlet extends HttpServlet {
	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response
	) throws ServletException, IOException {
		String html = "";
		
		String query = "SELECT * FROM blog AS B ORDER BY blog_id DESC LIMIT 3";
		
		System.out.println(query);
		
		try {
			Connection con = DBConnectionPool.getConnection();
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			if(result.next()) {
				result.previous();
				while(result.next()) {
					html += "<div class=\"blog-container\">\r\n"
							+ "            <div class=\"article\">\r\n"
							+ "                <h1>" + result.getString("blog_title") + "</h1>\r\n"
							+ "                <div class=\"content\">\r\n"
							+                      result.getString("blog_short_title")
							+ "                </div>          \r\n"
							+ "                <a href=\"/\">\r\n"
							+ "                    Leggi la notizia...\r\n"
							+ "                </a>\r\n"
							+ "            </div>\r\n"
							+ "        </div>\r\n";
				}
			} else
				html += "<div class=\"empty-collection\">\r\n"
						+ "<span>Nessun articolo disponibile...</span>\r\n"
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
