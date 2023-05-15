package model;

import java.time.LocalDate;
import java.util.regex.Pattern;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The abstract User class represents a basic user in a system. It includes properties such as
 * the user's name, email, password, and address.
 */
public abstract class User {

	/**
	 * Declaring all the Class properties
	 */
	private StringProperty CF = new SimpleStringProperty(this, "CF");
	private StringProperty email = new SimpleStringProperty(this, "email");
	private StringProperty password = new SimpleStringProperty(this, "password");
	private StringProperty name = new SimpleStringProperty(this, "name");
	private StringProperty surname = new SimpleStringProperty(this, "surname");
	private StringProperty sex = new SimpleStringProperty(this, "sex");
	private ObjectProperty<LocalDate> birthdate = new SimpleObjectProperty<>(this, "birthdate", null);
	private StringProperty nationality = new SimpleStringProperty(this, "nationality");
	private StringProperty street = new SimpleStringProperty(this, "street");
	private IntegerProperty civic_number = new SimpleIntegerProperty(this, "civic_number");
	private IntegerProperty cap = new SimpleIntegerProperty(this, "cap");
	private StringProperty city = new SimpleStringProperty(this, "city");
	private StringProperty phone_number = new SimpleStringProperty(this, "phone_number");
	
	/**
     * Creates a new User object with the specified properties.
     * 
     * @param CF            the user's Codice Fiscale (tax code)
     * @param email         the user's email address
     * @param password      the user's password
     * @param name          the user's first name
     * @param surname       the user's last name
     * @param sex           the user's gender
     * @param birthdate     the user's date of birth
     * @param nationality   the user's nationality
     * @param street        the user's street address
     * @param civic_number  the user's civic number (address number)
     * @param cap           the user's postal code
     * @param city          the user's city
     * @param phone_number  the user's phone number
     * @throws IllegalArgumentException if any of the specified values are null, or if the
     *         CF, email, or phone_number values are invalid
     */
	public User(String CF, String email, String password, String name, String surname, String sex, LocalDate birthdate, String nationality, String street, int civic_number, int cap, String city, String phone_number) {
		if (CF == null || email == null || password == null || name == null || surname == null || sex == null || birthdate == null || nationality == null || street == null || city == null || phone_number == null)
			throw new IllegalArgumentException("Something is null!");
		//checking if the given values are valid
		if (!isValidCF(CF))
			throw new IllegalArgumentException("Invalid CF");
		if (!isValidEmail(email))
			throw new IllegalArgumentException("Invalid Email");
		if (!isValidPhoneNumber(phone_number))
			throw new IllegalArgumentException("Invalid Phone Number");
		
		//set all the attributes of physician
		this.CF.set(CF);
		this.email.set(email);
		this.password.set(password);
		this.name.set(name);
		this.surname.set(surname);
		this.sex.set(sex);
		this.birthdate.set(birthdate);
		this.nationality.set(nationality);
		this.street.set(street);
		this.civic_number.set(civic_number);
		this.cap.set(cap);
		this.city.set(city);
		this.phone_number.set(phone_number);
	}
	
	/**
     * Returns true if the specified CF value is a valid Codice Fiscale, false otherwise.
     * 
     * @param CF  the Codice Fiscale to validate
     * @return true if the CF is valid, false otherwise
     */
	private static boolean isValidCF(String CF) {
		if (CF.length() != 16)return false;
		CF = CF.toUpperCase();
		int i;
		for (i=0; i<6; i++) {
			if (Character.isDigit(CF.charAt(i)))return false;
		}
		
		for (i = 6; i<8; i++) {
			if (Character.isAlphabetic(CF.charAt(i)))return false;
		}
		
		if (Character.isDigit(CF.charAt(i)))return false;
		for (i = 9; i<11; i++) {
			if (Character.isAlphabetic(CF.charAt(i)))return false;
		}
		if (Character.isDigit(CF.charAt(i)))return false;
		for (i = 12; i<15; i++) {
			if (Character.isAlphabetic(CF.charAt(i)))return false;
		}
		if (Character.isDigit(CF.charAt(15)))return false;
		return true;
	}
	
	/**
     * Returns true if the specified phone_number value is valid, false otherwise.
     * 
     * @param phone_number  the phone number to validate
     * @return true if the phone number is valid, false otherwise
     */
	private static boolean isValidPhoneNumber(String phone_number) {	
		if (phone_number.length() != 10)return false;
		
		for (int i=0; i<phone_number.length(); i++) {
			if (Character.isAlphabetic(phone_number.charAt(i)))return false;
		}
		return true;
	}
	
