package Service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Database.DBConnectionPool;
import Model.Game;
import Model.User;

public class HasGameService implements Serializable {

	private static final long serialVersionUID = -4279943566075781437L;
	
	private Connection db;
	private PreparedStatement statement;
	
	public HasGameService() {
		try {
			db = DBConnectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public void addGame(User user, Game game) {
		try {
			String query = "INSERT INTO has_game VALUES (" + user.getId() + "," + game.getId() + ")";
			
			statement = db.prepareStatement(query);
			statement.executeUpdate();
			
			System.out.println("# GameService > Query > " + query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
