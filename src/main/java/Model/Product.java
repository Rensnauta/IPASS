package Model;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.Date;

public class Product extends HttpServlet {
    private int productNr;
    private String name;
    private String category;
    private Date expirationDate;
    private int stock;
    private double price;
    private static ArrayList<Product> products = new ArrayList<>();

    public Product(String name, int productNr, String category, Date expirationDate, int stock, double price) {
        this.name = name;
        this.productNr = productNr;
        this.category = category;
        this.expirationDate = expirationDate;
        this.stock = stock;
        this.price = price;
        products.add(this);
        System.out.println("Product added to list");
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public String getName() {
        return name;
    }
}
