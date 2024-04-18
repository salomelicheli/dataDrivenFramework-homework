package ge.tbcitacademy;

import ge.tbc.tbcitacademy.data.DataBaseProvider;
import ge.tbc.tbcitacademy.data.DataBaseSteps;
import ge.tbc.tbcitacademy.helper.RegistrationData;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ge.tbc.tbcitacademy.data.Constants.*;

public class DataBaseTests2 {
    DataBaseSteps dbSteps;
    @BeforeClass
    public void setUp() {
        dbSteps = new DataBaseSteps();
    }
    @Test
    public void insertNumbers() {
        int rows = dbSteps.insertPhones();
        System.out.println(rows + rowsAffected);
    }
    @Test
    public void inserting2() {
        List<RegistrationData> data= new ArrayList<>(Arrays.asList(
            new RegistrationData(111, "Mamulia", "Konveiri", male,
                    SerenePad64G, "samargalo", "addr2", "ct", "con1", "con2"),
            new RegistrationData(71, "Sedraq", "Finezis vezriri", male,
                    SerenePad64G, "samefo kari", "addr2", "ct", "con1", "con2"),
            new RegistrationData(81, "Ruqa", "Finezis vezriri", male,
                    SerenePad32G, "samefo kari", "addr2", "ct", "con1", "con2"),
            new RegistrationData(91, "Efsia", "Direqtori", male,
                    MegaLargeScreen, "Samargalo", "addr2", "ct", "con1", "con2"),
            new RegistrationData(15, "Sulxan-saba", "Orbeliani", male,
                    mediumScreen, "qvemo qartli", "addr2", "tandzia", "con1", "con2")
        ));
        int rows = dbSteps.insertRegistrationData(data);
        System.out.println(rows + rowsAffected);
    }
    @Test
    public void updateRegistrationValues() {
        int rows = dbSteps.updateRegistrations();
        System.out.println(rows + rowsAffected);
    }
    @Test
    public void updateNumberValues() {
        int rows = dbSteps.updatePhoneNumbers();
        System.out.println(rows + rowsAffected);
    }
    @Test(dataProvider = "dataProvider", dataProviderClass = DataBaseProvider.class)
    public void testingWithDataProvider(String firstName, String lastName, String number) {
        System.out.printf("Name: %s %s, tel: %s\n", firstName, lastName, number);
    }

    @Test
    public void autoCommitTest() throws SQLException {
        int rowsAffected = dbSteps.autoCommitTest();
    }

    @Test
    public void changingLastNameTest(){
        int rowsAffected = dbSteps.updatingLastNameOfARegistration();
        Assert.assertEquals(rowsAffected, 1);
    }
}
