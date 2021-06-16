package Service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Game;

public class GameService implements Serializable {

	private static final long serialVersionUID = -7187173329847684983L;
	
	private Connection db;
	private PreparedStatement statement;
	
	public GameService(Connection db) {
		this.db = db;
	}
	
	public Game getGame(int id) {
		Game game = null;
		
		try {
			String query = "SELECT * FROM games WHERE game_id = ?";
			
			statement = db.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			System.out.println("# GameService > Query > SELECT * FROM games WHERE game_id = " + id);
			
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
		
		try {
			String query = "SELECT * FROM games AS G, has_game AS HG WHERE G.game_id = HG.game_id AND user_id = ?";
			
			statement = db.prepareStatement(query);
			statement.setInt(1, user_id);
			
			ResultSet result = statement.executeQuery();
			
			if(!result.next())
				return null;
			
			result.beforeFirst();
			
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
	
	public ArrayList<Game> getAllDescendingGames(int limit) {
		ArrayList<Game> games = new ArrayList<Game>();
		
		try {
			String query = "SELECT * FROM games ORDER BY game_id DESC" + (limit != 0 ? (" LIMIT " + limit) : "");

			statement = db.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			if(!result.next())
				return null;
			
			result.beforeFirst();
			
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
	
	public ArrayList<Game> getAllAscendingGames(int limit) {
		ArrayList<Game> games = new ArrayList<Game>();
		
		try {
			String query = "SELECT * FROM games ORDER BY game_id" + (limit != 0 ? (" LIMIT " + limit) : "");
			
			statement = db.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			if(!result.next())
				return null;
			
			result.beforeFirst();
			
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
	
	public boolean addGame(String game_name, String game_image, int game_price, Date game_date, String game_description) {
		try {
			String query = "INSERT INTO games(game_name, game_image, game_price, game_release, game_description) VALUES(?, ?, ?, ?, ?)";
		
			System.out.println("# GameService > Query > " + query);
			
			statement = db.prepareStatement(query);
			statement.setString(1, game_name);
			statement.setString(2, game_image);
			statement.setInt(3, game_price);
			statement.setDate(4, game_date);
			statement.setString(5, game_description);
			
			statement.executeUpdate();
			
			System.out.println("# GameService > Aggiungo il gioco " + game_name);
		
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean deleteGame(int id) {
		try {
			String query = "DELETE FROM games WHERE game_id = ?";
			
			System.out.println("# GameService > Query > " + query);
			
			statement = db.prepareStatement(query);
			statement.setInt(1, id);
			
			statement.executeUpdate();
			
			System.out.println("# GameService > Elimino il gioco " + id);
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
