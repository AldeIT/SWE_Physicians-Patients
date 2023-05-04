package model;

import java.time.LocalDateTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PatientPathology {
	private ObjectProperty<LocalDateTime> startDate = new SimpleObjectProperty<>(this, "date", null);
	private ObjectProperty<LocalDateTime> endDate = new SimpleObjectProperty<>(this, "date", null);
	private StringProperty CFPatient = new SimpleStringProperty(this, "CFPhysician");
	private IntegerProperty idPathology = new SimpleIntegerProperty(this,"done");
	
	public PatientPathology(LocalDateTime startDate, LocalDateTime endDate, String CFPatient, int idPathology) {
		this.startDate.set(startDate);
		this.endDate.set(endDate);
		this.CFPatient.set(CFPatient);
		this.idPathology.set(idPathology);
	}
	
	/*get the startDate*/
	public LocalDateTime getStartDate() {
		return startDate.get();
	}
	
	/*get the startDate property*/
	public ObjectProperty<LocalDateTime> startDateProperty() {
		return startDate;
	}
	
	/*get the endDate*/
	public LocalDateTime getEndDate() {
		return endDate.get();
	}
	
	/*get the endDate property*/
	public ObjectProperty<LocalDateTime> endDateProperty() {
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
	
	/*gets the idPathology*/
    public int getIdPathology(){
        return idPathology.get();
    }

    /*gets the idPathologyProperty*/
    public IntegerProperty idPathologyProperty(){
        return idPathology;
    }
    
    public String toString(){
		return "CFpatient: " + getCFPatient() + ", idPathology: " + getIdPathology() + ", startDate: " + getStartDate().toString() + ", endDate: " + (getEndDate()==null ? 99999999 : getEndDate().toString());
	}
}
