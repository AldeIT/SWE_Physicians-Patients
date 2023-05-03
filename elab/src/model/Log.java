package model;

import java.time.LocalDateTime;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Log {
	private ObjectProperty<LocalDateTime> dateTime = new SimpleObjectProperty<>(this, "date", null);
	private StringProperty informations = new SimpleStringProperty(this, "informations");
	private StringProperty CFPhysician = new SimpleStringProperty(this, "CFPhysician");
	
	public Log(LocalDateTime dateTime, String informations, String CFPhysician) {
		this.dateTime.set(dateTime);
		this.informations.set(informations);
		this.CFPhysician.set(CFPhysician);
	}
	
	/*get the datetime*/
	public LocalDateTime getDatetime() {
		return dateTime.get();
	}
	
	/*get the datetime property*/
	public ObjectProperty<LocalDateTime> dateTimeProperty() {
		return dateTime;
	}
	
	/*get the informations*/
	public String getInformations() {
		return informations.get();
	}
	
	/*gets the informationsProperty*/
	public StringProperty informationsProperty() {
		return informations;
	}
	
	/*gets the cfphysician*/
	public String getCFPhysician() {
		return CFPhysician.get();
	}
	
	/*gets the cfPhysicianProperty*/
	public StringProperty cfPhysicianProperty() {
		return CFPhysician;
	}
	
	public String toString(){
		return "date:" + getDatetime().toString() + ", info: " + getInformations() + ", physician: " + getCFPhysician();
	}
}
