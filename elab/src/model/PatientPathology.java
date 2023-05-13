package model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The PatientPathology class represents a pathology that a patient can have.
 * It contains information about the start and end date of the pathology,
 * the patient's CF (fiscal code) and the pathology's ID. 
 */
public class PatientPathology {
    private ObjectProperty<LocalDate> startDate = new SimpleObjectProperty<>(this, "startdate", null);
    private ObjectProperty<LocalDate> endDate = new SimpleObjectProperty<>(this, "enddate", null);
    private StringProperty CFPatient = new SimpleStringProperty(this, "CFPatient");
    private IntegerProperty idPathology = new SimpleIntegerProperty(this,"done");
    private StringProperty description = new SimpleStringProperty(this, "description");

    /**
     * Constructs a new PatientPathology with the given start date, end date, patient's CF, and pathology ID.
     * 
     * @param startDate the start date of the pathology
     * @param endDate the end date of the pathology
     * @param CFPatient the patient's CF (fiscal code)
     * @param idPathology the pathology's ID
     */
    public PatientPathology(LocalDate startDate, LocalDate endDate, String CFPatient, int idPathology) {
        this.startDate.set(startDate);
        this.endDate.set(endDate);
        this.CFPatient.set(CFPatient);
        this.idPathology.set(idPathology);
    }

    /**
     * Constructs a new PatientPathology with the given start date, end date, patient's CF, pathology ID, and description.
     * 
     * @param startDate the start date of the pathology
     * @param endDate the end date of the pathology
     * @param CFPatient the patient's CF (fiscal code)
     * @param idPathology the pathology's ID
     * @param description the description of the pathology
     */
    public PatientPathology(LocalDate startDate, LocalDate endDate, String CFPatient, int idPathology, String description) {
        this.startDate.set(startDate);
        this.endDate.set(endDate);
        this.CFPatient.set(CFPatient);
        this.idPathology.set(idPathology);
        this.description.set(description);
    }

    /**
     * Gets the start date of the pathology.
     * 
     * @return the start date of the pathology
     */
    public LocalDate getStartdate() {
        return startDate.get();
    }

    /**
     * Gets the start date property of the pathology.
     * 
     * @return the start date property of the pathology
     */
    public ObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }

    /**
     * Gets the end date of the pathology.
     * 
     * @return the end date of the pathology
     */
    public LocalDate getEnddate() {
        return endDate.get();
    }

    /**
     * Gets the end date property of the pathology.
     * 
     * @return the end date property of the pathology
     */
    public ObjectProperty<LocalDate> endDateProperty() {
        return endDate;
    }

    /**
     * Gets the patient's CF (fiscal code).
     * 
     * @return the patient's CF (fiscal code)
     */
    public String getCFPatient() {
        return CFPatient.get();
    }

    /**
     * Gets the patient's CF (fiscal code) property.
     * 
     * @return the patient's CF (fiscal code) property
     */
    public StringProperty cfPatientProperty() {
        return CFPatient;
    }
	
	/**
     * Gets the description.
     * 
     * @return the description.
     */
	public String getDescription() {
		return description.get();
	}
	
	/**
     * Gets the description property.
     * 
     * @return the description property
     */
	public StringProperty descriptionProperty() {
		return description;
	}
	
	/**
     * Gets the pathology's id.
     * 
     * @return the pathology's id.
     */
    public int getIdPathology(){
        return idPathology.get();
    }

    /**
     * Gets the pathology's id property.
     * 
     * @return the pathology's id property.
     */
    public IntegerProperty idPathologyProperty(){
        return idPathology;
    }
      
    /**
     * Returns a string representation of the PatientPathology instance.
     *
     * @return a string representation of the PatientPathology instance.
     */
    public String toString(){
		return "CFpatient: " + getCFPatient() + ", idPathology: " + getIdPathology() + ", startDate: " + getStartdate().toString() + ", endDate: " + (getEnddate()==null ? 99999999 : getEnddate().toString());
	}
}
