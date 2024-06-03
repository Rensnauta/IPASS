package server.website.setup;

import server.website.DAO.ProductDAO;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDAO.retrieveProducts();
        System.out.println("Application started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application destroyed");
    }
}
