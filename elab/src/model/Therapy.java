package model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Therapy {
    private ReadOnlyIntegerWrapper id = new ReadOnlyIntegerWrapper(this,"id");
    private IntegerProperty dailydose = new SimpleIntegerProperty(this,"dailydose");
    private IntegerProperty quantity = new SimpleIntegerProperty(this,"quantity");
    private StringProperty directions = new SimpleStringProperty(this,"directions");
    private ObjectProperty<LocalDate> startDate = new SimpleObjectProperty<>(this, "startDate");
    private ObjectProperty<LocalDate> endDate = new SimpleObjectProperty<>(this, "endDate");
    private IntegerProperty IDDrug = new SimpleIntegerProperty(this,"IDdrug");
    private StringProperty CFPatient = new SimpleStringProperty(this,"CFpatient");
    private StringProperty CFPhysician = new SimpleStringProperty(this,"CFphysician");
    private IntegerProperty daily_dose_remaining = new SimpleIntegerProperty(this, "daily_dose_remaining");
    private IntegerProperty quantity_remaining = new SimpleIntegerProperty(this, "quantity_dose_remaining");
    private IntegerProperty total_quantity_remaining = new SimpleIntegerProperty(this, "total_quantity_dose_remaining");
    private StringProperty drug = new SimpleStringProperty(this,"drug");
    
    public Therapy(int id, int daily_dose, int quantity, String directions, LocalDate startDate, LocalDate endDate, int IDDrug, String CFPatient, String CFPhysician){
        this.id.set(id);
        this.dailydose.set(daily_dose);
        this.quantity.set(quantity);
        this.directions.set(directions);
        this.startDate.set(startDate);
        this.endDate.set(endDate);
        this.IDDrug.set(IDDrug);
        this.CFPatient.set(CFPatient);
        this.CFPhysician.set(CFPhysician);
        this.daily_dose_remaining.set(daily_dose);
        this.quantity_remaining.set(quantity);
        this.drug.set("drug");
    }

    /*gets the id*/
    public int getID(){
        return id.get();
    }

    /*gets the idProperty*/
    public ReadOnlyIntegerProperty idProperty(){
        return id.getReadOnlyProperty();
    }

    /*gets the dailyDose*/
    public int getDailydose(){
        return dailydose.get();
    }
    
    public void setDailydose(int daily_dose) {
    	this.dailydose.set(daily_dose);
    }

    /*gets the dailyDoseProperty*/
    public IntegerProperty dailydoseProperty(){
        return dailydose;
    }

    /*gets the quantity*/
    public int getQuantity(){
        return quantity.get();
    }

    public void setQuantity(int quantity) {
    	this.quantity.set(quantity);
    }
    
    /*gets the quantityProperty*/
    public IntegerProperty quantityProperty(){
        return quantity;
    }

    /*gets the directions*/
    public String getDirections(){
        return directions.get();
    }

    public void setDirections(String directions) {
    	this.directions.set(directions);
    }
    
    /*gets the directionsProperty*/
    public StringProperty directionsProperty(){
        return directions;
    }

    public LocalDate getStartDate() {
    	return startDate.get();
    }
    
    public void setStartDate(LocalDate startDate) {
    	this.startDate.set(startDate);
    }
    
    public ObjectProperty<LocalDate> startDateProperty(){
    	return startDate;
    }
    
    public LocalDate getEndDate() {
    	return endDate.get();
    }
    
    public void setEndDate(LocalDate endDate) {
    	this.endDate.set(endDate);
    }
    
    public ObjectProperty<LocalDate> endDateProperty(){
    	return endDate;
    }

    /*gets the IDDrug*/
    public int getIDDrug(){
        return IDDrug.get();
    }

    public void setIDDrug(int IDDrug) {
    	this.IDDrug.set(IDDrug);
    }
    
    /*gets the IDDrugProperty*/
    public IntegerProperty IDDrugProperty(){
        return IDDrug;
    }

    /*gets the CFPatient*/
    public String getCFPatient(){
        return CFPatient.get();
    }

    /*gets the CFPatientProperty*/
    public StringProperty CFPatientProperty(){
        return CFPatient;
    }

    /*gets the CFPhysician*/
    public String getCFPhysician(){
        return CFPhysician.get();
    }

    /*gets the CFPhysicianProperty*/
    public StringProperty CFPhysicianProperty(){
        return CFPhysician;
    }
    
    /*gets the dailyDose*/
    public int getDailyDoseRemaining(){
        return daily_dose_remaining.get();
    }
    
    public void setDailyDoseRemaining(int daily_dose_remaining) {
    	this.daily_dose_remaining.set(daily_dose_remaining);
    }

    /*gets the dailyDoseProperty*/
    public IntegerProperty dailyDoseRemainingProperty(){
        return daily_dose_remaining;
    }
    
    /*gets the dailyDose*/
    public int getQuantityRemaining(){
        return quantity_remaining.get();
    }
    
    public void setQuantityRemaining(int quantity_remaining) {
    	this.quantity_remaining.set(quantity_remaining);
    }

    /*gets the dailyDoseProperty*/
    public IntegerProperty quantityRemainingProperty(){
        return quantity_remaining;
    }
    
    /*gets the dailyDose*/
    public int getTotalQuantityRemaining(){
        return total_quantity_remaining.get();
    }
    
    public void setTotalQuantityRemaining(int total_quantity_remaining) {
    	this.total_quantity_remaining.set(total_quantity_remaining);
    }

    /*gets the dailyDoseProperty*/
    public IntegerProperty totalQuantityRemainingProperty(){
        return total_quantity_remaining;
    }
    
    
    /*gets the CFPatient*/
    public String getDrug(){
        return drug.get();
    }

    /*gets the CFPatientProperty*/
    public StringProperty drugProperty(){
        return drug;
    }
    
    public void setDrug(String drug){
        this.drug.set(drug);
    }

    public String toString(){
    	String out = "Therapy: " + getID() + ", Directions: " + getDirections() + ", Drug: " + getDrug();
    	
    	
        return "Therapy: " + getID() + ", Total Pills Remaining : " + getTotalQuantityRemaining() + ", Daily Dose Remaining: " + getDailyDoseRemaining() + ", Quantity: " + getQuantityRemaining() + ", Directions: " + getDirections() + ", Drug: " + getDrug();
    }
} 
