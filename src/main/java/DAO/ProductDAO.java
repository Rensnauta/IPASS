package DAO;


import Model.Product;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ProductDAO {
    public static void retrieveProducts() {
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:postgresql://aws-0-eu-central-1.pooler.supabase.com:6543/postgres");
        p.setDriverClassName("org.postgresql.Driver");
        p.setUsername("postgres.jsrjkbulxchlrombsxks");
        p.setPassword("zS83wKIA6qn8zmet");

        DataSource datasource = new DataSource();
        datasource.setPoolProperties(p);

        Connection con = null;
        try {
            con = datasource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from productdata");
            while (rs.next()) {
                String name = rs.getString("productname");
                int productNr = rs.getInt("productnr");
                Date expirationDate = rs.getDate("expirationdate");
                int stock = rs.getInt("stock");
                String category = rs.getString("category");
                double price = rs.getDouble("price");
                System.out.println("data retrieved");
                new Product(name, productNr, category, expirationDate, stock, price);
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

