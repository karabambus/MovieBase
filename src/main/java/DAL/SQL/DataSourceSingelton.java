package DAL.SQL;

import Exceptions.DatabaseException;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public final class DataSourceSingelton {

    // Path to the database configuration file
    public static final String PATH = "/config/db.properties";

    // Properties object to hold the database configuration
    private static final Properties PROPERTIES = new Properties();

    // Configuration keys for the database
    public static final String SERVER_NAME = "SERVER_NAME";
    public static final String DATABASE_NAME = "DATABASE_NAME";
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";

    // Static block to load the properties file
    static {
        try (InputStream in = DataSourceSingelton.class.getResourceAsStream(PATH)) {
            if (in == null) {
                throw new DatabaseException("Database configuration file not found.");
            }
            PROPERTIES.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Private constructor to prevent instantiation
    private DataSourceSingelton() {}

    // Singleton instance of the DataSource
    private static DataSource instance;

    public static DataSource getInstance() throws DatabaseException {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }

    private static DataSource createInstance() throws DatabaseException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName(PROPERTIES.getProperty(SERVER_NAME));
        dataSource.setDatabaseName(PROPERTIES.getProperty(DATABASE_NAME));
        dataSource.setUser(PROPERTIES.getProperty(USERNAME));
        dataSource.setPassword(PROPERTIES.getProperty(PASSWORD));
        try {
            dataSource.setProperty("escapeSyntaxCallMode", "callIfNoReturn");
        } catch (SQLException e) {
            throw new DatabaseException("Failed to configure the DataSource", e);
        }
        return dataSource;
    }
}
