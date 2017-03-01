package virtualStock.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import virtualStock.util.LocalDateAdapter;

public class User {
	
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty address;
    private final StringProperty ssn;
    private final ObjectProperty<LocalDate> birthday;
    private final StringProperty phoneNumber;
    private final StringProperty address2;
    private final StringProperty emailAddress;
    private final StringProperty bankRouting;
    private final StringProperty bankAccount;
    private final StringProperty cash;
    
    

    /**
     * Default constructor.
     */
    public User() {
        this(null, null);
    }
    
    
    public User(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.address = new SimpleStringProperty("some street");
        this.ssn = new SimpleStringProperty("666-66-6666");
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1990, 5, 24));
        this.phoneNumber = new SimpleStringProperty("404-434-0000");
        this.address2 = new SimpleStringProperty("some apartment");
        this.emailAddress = new SimpleStringProperty("mzhu7@gsu.edu");
        this.bankRouting = new SimpleStringProperty("000000");
        this.bankAccount = new SimpleStringProperty("123456");
        this.cash = new SimpleStringProperty("250.00");
    }
    
    
    
    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String firstName) {
        this.phoneNumber.set(firstName);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getStreet() {
        return address.get();
    }

    public void setStreet(String street) {
        this.address.set(street);
    }

    public StringProperty streetProperty() {
        return address;
    }
    
    public String getSSN() {
        return ssn.get();
    }

    public void setSSN(String ssn) {
        this.ssn.set(ssn);
    }

    public StringProperty ssnProperty() {
        return ssn;
    }

    
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getBirthday() {
        return birthday.get();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }
    
}