	/**
     * Returns true if the specified email value is valid, false otherwise.
     * 
     * @param email  the email address to validate
     * @return true if the email is valid, false otherwise
     */
	public static boolean isValidEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

	/**
     * Returns the user's Codice Fiscale (tax code).
     * 
     * @return the user's CF
     */
	public String getCF() {
		return CF.get();
	}
	
	/**
     * Returns the StringProperty for the user's CF.
     * 
     * @return the CF StringProperty
     */
	public StringProperty CFProperty() {
		return CF;
	}
	
	/**
     * Returns the StringProperty for the user's CF.
     * 
     * @return the CF StringProperty
     */
	public String getEmail() {
		return email.get();
	}
	
	/**
     * Returns the StringProperty for the user's email.
     * 
     * @return the email StringProperty
     */
	public StringProperty emailProperty() {
		return email;
	}
	
	/**
     * Returns the user's password.
     * 
     * @return the user's password
     */
	public String getPassword() {
		return password.get();
	}
	
	/**
     * Returns the StringProperty for the user's password.
     * 
     * @return the password StringProperty
     */
	public StringProperty passwordProperty() {
		return password;
	}
	
	/**
     * Returns the user's first name.
     * 
     * @return the user's first name
     */
	public String getName() {
		return name.get();
	}
	
	/**
     * Returns the StringProperty for the user's name.
     * 
     * @return the name StringProperty
     */
	public StringProperty nameProperty() {
		return name;
	}
	
	/**
     * Returns the user's surname.
     * 
     * @return the user's surname
     */
	public String getSurname() {
		return surname.get();
	}
	
	/**
     * Returns the StringProperty for the user's surname.
     * 
     * @return the surname StringProperty
     */
	public StringProperty surnameProperty() {
		return surname;
	}
	
	/**
     * Returns the user's gender.
     * 
     * @return the user's gender
     */
	public String getSex() {
		return sex.get();
	}
	
	/**
     * Returns the StringProperty for the user's gender.
     * 
     * @return the gender StringProperty
     */
	public StringProperty sexProperty() {
		return sex;
	}
	
	/**
     * Returns the user's birthdate.
     * 
     * @return the user's birthdate
     */
	public LocalDate getBirthdate() {
		return birthdate.get();
	}
	
	/**
     * Returns the StringProperty for the user's birthdate.
     * 
     * @return the birthdate LocalDate Property
     */
	public ObjectProperty<LocalDate> birthdateProperty() {
		return birthdate;
	}
	
	/**
     * Returns the user's nationality.
     * 
     * @return the user's nationality
     */
	public String getNationality() {
		return nationality.get();
	}
	
	/**
     * Returns the StringProperty for the user's nationality.
     * 
     * @return the nationality StringProperty
     */
	public StringProperty nationalityProperty() {
		return nationality;
	}
	
	/**
     * Returns the user's street.
     * 
     * @return the user's street
     */
	public String getStreet() {
		return street.get();
	}
	
	/**
     * Returns the StringProperty for the user's street.
     * 
     * @return the street StringProperty
     */
	public StringProperty streetProperty() {
		return street;
	}
	
	/**
     * Returns the user's civic number.
     * 
     * @return the user's civic number
     */
	public int getCivicNumber() {
		return civic_number.get();
	}
	
	/**
     * Returns the StringProperty for the user's civic number.
     * 
     * @return the civic number IntegerProperty
     */
	public IntegerProperty civicNumberProperty() {
		return civic_number;
	}
	
	/**
     * Returns the user's CAP.
     * 
     * @return the user's CAP
     */
	public int getCAP() {
		return cap.get();
	}
	
	/**
     * Returns the StringProperty for the user's CAP.
     * 
     * @return the CAP IntegerProperty
     */
	public IntegerProperty capProperty() {
		return cap;
	}
	
	/**
     * Returns the user's city.
     * 
     * @return the user's city
     */
	public String getCity() {
		return city.get();
	}
	
	/**
     * Returns the StringProperty for the user's city.
     * 
     * @return the city StringProperty
     */
	public StringProperty cityProperty() {
		return city;
	}
	
	/**
     * Returns the user's phone number.
     * 
     * @return the user's phone number
     */
	public String getPhoneNumber() {
		return phone_number.get();
	}
	
	/**
     * Returns the StringProperty for the user's phone number.
     * 
     * @return the phone number StringProperty
     */
	public StringProperty phoneNumberProperty() {
		return phone_number;
	}
	
	/**
	 * Returns a string representation of the User.
	 * 
	 * @return a string representation of the User
	 */
	public abstract String toString();
	
}
