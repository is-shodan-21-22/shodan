package Control;

import Model.Article;
import Service.ArticleService;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BlogServlet")
public class BlogServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		System.out.println("# BlogServlet > Session: " + request.getSession().getId());
		
		Connection db = (Connection) request.getServletContext().getAttribute("databaseConnection");
		
		String endpoint = request.getParameter("endpoint");
		
		switch(request.getParameter("action")) {
			case "blog":
				int limit = request.getParameter("limit") != null 
							? Integer.parseInt(request.getParameter("limit")) 
							: 0;
	
				ArrayList<Article> articles = new ArticleService(db).getAllArticles(limit);
				
				if(articles != null) {
					request.setAttribute("articles", articles);
					request.getRequestDispatcher(endpoint).forward(request, response);
					response.setStatus(200);
				} else 
					response.setStatus(400);
				
				System.out.println("# BlogServlet > GET > Tutti gli articoli");
				
				break;
			
			case "article":
				int blog_id = request.getParameter("blog_id") != null 
							? Integer.parseInt(request.getParameter("blog_id")) 
							: 0;
				
				Article article = new ArticleService(db).getArticle(blog_id);
				
				System.out.println("# BlogServlet > GET > " + article.toString());
				
				request.setAttribute("article", article);
				request.getRequestDispatcher(endpoint).forward(request, response);
				
				break;
				
			default:
				System.out.println("# BlogServlet > GET > Nessuna azione specificata");
				
				break;
		}
	}
	
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response
	) throws ServletException, IOException {
		System.out.println("# BlogServlet > Session: " + request.getSession().getId());
		
		Connection db = (Connection) request.getServletContext().getAttribute("databaseConnection");
		
		switch(request.getParameter("action")) {
			case "addArticle":
			
				new ArticleService(db).addArticle(request.getParameter("add-article-title"),
						request.getParameter("article-shortTitle"), 
						request.getParameter("article-html"));
		
				request.setAttribute("messageArticleAdd", "Articolo aggiunto con successo");
				request.getRequestDispatcher("admin.jsp").forward(request, response);
		
				System.out.println("# BlogServlet > POST > Articolo aggiunto > " + request.getParameter("add-article-title"));
		
				break;
		
			case "deleteArticle":
		
				Article article = new ArticleService(db).getArticle(Integer.valueOf(request.getParameter("delete-article-id")));
				
				if(article != null) {
					new ArticleService(db).deleteArticle(Integer.valueOf(request.getParameter("delete-article-id")));
			
					request.setAttribute("messageArticleDelete", "Articolo eliminato con successo");
					request.getRequestDispatcher("admin.jsp").forward(request, response);
					
					System.out.println("# BlogServelt > POST > Articolo eliminato > " + article.getTitle());
				} else {
					request.setAttribute("errorMessageArticleDelete", "Articolo non presente");
					request.getRequestDispatcher("admin.jsp").forward(request, response);
					
					System.out.println("# BlogServelt > POST > Articolo insistente > " + request.getParameter("add-article-title"));
				}
		
			default:
				System.out.println("# BlogServelt > POST > Nessuna azione specificata");
			
			break;
		}
	}
	
}
