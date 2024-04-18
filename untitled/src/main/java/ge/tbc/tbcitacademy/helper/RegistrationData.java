package ge.tbc.tbcitacademy.helper;

public class RegistrationData {
    private Integer id;
    private String firstName;
    private String lastName;
    private String gender;
    private String model;
    private String address1;
    private String address2;
    private String city;
    private String contact1;
    private String contact2;

    public RegistrationData(Integer id, String firstName, String lastName, String gender, String model,
                            String address1, String address2, String city, String contact1, String contact2) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.model = model;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.contact1 = contact1;
        this.contact2 = contact2;
    }

    public RegistrationData(String address1, String address2) {
        this.address1 = address1;
        this.address2 = address2;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getModel() {
        return model;
    }

    public String getAddress1() {
        return address1;
    }

    public String getContact1() {
        return contact1;
    }

    public String getCity() {
        return city;
    }

    public String getAddress2() {
        return address2;
    }

    public String getContact2() {
        return contact2;
    }
}
