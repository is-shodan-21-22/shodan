package Filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.User;

@WebFilter(urlPatterns = 
	{ 
		"/index.jsp", 
		"/app.jsp", 
		"/admin.jsp" 
	}
)
public class ShodanFilters implements Filter {

	@Override
	public void doFilter(
		ServletRequest request, 
		ServletResponse response, 
		FilterChain chain
	) throws IOException, ServletException {
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse = (HttpServletResponse) response;
		
		User user = (User) hRequest.getSession().getAttribute("user_metadata");
		String uri = hRequest.getRequestURI();
		
		if(uri.contains("/index.jsp") || uri.equals("/Shodan/")) {
			if(user != null)
				hResponse.sendRedirect("app.jsp");
		} else if(uri.contains("/app.jsp")) {
			if(user == null)
				hResponse.sendRedirect("index.jsp");
		} else if(uri.contains("/admin.jsp")) {
			if(user == null || !user.isAdmin())
				hResponse.sendRedirect("app.jsp");
		}
		
		chain.doFilter(hRequest, hResponse);
	}

}