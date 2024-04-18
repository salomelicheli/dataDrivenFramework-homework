package ge.tbc.tbcitacademy.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseSteps {
     public static ResultSet getResults(Connection connection, String tableName) throws SQLException {
         String SQL = "SELECT * FROM " + tableName;
         Statement statement = connection.createStatement();
         return statement.executeQuery(SQL);
     }
}
