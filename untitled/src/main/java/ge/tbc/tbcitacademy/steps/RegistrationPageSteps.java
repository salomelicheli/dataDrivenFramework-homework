package ge.tbc.tbcitacademy.steps;

import ge.tbc.tbcitacademy.pages.RegistrationPage;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.refresh;

public class RegistrationPageSteps {
    RegistrationPage pg = new RegistrationPage();

    public RegistrationPageSteps fillInFirstName(String firstName){
        pg.firstNameField.sendKeys(firstName);
        return this;
    }
    public RegistrationPageSteps fillInLastName(String lastName){
        pg.lastNameField.sendKeys(lastName);
        return this;
    }
    public RegistrationPageSteps chooseGender(String gender){
        pg.genders.filter(attribute("value", gender.toLowerCase())).first().click();
        return this;
    }
    public RegistrationPageSteps fillInAddressOne(String addressOne){
        pg.address1Field.sendKeys(addressOne);
        return this;
    }
    public RegistrationPageSteps fillInAddressTwo(String addressTwo){
        pg.address2Field.sendKeys(addressTwo);
        return this;
    }
    public RegistrationPageSteps fillInCity(String city){
        pg.cityField.sendKeys(city);
        return this;
    }
    public RegistrationPageSteps fillInContactOne(String contactOne){
        pg.contact1Field.sendKeys(contactOne);
        return this;
    }
    public RegistrationPageSteps fillInContactTwo(String contactTwo){
        pg.contact2Field.sendKeys(contactTwo);
        return this;
    }
    public RegistrationPageSteps chooseAModel(String model){
        pg.dropdown.selectOptionContainingText(model);
        return this;
    }
    public void refreshingAPage(){
        refresh();
    }
}
