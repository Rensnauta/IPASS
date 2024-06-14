package server.website.DAO;


import server.website.Model.Product;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import server.website.utils.DataSourceProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ProductDAO {
    public static void retrieveProducts() {
        Product.removeAllProducts();
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
}

