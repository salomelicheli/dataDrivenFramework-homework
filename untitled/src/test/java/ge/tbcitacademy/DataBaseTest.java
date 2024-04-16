package ge.tbcitacademy;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import ge.tbc.tbcitacademy.jdbcsteps.SQLConnection;
import ge.tbc.tbcitacademy.data.DataBaseSteps;
import ge.tbc.tbcitacademy.steps.RegistrationPageSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.*;
import static com.codeborne.selenide.Selenide.open;
import static ge.tbc.tbcitacademy.data.Constants.*;

public class DataBaseTest {
    RegistrationPageSteps steps;
    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        Configuration.timeout = 7;
        steps = new RegistrationPageSteps();
    }
    @BeforeMethod
    public void openBrowser(){
        open(registrationPage);
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }
    @AfterMethod
    public void closeBrowser() {
        WebDriverRunner.getWebDriver().close();
    }
    @Test
    public void fillingData(){
        try (Connection connection = SQLConnection.connect()) {
            ResultSet results = DataBaseSteps.getResults(connection, tableName);
            while (results.next()) {
                steps.fillInFirstName(results.getString(firstName))
                        .fillInLastName(results.getString(lastName))
                        .chooseGender(results.getString(gender))
                        .chooseAModel(results.getString(model1))
                        .fillInAddressOne(results.getString(address1))
                        .fillInAddressTwo(results.getString(address2))
                        .fillInCity(results.getString(city))
                        .fillInContactOne(results.getString(contact1))
                        .fillInContactTwo(results.getString(contact2))
                        .refreshingAPage();
            }
        }catch (SQLException | RuntimeException e) {}
    }
}