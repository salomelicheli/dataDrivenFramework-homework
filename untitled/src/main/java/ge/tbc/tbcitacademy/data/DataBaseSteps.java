package ge.tbc.tbcitacademy.data;

import ge.tbc.tbcitacademy.helper.RegistrationData;
import ge.tbc.tbcitacademy.jdbcsteps.SQLConnection;
import org.testng.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBaseSteps {
    public static ResultSet getResults(Connection connection, String tableName) throws SQLException {
        String SQL = "SELECT * FROM " + tableName;
        Statement statement = connection.createStatement();
        return statement.executeQuery(SQL);
    }

    public int insertRegistrationData(List<RegistrationData> dataList) {
        try (Connection connection = SQLConnection.connect()) {
            String sql = """
                    INSERT INTO dbo.RegistrationData VALUES(?,?,?,?,?,?,?,?,?,?);
                    """;
            PreparedStatement preparedStatement = null;
            int rows = 0;
            for (int i = 0; i < dataList.size(); i++) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, dataList.get(i).getId());
                preparedStatement.setString(2, dataList.get(i).getFirstName());
                preparedStatement.setString(3, dataList.get(i).getLastName());
                preparedStatement.setString(4, dataList.get(i).getGender());
                preparedStatement.setString(5, dataList.get(i).getModel());
                preparedStatement.setString(6, dataList.get(i).getAddress1());
                preparedStatement.setString(7, dataList.get(i).getAddress2());
                preparedStatement.setString(8, dataList.get(i).getCity());
                preparedStatement.setString(9, dataList.get(i).getContact1());
                preparedStatement.setString(10, dataList.get(i).getContact2());
                rows += preparedStatement.executeUpdate();
            }
            System.out.printf("%d rows have been affected\n", rows);
            return rows;
        } catch (SQLException e) {
            e.getStackTrace();
            return 0;
        }
    }

    public int insertPhones() {
        try (Connection connection = SQLConnection.connect()) {
            String selectOwnersId = """
                    USE Registration;
                    SELECT TOP 8 id FROM RegistrationData;
                    """;
            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery(selectOwnersId);
            String sql = """
                    INSERT INTO dbo.Phones VALUES(?,?);
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            String[] phoneNumbers = {"568992235", "557318094", "238477277", "398478287", "348768728",
                    "234766777", "384578788", "374587982"};
            int rows = 0;
            int index = phoneNumbers.length;
            while (index >= 0 && resultSet.next()) {
                int ownerId = resultSet.getInt("id");
                preparedStatement.setString(1, phoneNumbers[index - 1]);
                preparedStatement.setInt(2, ownerId);
                rows += preparedStatement.executeUpdate();
                index--;
            }
            return rows;
        } catch (SQLException e) {
            e.getStackTrace();
            return 0;
        }
    }

    public int updateRegistrations() {
        try (Connection connection = SQLConnection.connect()) {
            String selectOwnersId = """
                    USE Registration;
                    SELECT TOP 2 id FROM RegistrationData;
                    """;
            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery(selectOwnersId);
            String numbersAndDataQuery = """
                    UPDATE RegistrationData
                    SET address1 = ?, address2 = ?
                    WHERE id = ?
                    """;
            PreparedStatement preparedStatement = null;
            List<RegistrationData> data = new ArrayList<>(Arrays.asList(
                    new RegistrationData("sadgac", "addr2"),
                    new RegistrationData("samefo kari", "addr2")
            ));
            int rows = 0;
            int i = 0;
            while (resultSet.next()) {
                preparedStatement = connection.prepareStatement(numbersAndDataQuery);
                preparedStatement.setString(1, data.get(i).getAddress1());
                preparedStatement.setString(2, data.get(i).getAddress2());
                preparedStatement.setInt(3, resultSet.getInt("id"));
                rows += preparedStatement.executeUpdate();
                i++;
            }
            return rows;
        } catch (SQLException e) {
            e.getStackTrace();
            return 0;
        }
    }

    public int updatePhoneNumbers() {
        try (Connection connection = SQLConnection.connect()) {
            String selectOwnersId = """
                    USE Registration;
                    SELECT TOP 2 ownerId FROM Phones;
                    """;
            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery(selectOwnersId);
            String numbersAndDataQuery = """
                    UPDATE Phones
                    SET phoneNumber = ?
                    WHERE ownerId = ?
                    """;
            String[] phoneNumbers = new String[]{"238472722", "329848232"};
            PreparedStatement preparedStatement = null;
            int rows = 0;
            int i = 0;
            while (resultSet.next()) {
                preparedStatement = connection.prepareStatement(numbersAndDataQuery);
                preparedStatement.setString(1, phoneNumbers[i]);
                preparedStatement.setInt(2, resultSet.getInt("ownerId"));
                rows += preparedStatement.executeUpdate();
                i++;
            }
            return rows;
        } catch (SQLException e) {
            e.getStackTrace();
            return 0;
        }
    }

    public int updatingLastNameOfARegistration() {
        try (Connection connection = SQLConnection.connect()) {
            String lastNameUpdateQuery = """
                    UPDATE RegistrationData
                    SET lastName = ?
                    WHERE id = 16
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(lastNameUpdateQuery);
            preparedStatement.setString(1, "Tandzieli");
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
            return 0;
        }
    }
    public int autoCommitTest() throws SQLException {
        Connection connection = SQLConnection.connect();
        try (connection) {
            connection.setAutoCommit(false);
            String sql = """
                    INSERT INTO dbo.RegistrationData VALUES(1002,'Juansher','Juansheriani','male'
                    ,'Mega 123 Large screen','address1','address2','tiflisi','contact1','contact2')
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " affected after setting commit to false");
            connection.commit();
            return rows;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
