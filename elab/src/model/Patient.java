package model;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * The Patient class represents a single patient and extend the cass {@link User}.
 */
public class Patient extends User {
	
	private StringProperty informations = new SimpleStringProperty(this, "informations");
	private StringProperty CFPhysician = new SimpleStringProperty(this, "CFPhysician");
	private BloodPressure bloodPressure;
	private StringProperty bloodPressureString = new SimpleStringProperty(this, "bloodPressureString");

	/**
	 * Creates a new Patient with the given informations.
	 *
	 * @param CF the cf of the patient.
	 * @param email the email of the patient.
	 * @param password the password of the patient.
	 * @param name the name of the patient.
	 * @param surname the surname of the patient.
	 * @param sex the sex of the patient.
	 * @param birthdate the birthdate of the patient.
	 * @param nationality the nationality of the patient.
	 * @param street the street of the patient.
	 * @param civic_number the civic_number of the patient.
	 * @param cap the cap of the patient.
	 * @param city the city of the patient.
	 * @param phone_number the phone_number of the patient.
	 * @param informations the informations of the patient.
	 * @param CFPhysician the CFPhysician of the physician of the patient.
	 */
	public Patient(String CF, String email, String password, String name, String surname, String sex,
			LocalDate birthdate, String nationality, String street, int civic_number, int cap, String city,
			String phone_number, String informations, String CFPhysician) {
		super(CF, email, password, name, surname, sex, birthdate, nationality, street, civic_number, cap, city,
				phone_number);
		this.informations.set(informations);
		this.CFPhysician.set(CFPhysician);
	}
	
	
	/**
	 * Creates a new Patient copiyng the information of a exsisting Patienti Object.
	 *
	 * @param patient the patient.
	 */
	public Patient(Patient patient) {
		super(patient.getCF(), patient.getEmail(), patient.getPassword(), patient.getName(), patient.getSurname(), patient.getSex(), patient.getBirthdate(),patient.getNationality(), patient.getStreet(), patient.getCivicNumber(), patient.getCAP(), patient.getCity(), patient.getPhoneNumber());
		this.informations.set(patient.getInformations());
		this.CFPhysician.set(patient.getCFPhysician());
	}
	
	/**
	 * Gets the informations of the patient.
	 * @return the informations of the patient.
	 */
	public String getInformations() {
		return informations.get();
	}
	
	/**
	 * Gets the informations property of the patient.
	 * @return the informations property of the patient.
	 */
	public StringProperty informationsProperty() {
		return informations;
	}
	
	/**
	 * Sets the informations of the patient.
	 * @param informations the new informations of the patient.
	 */
	public void setInformations(String informations) {
		this.informations.set(informations);
	}
	
	/**
	 * Gets the blood pressure of the patient.
	 * @return the blood pressure of the patient.
	 */
	public BloodPressure getEnum() {
		return bloodPressure;
	}
	
	/**
	 * Sets the blood pressure of the patient.
	 * @param bloodPressure the new blood pressure of the patient.
	 */
	public void setBloodPressure(BloodPressure bloodPressure) {
		this.bloodPressure = bloodPressure;
		this.bloodPressureString.set(bloodPressure.toString());
	}
	
	/**
	 * Gets the blood pressure string of the patient.
	 * @return the blood pressure string of the patient.
	 */
	public String getBloodPressureString() {
		return bloodPressureString.get();
	}
	
	/**
	 * Gets the blood pressure string property of the patient.
	 * @return the blood pressure string property of the patient.
	 */
	public StringProperty bloodPressureStringProperty() {
		return bloodPressureString;
	}
	
	/**
	 * Gets the CF (fiscal code) of the patient's physician.
	 * @return the CF of the patient's physician.
	 */
	public String getCFPhysician() {
		return CFPhysician.get();
	}
	
	/**
	 * Gets the CF (fiscal code) property of the patient's physician.
	 * @return the CF property of the patient's physician.
	 */
	public StringProperty CFPhysicianProperty() {
		return CFPhysician;
	}
	
	/**
	 * Returns a String representation of the Patient object.
	 * @return A String representation of the Patient object.
	 */
	@Override
	public String toString() {
		return "Patient: " + getCF() + ", " + getEmail() + ", " + getName() + ", " + getSurname() + ", " + getCFPhysician();
	}

}

