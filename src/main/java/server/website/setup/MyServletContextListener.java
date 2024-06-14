package server.website.setup;

import server.website.DAO.CategoryDAO;
import server.website.DAO.ProductDAO;
import server.website.Model.Category;
import server.website.Model.MyUser;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        new MyUser("test", "password");
        CategoryDAO.retrieveCategories();
        System.out.println("Application started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application destroyed");
    }
}
