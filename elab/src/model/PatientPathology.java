package model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PatientPathology {
	private ObjectProperty<LocalDate> startDate = new SimpleObjectProperty<>(this, "startdate", null);
	private ObjectProperty<LocalDate> endDate = new SimpleObjectProperty<>(this, "enddate", null);
	private StringProperty CFPatient = new SimpleStringProperty(this, "CFPhysician");
	private IntegerProperty idPathology = new SimpleIntegerProperty(this,"done");
	private StringProperty description = new SimpleStringProperty(this, "description");
	
	public PatientPathology(LocalDate startDate, LocalDate endDate, String CFPatient, int idPathology) {
		this.startDate.set(startDate);
		this.endDate.set(endDate);
		this.CFPatient.set(CFPatient);
		this.idPathology.set(idPathology);
	}
	
	public PatientPathology(LocalDate startDate, LocalDate endDate, String CFPatient, int idPathology, String description) {
		this.startDate.set(startDate);
		this.endDate.set(endDate);
		this.CFPatient.set(CFPatient);
		this.idPathology.set(idPathology);
		this.description.set(description);
	}
	
	/*get the startDate*/
	public LocalDate getStartdate() {
		return startDate.get();
	}
	
	/*get the startDate property*/
	public ObjectProperty<LocalDate> startDateProperty() {
		return startDate;
	}
	
	/*get the endDate*/
	public LocalDate getEnddate() {
		return endDate.get();
	}
	
	/*get the endDate property*/
	public ObjectProperty<LocalDate> endDateProperty() {
		return endDate;
	}
	
	/*gets the cfpatient*/
	public String getCFPatient() {
		return CFPatient.get();
	}
	
	/*gets the cfPatientProperty*/
	public StringProperty cfPatientProperty() {
		return CFPatient;
	}
	
	/*gets the cfpatient*/
	public String getDescription() {
		return description.get();
	}
	
	/*gets the cfPatientProperty*/
	public StringProperty descriptionProperty() {
		return description;
	}
	
	/*gets the idPathology*/
    public int getIdPathology(){
        return idPathology.get();
    }

    /*gets the idPathologyProperty*/
    public IntegerProperty idPathologyProperty(){
        return idPathology;
    }
    
    public String toString(){
		return "CFpatient: " + getCFPatient() + ", idPathology: " + getIdPathology() + ", startDate: " + getStartdate().toString() + ", endDate: " + (getEnddate()==null ? 99999999 : getEnddate().toString());
	}
}
