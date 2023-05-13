package model;

import java.time.LocalDateTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Measurement class represents a single measurement of blood pressure taken from a patient.
 */
public class Measurement {
    private final ReadOnlyIntegerWrapper id = new ReadOnlyIntegerWrapper(this, "id");
    private IntegerProperty sbp = new SimpleIntegerProperty(this, "sbp");
    private IntegerProperty dbp = new SimpleIntegerProperty(this, "dbp");
    private ObjectProperty<LocalDateTime> date = new SimpleObjectProperty<>(this, "datetime", null);
    private StringProperty informations = new SimpleStringProperty(this, "informations");
    private StringProperty CFpatient = new SimpleStringProperty(this, "CFpatient");

    /**
     * Creates a new Measurement with the given id, systolic blood pressure, diastolic blood pressure, date and time, 
     * additional information, and the patient's CF code.
     * 
     * @param id the unique identifier for this measurement
     * @param sbp the systolic blood pressure reading for this measurement
     * @param dbp the diastolic blood pressure reading for this measurement
     * @param datetime the date and time when the measurement was taken
     * @param informations additional information about the measurement
     * @param CFpatient the CF code of the patient who took the measurement
     */
    public Measurement(int id, int sbp, int dbp, LocalDateTime datetime, String informations, String CFpatient){
        this.id.set(id);
        this.sbp.set(sbp);
        this.dbp.set(dbp);
        this.date.set(datetime);
        this.informations.set(informations);
        this.CFpatient.set(CFpatient);
    }

    /**
     * Returns the unique identifier for this measurement.
     * 
     * @return the unique identifier for this measurement
     */
    public final int getId() {
        return id.get();
    }

    /**
     * Returns the read-only property for the unique identifier of this measurement.
     * 
     * @return the read-only property for the unique identifier of this measurement
     */
    public final ReadOnlyIntegerProperty idProperty() {
        return id.getReadOnlyProperty();
    }

    /**
     * Returns the systolic blood pressure reading for this measurement.
     * 
     * @return the systolic blood pressure reading for this measurement
     */
    public int getSbp(){
        return sbp.get();
    }

    /**
     * Returns the property for the systolic blood pressure reading for this measurement.
     * 
     * @return the property for the systolic blood pressure reading for this measurement
     */
    public IntegerProperty sbpProperty(){
        return sbp;
    }

    /**
     * Returns the diastolic blood pressure reading for this measurement.
     * 
     * @return the diastolic blood pressure reading for this measurement
     */
    public int getDbp(){
        return dbp.get();
    }

    /**
     * Returns the property for the diastolic blood pressure reading for this measurement.
     * 
     * @return the property for the diastolic blood pressure reading for this measurement
     */
    public IntegerProperty dbpProperty(){
        return dbp;
    }

    /**
     * Returns the date and time when the measurement was taken.
     * 
     * @return the date and time when the measurement was taken
     */
    public LocalDateTime getDateTime(){
        return date.get();
    }

    /**
     * Returns the property for the date and time when the measurement was taken.
     * 
     * @return the property for the date and time when the measurement was taken
     */
    public ObjectProperty<LocalDateTime> dateProperty(){
        return date;
    }
    
     /**
     * Returns the property for the date and time when the measurement was taken.
     * 
     * @return the property for the date and time when the measurement was taken
     */
	public String getInformations() {
		return informations.get();
	}
	
	/**
     * Returns the property for the date and time when the measurement was taken.
     * 
     * @return the property for the date and time when the measurement was taken
     */
	public StringProperty informationsProperty() {
		return informations;
	}
	
    /**
	 * Returns the CF (codice fiscale) of the patient that take the measurement.
	 *
	 * @return The CF (codice fiscale) of the patient that take the measurement.
	 */
	public String getCFPatient() {
		return CFpatient.get();
	}
	
	/**
	 * Returns the property representing the CF (codice fiscale) of the patient that take the measurement.
	 *
	 * @return The property representing the CF (codice fiscale) of the patient that take the measurement.
	 */
	public StringProperty CFPatientProperty() {
		return CFpatient;
	}
	
	/**
	 * Returns a String representation of the Measurement object.
	 *
	 * @return A String representation of the Measurement object.
	 */
    @Override
	public String toString() {
		return "Measurement: " + getId() + ", " + getSbp() + "/" + getDbp() + ", " + getDateTime() + ", " + getCFPatient() + ", " + getInformations();
	}

}
