package server.website.DAO;


import server.website.Model.Product;
import server.website.utils.DataSourceProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProductDAO {
    public static void retrieveProducts() {
        Connection con = null;
        try {
            con = DataSourceProvider.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from productdata");
            while (rs.next()) {
                String name = rs.getString("productname");
                int productNr = rs.getInt("productnr");
                Date expirationDate = rs.getDate("expirationdate");
                int stock = rs.getInt("stock");
                int category = rs.getInt("category");
                double price = rs.getDouble("price");
                Product.getOrCreateProduct(name, productNr, category, expirationDate, stock, price);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (con!=null) try {con.close();}catch (Exception ignore) {}
        }
    }

    public static void updateProduct(Product product) {
        Connection con = null;
        try {
            con = DataSourceProvider.getDataSource().getConnection();
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE productdata SET productname = '" + product.getName() + "', " +
                    "expirationdate = '" + new java.sql.Date(product.getExpirationDate().getTime()) + "', " +
                    "stock = " + product.getStock() + ", " +
                    "price = " + product.getPrice() +
                    " WHERE productnr = " + product.getProductNr());
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (con!=null) try {con.close();}catch (Exception ignore) {}
        }
    }

    public static List<Product> getExpiredOrExpiringProducts() {
        List<Product> products = new ArrayList<>();
        Connection con = null;
        try {
            con = DataSourceProvider.getDataSource().getConnection();
            Statement st = con.createStatement();

            // Get tomorrow's date
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            Date tomorrow = calendar.getTime();
            java.sql.Date sqlTomorrow = new java.sql.Date(tomorrow.getTime());

            ResultSet rs = st.executeQuery("SELECT * FROM productdata WHERE expirationdate <= '" + sqlTomorrow + "'");
            while (rs.next()) {
                String name = rs.getString("productname");
                int productNr = rs.getInt("productnr");
                Date expirationDate = rs.getDate("expirationdate");
                int stock = rs.getInt("stock");
                int category = rs.getInt("category");
                double price = rs.getDouble("price");
                products.add(new Product(name, productNr, category, expirationDate, stock, price));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ignore) {
                }
            }
        }
        return products;
    }
}

