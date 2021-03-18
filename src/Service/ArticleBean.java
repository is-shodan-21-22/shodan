package Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Control.DBConnectionPool;
import Model.Article;

public class ArticleBean {

	private Connection db;
	private Statement statement;
	
	public ArticleBean() {
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
			
			System.out.println("# ArticleBean > Executing SELECT * FROM blog WHERE blog_id = " + id);
			
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
	
	public ArrayList<Article> getAllArticles() {
		ArrayList<Article> blog = new ArrayList<Article>();
		
		try {
			ResultSet result = statement.executeQuery("SELECT * FROM blog");
			
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
