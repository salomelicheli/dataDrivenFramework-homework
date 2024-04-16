package ge.tbc.tbcitacademy.jdbcsteps;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLConnection {
    private static final Properties properties = new Properties();
    public static Connection connect(){
        try(InputStream input = SQLConnection.class.getClassLoader().getResourceAsStream("database.properties")){
            properties.load(input);
            DriverManager.registerDriver(new SQLServerDriver());

            String jdbcUrl = ConfigurationMethods.getURL();
            String password = ConfigurationMethods.getPassword();
            String username = ConfigurationMethods.getUsername();
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static class ConfigurationMethods{
        static String getURL(){
            return properties.getProperty("db.url");
        }
        static String getUsername(){
            return properties.getProperty("db.username");
        }
        static String getPassword(){
            return properties.getProperty("db.password");
        }
    }
}
