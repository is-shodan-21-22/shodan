package Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Control.DBConnectionPool;
import Model.Article;

public class ArticleService {

	private Connection db;
	private Statement statement;
	
	public ArticleService() {
		try {
			db = DBConnectionPool.getConnection();
			statement = db.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Article getArticle(int id) {
		Article article = null;
		
		try {
			ResultSet result = statement.executeQuery("SELECT * FROM blog WHERE blog_id = " + id);
			
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
		String query = "SELECT * FROM blog" + (limit != 0 ? (" LIMIT " + limit) : "");
		
		try {
			ResultSet result = statement.executeQuery(query);
			
			System.out.println("# ArticleService > Query > " + query);
			
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

}
