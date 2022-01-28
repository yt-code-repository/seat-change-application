package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import model.DbConnection;

/**
 * Application Lifecycle Listener implementation class SeatChangeListener
 *
 */
@WebListener
public class SeatChangeListener implements ServletContextListener {

    /**
     * Default constructor.
     */
    public SeatChangeListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  {
         // TODO Auto-generated method stub
		//DB接続を切断する
		DbConnection.dbDisconnection();
		System.out.println("seat-change-application: DB connection is close.");
		ServletContext sc = sce.getServletContext();
		sc.log(sc.getServletContextName() + "END!!");
	}

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  {
         // TODO Auto-generated method stub
		// Web.xmlからcontextを取得
		ServletContext srvc = sce.getServletContext();

		DbConnection.jdbcDriver = srvc.getInitParameter("jdbcDriver");
		DbConnection.jdbcUri = srvc.getInitParameter("jdbcUri");
		DbConnection.dbUser = srvc.getInitParameter("dbUser");
		DbConnection.dbPassword = srvc.getInitParameter("dbPassword");

		//DBに接続
		boolean dbc = DbConnection.dbConnection();

		System.out.println("seat-change-application: DB connection is done." + dbc);
		ServletContext sc = sce.getServletContext();
		sc.log(sc.getServletContextName() + "START!!");
    }

}
