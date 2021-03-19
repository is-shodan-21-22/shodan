package Control;

import Model.Article;
import Service.ArticleService;
import java.io.IOException;
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
		
		switch(request.getParameter("action")) {
			case "blog":
				int limit = request.getParameter("limit") != null 
							? Integer.parseInt(request.getParameter("limit")) 
							: 0;
	
				request.getSession().setAttribute("articles", new ArticleService().getAllArticles(limit));
				
				System.out.println("# BlogServlet > GET > Tutti gli articoli");
				
				break;
			
			case "article":
				int blog_id = request.getParameter("blog_id") != null 
							? Integer.parseInt(request.getParameter("blog_id")) 
							: 0;
				
				Article article = new ArticleService().getArticle(blog_id);
				
				System.out.println("# BlogServlet > GET > " + article.toString());
				
				request.getSession().setAttribute("article", article);
				
				break;
				
			default:
				System.out.println("# BlogServlet > GET > Nessuna azione specificata");
				
				break;
		}
	}
	
}
