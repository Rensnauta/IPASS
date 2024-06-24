package server.website.setup;

import server.website.DAO.CategoryDAO;
import server.website.DAO.ProductDAO;
import server.website.Model.Category;
import server.website.Model.MyUser;
import server.website.Model.Product;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        new MyUser("test", "password");
        List<Product> products = ProductDAO.getExpiredOrExpiringProducts();
        System.out.println("Expired or expiring products: " + products.size());
        CategoryDAO.retrieveCategories();
        System.out.println("Application started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application destroyed");
    }
}
