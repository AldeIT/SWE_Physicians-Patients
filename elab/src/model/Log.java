package model;

import java.time.LocalDateTime;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This class represents a log containing information related to a medical operation or procedure, such as a medication administration.
 * It contains the date and time the log was created, details about the operation or procedure, and the CF (codice fiscale) of the physician that performed it.
 */
public class Log {
	/**
	 * The date of the log.
	 */
	private ObjectProperty<LocalDateTime> dateTime = new SimpleObjectProperty<>(this, "date", null);
	/**
	 * Details about the medical operation or procedure.
	 */
	private StringProperty informations = new SimpleStringProperty(this, "informations");
	
	/**
	 * The CF (codice fiscale) of the physician that performed the medical operation or procedure.
	 */
	private StringProperty CFPhysician = new SimpleStringProperty(this, "CFPhysician");
	
	/**
	 * Constructor for the Log class.
	 * @param dateTime The date and time the log was created.
	 * @param informations Details about the medical operation or procedure.
	 * @param CFPhysician The CF (codice fiscale) of the physician that performed the medical operation or procedure.
	 */
	public Log(LocalDateTime dateTime, String informations, String CFPhysician) {
	    this.dateTime.set(dateTime);
	    this.informations.set(informations);
	    this.CFPhysician.set(CFPhysician);
	}
	
	/**
	 * Returns the date and time the log was created.
	 * @return The date and time the log was created.
	 */
	public LocalDateTime getDatetime() {
	    return dateTime.get();
	}
	
	/**
	 * Returns the property representing the date and time the log was created.
	 * @return The property representing the date and time the log was created.
	 */
	public ObjectProperty<LocalDateTime> dateTimeProperty() {
	    return dateTime;
	}
	
	/**
	 * Returns details about the medical operation or procedure.
	 * @return Details about the medical operation or procedure.
	 */
	public String getInformations() {
	    return informations.get();
	}
	
	/**
	 * Returns the property representing the details about the medical operation or procedure.
	 * @return The property representing the details about the medical operation or procedure.
	 */
	public StringProperty informationsProperty() {
	    return informations;
	}
	
	/**
	 * Returns the CF (codice fiscale) of the physician that performed the medical operation or procedure.
	 * @return The CF (codice fiscale) of the physician that performed the medical operation or procedure.
	 */
	public String getCFPhysician() {
	    return CFPhysician.get();
	}
	
	/**
	 * Returns the property representing the CF (codice fiscale) of the physician that performed the medical operation or procedure.
	 * @return The property representing the CF (codice fiscale) of the physician that performed the medical operation or procedure.
	 */
	public StringProperty cfPhysicianProperty() {
	    return CFPhysician;
	}
	
	/**
	 * Returns a String representation of the Log object.
	 * @return A String representation of the Log object.
	 */
	public String toString(){
	    return "date:" + getDatetime().toString() + ", info: " + getInformations() + ", physician: " + getCFPhysician();
	}

}
