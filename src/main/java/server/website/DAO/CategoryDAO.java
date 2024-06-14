package server.website.DAO;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import server.website.Model.Category;
import server.website.Model.Product;
import server.website.utils.DataSourceProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class CategoryDAO {
    public static void retrieveCategories() {
        Connection con = null;
        try {
            con = DataSourceProvider.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from categorydata");
            while (rs.next()) {
                int Id = rs.getInt("id");
                String name = rs.getString("name");
                new Category(Id, name);
                System.out.println("Category added" + Id + name);
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
