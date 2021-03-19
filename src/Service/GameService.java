package Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Control.DBConnectionPool;
import Model.Game;

public class GameService {

	private Connection db;
	private Statement statement;
	
	public GameService() {
		try {
			db = DBConnectionPool.getConnection();
			statement = db.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Game getGame(int id) {
		Game game = null;
		
		try {
			ResultSet result = statement.executeQuery("SELECT * FROM games WHERE game_id = " + id);
			
			System.out.println("# GameService > Executing SELECT * FROM blog WHERE game_id = " + id);
			
			if(result.next())
				game = new Game(
					result.getInt("game_id"),
					result.getInt("game_price"),
					result.getString("game_name"),
					result.getString("game_description"),
					result.getString("game_image"),
					result.getDate("game_release")
				);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return game;
	}
	
	public ArrayList<Game> getAllGamesByUser(int user_id) {
		ArrayList<Game> games = new ArrayList<Game>();
		String query = "SELECT * FROM games AS G, has_game AS HG WHERE G.game_id = HG.game_id AND user_id = " + user_id;
		
		try {
			ResultSet result = statement.executeQuery(query);
			
			System.out.println("# GameService > Query > " + query);
			
			while(result.next()) {
				games.add(
					new Game(
						result.getInt("game_id"),
						result.getInt("game_price"),
						result.getString("game_name"),
						result.getString("game_description"),
						result.getString("game_image"),
						result.getDate("game_release")
					)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return games;
	}
	
	public ArrayList<Game> getAllGames(int limit) {
		ArrayList<Game> games = new ArrayList<Game>();
		String query = "SELECT * FROM games" + (limit != 0 ? (" LIMIT " + limit) : "");
		
		try {
			ResultSet result = statement.executeQuery(query);
			
			System.out.println("# GameService > Query > " + query);
			
			while(result.next()) {
				games.add(
					new Game(
						result.getInt("game_id"),
						result.getInt("game_price"),
						result.getString("game_name"),
						result.getString("game_description"),
						result.getString("game_image"),
						result.getDate("game_release")
					)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return games;
	}
	
}
