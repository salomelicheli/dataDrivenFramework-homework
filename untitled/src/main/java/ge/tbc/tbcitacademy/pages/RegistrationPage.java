package ge.tbc.tbcitacademy.pages;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    public SelenideElement firstNameField = $(byAttribute("value", "First Name")),
            lastNameField = $(byAttribute("value", "Last Name")),
            address1Field = $(byAttribute("value", "Address1")),
            address2Field = $(byAttribute("value", "Address2")),
            cityField = $(byAttribute("value", "City")),
            contact1Field = $(byAttribute("value", "Contact1")),
            contact2Field = $(byAttribute("value", "Contact2")),
            dropdown = $x("//select[@name='model']");
    public ElementsCollection genders = $$(byAttribute("name", "gender"));
}
