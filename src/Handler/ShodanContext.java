package Handler;

import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import Database.DBConnectionPool;

@WebListener
public class ShodanContext implements ServletContextListener {
	public void contextInitialized(ServletContextEvent event) {	
		try {
			Connection databaseConnection = DBConnectionPool.getConnection();
			event.getServletContext().setAttribute("databaseConnection", databaseConnection);	
			System.out.println("# ShodanContext > Connessione al database stabilita");
		} catch (SQLException e) {
			System.out.println("# ShodanContext > Impossibile generare una connessione al database");
		}
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		event.getServletContext().removeAttribute("databaseConnection");
	}
}

