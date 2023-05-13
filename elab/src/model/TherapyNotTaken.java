package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * The TherapyNotTaken class represents a therapy that has not been taken by a patient.
 * It contains the patient's name, surname, the drug name, remaining amount of the drug, and their email address.
 */
public class TherapyNotTaken {
	
	private StringProperty name = new SimpleStringProperty(this, "name");
	private StringProperty surname = new SimpleStringProperty(this, "surname");
    private StringProperty drug = new SimpleStringProperty(this,"drug");
    private IntegerProperty remaining = new SimpleIntegerProperty(this,"remaining");
    private StringProperty email = new SimpleStringProperty(this,"email");

	/**
     * Creates a new TherapyNotTaken object with the specified parameters.
     *
     * @param name the patient's name
     * @param surname the patient's surname
     * @param drug the name of the drug that was not taken
     * @param remaining the remaining amount of the drug
     * @param email the patient's email address
     */
    public TherapyNotTaken(String name, String surname, String drug, int remaining, String email){
        this.name.set(name);
        this.surname.set(surname);
        this.drug.set(drug);
        this.remaining.set(remaining);
        this.email.set(email);
    }

    /**
     * Returns the name property of the patient.
     * @return the name property
     */
	public StringProperty nameProperty() {
		return name;
	}

    /**
     * Returns the patient's name.
     * @return the patient's name
     */
	public String getName() {
		return name.get();
	}

    /**
     * Returns the surname property of the patient.
     * @return the surname property
     */
	public StringProperty surnameProperty() {
		return surname;
	}

    /**
     * Returns the patient's surname.
     * @return the patient's surname
     */
	public String getSurname() {
		return surname.get();
	}

    /**
     * Returns the drug property of the therapy.
     * @return the drug property
     */
	public StringProperty drugProperty() {
		return drug;
	}

    /**
     * Returns the name of the drug.
     * @return the name of the drug
     */
	public String getDrug() {
		return drug.get();
	}

    /**
     * Returns the remaining property of the therapy.
     * @return the remaining property
     */
	public IntegerProperty remainingProperty() {
		return remaining;
	}

    /**
     * Returns the remaining amount of the drug.
     * @return the remaining amount of the drug
     */
    public int getRemaining(){
        return remaining.get();
    }

    /**
     * Returns the email property of the patient.
     * @return the email property
     */
	public StringProperty emailProperty() {
		return email;
	}

    /**
     * Returns the patient's email address.
     * @return the patient's email address
     */
	public String getEmail() {
		return email.get();
	}

	/**
     * Returns a string representation of the TherapyNotTaken object.
     * @return a string representation of the TherapyNotTaken object
	 */
    public String toString(){
        return "Therapy Not Taken: " + getName() + ", " + getSurname() + ", " + getDrug() + ", " + getRemaining() + ", " + getEmail();
    }
}
