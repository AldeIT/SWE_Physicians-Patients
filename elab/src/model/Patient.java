package model;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Patient extends User {
	
	private StringProperty informations = new SimpleStringProperty(this, "informations");
	private StringProperty CFPhysician = new SimpleStringProperty(this, "CFPhysician");

	public Patient(String CF, String email, String password, String name, String surname, String sex,
			LocalDate birthdate, String nationality, String street, int civic_number, int cap, String city,
			String phone_number, String informations, String CFPhysician) {
		super(CF, email, password, name, surname, sex, birthdate, nationality, street, civic_number, cap, city,
				phone_number);
		this.informations.set(informations);
		this.CFPhysician.set(CFPhysician);
	}
	
	/*gets the informations*/
	public String getInformations() {
		return informations.get();
	}
	
	/*gets the informationsProperty*/
	public StringProperty informationsProperty() {
		return informations;
	}
	
	/*gets the CFPhysician*/
	public String getCFPhysician() {
		return CFPhysician.get();
	}
	
	/*gets the CFPhysicianProperty*/
	public StringProperty CFPhysicianProperty() {
		return CFPhysician;
	}
	
	@Override
	public String toString() {
		return "Patient: " + getCF() + ", " + getEmail() + ", " + getName() + ", " + getSurname() + ", " + getCFPhysician();
	}

}
