package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DBConnectionPool;
import Model.Article;

public class ArticleService {

	private Connection db;
	private PreparedStatement statement;
	
	public ArticleService() {
		try {
			db = DBConnectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Article getArticle(int id) {
		Article article = null;
		
		try {
			String query = "SELECT * FROM blog WHERE blog_id = " + id;
			
			statement = db.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			System.out.println("# ArticleService > Executing SELECT * FROM blog WHERE blog_id = " + id);
			
			if(result.next())
				article = new Article(
					result.getInt("blog_id"),
					result.getString("blog_title"),
					result.getString("blog_short_title"),
					result.getString("blog_html")
				);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return article;
	}
	
	public ArrayList<Article> getAllArticles(int limit) {
		ArrayList<Article> blog = new ArrayList<Article>();
		
		try {
			String query = "SELECT * FROM blog" + (limit != 0 ? (" LIMIT " + limit) : "");
			
			statement = db.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			System.out.println("# ArticleService > Query > " + query);
			
			if(!result.next())
				return null;
			
			result.beforeFirst();
			
			while(result.next()) {
				blog.add(
					new Article(
						result.getInt("blog_id"),
						result.getString("blog_title"),
						result.getString("blog_short_title"),
						result.getString("blog_html")
					)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return blog;
	}
	
	public boolean addArticle(String title,String shortTitle,String html){
		try {
			String query = "INSERT INTO blog(blog_title, blog_short_title, blog_html) VALUES('" + title + "','" + shortTitle + "',\"" + html + "\")";
		
			System.out.println("# GameService > Query > " + query);
			
			statement = db.prepareStatement(query);
			statement.executeUpdate();
			
			System.out.println("# GameService > Aggiungo l'articolo " + title);
		
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean deleteArticle(int articleId) {
		try {
			String query = "DELETE FROM blog WHERE blog_id =" + articleId ;
			
			System.out.println("# GameService > Query > " + query);
			
			statement = db.prepareStatement(query);
			statement.executeUpdate();
			
			System.out.println("# GameService > Elimino l'articolo con id " + articleId);
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
