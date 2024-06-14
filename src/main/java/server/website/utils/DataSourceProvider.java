package server.website.utils;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class DataSourceProvider {

    private static DataSource dataSource;

    static {
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:postgresql://aws-0-eu-central-1.pooler.supabase.com:6543/postgres");
        p.setDriverClassName("org.postgresql.Driver");
        p.setUsername("postgres.jsrjkbulxchlrombsxks");
        p.setPassword("zS83wKIA6qn8zmet");

        dataSource = new DataSource();
        dataSource.setPoolProperties(p);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
