package DAO;

import java.sql.*;

public class ProductDAO {
static String url = "jdbc:postgresql://aws-0-eu-central-1.pooler.supabase.com:6543/postgres?user=postgres.jsrjkbulxchlrombsxks&password=zS83wKIA6qn8zmet";

    public static String test() {
        String result = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            Statement statement = connection.createStatement();

            // Execute a query
            String sql = "SELECT test FROM test";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                // Get the string from the first column
                result = resultSet.getString("test");
                System.out.println("data retrieved");
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        return result;
    }
}

