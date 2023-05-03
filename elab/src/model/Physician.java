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
	
	public Physician(String CF, String email, String password, String name, String surname, String sex,
			LocalDate birthdate, String nationality, String street, int civic_number, int cap, String city,
			String phone_number) {
		super(CF, email, password, name, surname, sex, birthdate, nationality, street, civic_number, cap, city, phone_number);
		// TODO Auto-generated constructor stub
	}
	
	public Physician(Physician physician) {
		super(physician.getCF(), physician.getEmail(), physician.getPassword(), physician.getName(), physician.getSurname(), physician.getSex(), physician.getBirthdate(), physician.getNationality(), physician.getStreet(), physician.getCivicNumber(), physician.getCAP(), physician.getCity(), physician.getPhoneNumber());
	}

	public String toString() {
		return "Physician: " + getCF() + ", " + getEmail() + ", " + getName() + ", " + getSurname();
	}
}
