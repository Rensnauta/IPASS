package server.website.Model;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.Date;

public class Product extends HttpServlet {
    private int productNr;
    private String name;
    private String categoryId;
    private Date expirationDate;
    private int stock;
    private double price;
    private static ArrayList<Product> products = new ArrayList<>();

    public Product(String name, int productNr, int category, Date expirationDate, int stock, double price) {
        this.name = name;
        this.productNr = productNr;
        this.categoryId = Category.getCategoryNameById(category);
        this.expirationDate = expirationDate;
        this.stock = stock;
        this.price = price;
        products.add(this);
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public String getName() {
        return name;
    }

    public int getProductNr() {
        return productNr;
    }

    public static int getProductsSize() {
        return products.size();
    }

    public static void removeAllProducts() {
        products.clear();
    }

    public String getCategory() {
        return categoryId;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Product getProductByProductNr(int productNr) {
        for (Product product : products) {
            if (product.getProductNr() == productNr) {
                return product;
            }
        }
        return null;
    }

    public static Product getOrCreateProduct(String name, int productNr, int category, Date expirationDate, int stock, double price) {
        for (Product product : products) {
            if (product.getProductNr() == productNr) {
                return product;
            }
        }
        return new Product(name, productNr, category, expirationDate, stock, price);
    }



    public Date getExpirationDate() {
        return expirationDate;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }
}
