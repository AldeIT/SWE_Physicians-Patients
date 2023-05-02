package model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class Physician extends User{
	
	
	
	/*Declaring all the Class properties*/
	/*private StringProperty CF = new SimpleStringProperty(this, "CF");
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

	
	public Physician(String CF, String email, String password, String name, String surname, String sex, LocalDate birthdate, String nationality, String street, int civic_number, int cap, String city, String phone_number) {
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
	}*/
	
	/*Checks if the given CF is a valid "Codice Fiscale"
	 * returns true if valid, false otherwise*/
	/*private boolean isValidCF(String CF) {
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
	}*/
	
	/*Checks if the given phone_number is valid 
	 * returns true if valid, false otherwise*/
	/*private boolean isValidPhoneNumber(String phone_number) {	
		if (phone_number.length() != 10)return false;
		
		for (int i=0; i<phone_number.length(); i++) {
			if (Character.isAlphabetic(phone_number.charAt(i)))return false;
		}
		return true;
	}*/
	
	/*Checks if the given email is valid 
	 * returns true if valid, false otherwise*/
	/*public static boolean isValidEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }*/

	
	
	
	public Physician(String CF, String email, String password, String name, String surname, String sex,
			LocalDate birthdate, String nationality, String street, int civic_number, int cap, String city,
			String phone_number) {
		super(CF, email, password, name, surname, sex, birthdate, nationality, street, civic_number, cap, city, phone_number);
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "Physician: " + getCF() + ", " + getEmail() + ", " + getName() + ", " + getSurname();
	}
}
