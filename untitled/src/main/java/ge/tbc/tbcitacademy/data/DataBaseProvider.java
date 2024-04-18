package ge.tbc.tbcitacademy.data;

import ge.tbc.tbcitacademy.jdbcsteps.SQLConnection;
import org.testng.annotations.DataProvider;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseProvider {
    @DataProvider
    public static Object[][] dataProvider() {
        try (Connection connection = SQLConnection.connect()) {
            String SQL = """
                    SELECT RegistrationData.firstName, RegistrationData.lastName, Phones.phoneNumber
                    FROM RegistrationData
                    INNER JOIN Phones ON RegistrationData.id = Phones.ownerId
                    """;
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.last();
            int rows= resultSet.getRow();
            resultSet.beforeFirst();
            Object[][] data = new Object[rows][resultSet.getMetaData().getColumnCount()];
            int index = 0;
            while (resultSet.next()) {
                data[index] = new Object[]{
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("phoneNumber"),
                };
                index++;
            }
            return data;
        } catch (SQLException e) {
            return new Object[][]{};
        }
    }
}

