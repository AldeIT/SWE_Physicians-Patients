package model;

import java.time.LocalDate;

/**
 * A class representing a physician, which is a type of {@link User}.
 */
public class Physician extends User{
	
	/**
	 * Constructs a new Physician with the given information.
	 * 
	 * @param CF the fiscal code of the physician
	 * @param email the email address of the physician
	 * @param password the password of the physician's account
	 * @param name the name of the physician
	 * @param surname the surname of the physician
	 * @param sex the sex of the physician
	 * @param birthdate the birth date of the physician
	 * @param nationality the nationality of the physician
	 * @param street the street where the physician lives
	 * @param civic_number the civic number of the physician's address
	 * @param cap the postal code of the physician's address
	 * @param city the city of the physician's address
	 * @param phone_number the phone number of the physician
	 */
	public Physician(String CF, String email, String password, String name, String surname, String sex,
			LocalDate birthdate, String nationality, String street, int civic_number, int cap, String city,
			String phone_number) {
		super(CF, email, password, name, surname, sex, birthdate, nationality, street, civic_number, cap, city, phone_number);
	}
	
	/**
	 * Constructs a new Physician by copying the information of an existing Physician.
	 * 
	 * @param physician the physician to copy
	 */
	public Physician(Physician physician) {
		super(physician.getCF(), physician.getEmail(), physician.getPassword(), physician.getName(), physician.getSurname(), physician.getSex(), physician.getBirthdate(), physician.getNationality(), physician.getStreet(), physician.getCivicNumber(), physician.getCAP(), physician.getCity(), physician.getPhoneNumber());
	}

	/**
	 * Returns a string representation of the Physician, including the fiscal code,
	 * email address, name, and surname.
	 * 
	 * @return a string representation of the Physician
	 */
	public String toString() {
		return "Physician: " + getCF() + ", " + getEmail() + ", " + getName() + ", " + getSurname();
	}
}

