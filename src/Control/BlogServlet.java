package Control;

import Model.Article;
import Service.ArticleBean;
import java.io.IOException;
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
		switch(request.getParameter("action")) {
			case "blog":
				request.getSession().setAttribute("articles", new ArticleBean().getAllArticles());
				
				System.out.println("# BlogServlet > GET > Tutti gli articoli");
				
				break;
			
			case "article":
				Article article = new ArticleBean().getArticle(2);
				
				System.out.println("# BlogServlet > GET > " + article.toString());
				
				request.getSession().setAttribute("article", article);
				
				break;
				
			default:
				System.out.println("# BlogServlet > GET > Nessuna azione specificata");
				break;
		}
	}
	
}
