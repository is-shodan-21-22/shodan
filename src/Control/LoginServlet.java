package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response
	) throws ServletException, IOException {
		String userid = "unknown";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String query = "SELECT * FROM users WHERE user_name = '" + username + "' AND user_password = '" + password + "'";
		
		try {
			Connection con = DBConnectionPool.getConnection();
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			result.first();
			result.previous();
			
			if(result.next())
				userid = String.valueOf(result.getInt("user_id"));
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PrintWriter writer = response.getWriter();
		
		writer.println(userid);
	}
}
