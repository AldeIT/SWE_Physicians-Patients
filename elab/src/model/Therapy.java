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
    private IntegerProperty daily_dose = new SimpleIntegerProperty(this,"dailydose");
    private IntegerProperty quantity = new SimpleIntegerProperty(this,"quantity");
    private StringProperty directions = new SimpleStringProperty(this,"directions");
    private ObjectProperty<LocalDate> startDate = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> endDate = new SimpleObjectProperty<>();
    private IntegerProperty IDDrug = new SimpleIntegerProperty(this,"IDdrug");
    private StringProperty CFPatient = new SimpleStringProperty(this,"CFpatient");
    private StringProperty CFPhysician = new SimpleStringProperty(this,"CFphysician");
    private IntegerProperty daily_dose_remaining = new SimpleIntegerProperty(this, "daily_dose_remaining");
    private IntegerProperty quantity_remaining = new SimpleIntegerProperty(this, "daily_dose_remaining");
    private StringProperty drugName = new SimpleStringProperty(this,"drug");
    
    public Therapy(int id, int daily_dose, int quantity, String directions, LocalDate startDate, LocalDate endDate, int IDDrug, String CFPatient, String CFPhysician){
        this.id.set(id);
        this.daily_dose.set(daily_dose);
        this.quantity.set(quantity);
        this.directions.set(directions);
        this.startDate.set(startDate);
        this.endDate.set(endDate);
        this.IDDrug.set(IDDrug);
        this.CFPatient.set(CFPatient);
        this.CFPhysician.set(CFPhysician);
        this.daily_dose_remaining.set(daily_dose);
        this.quantity_remaining.set(quantity);
        this.drugName.set("drug");
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
    public int getDailyDose(){
        return daily_dose.get();
    }

    /*gets the dailyDoseProperty*/
    public IntegerProperty dailyDoseProperty(){
        return daily_dose;
    }

    /*gets the quantity*/
    public int getQuantity(){
        return quantity.get();
    }

    /*gets the quantityProperty*/
    public IntegerProperty quantityProperty(){
        return quantity;
    }

    /*gets the directions*/
    public String getDirections(){
        return directions.get();
    }

    /*gets the directionsProperty*/
    public StringProperty directionsProperty(){
        return directions;
    }

    public LocalDate getStartDate() {
    	return startDate.get();
    }
    
    public ObjectProperty<LocalDate> startDateProperty(){
    	return startDate;
    }
    
    public LocalDate getEndDate() {
    	return endDate.get();
    }
    
    public ObjectProperty<LocalDate> endDateProperty(){
    	return endDate;
    }

    /*gets the IDDrug*/
    public int getIDDrug(){
        return IDDrug.get();
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
    
    /*gets the CFPatient*/
    public String getDrugName(){
        return drugName.get();
    }

    /*gets the CFPatientProperty*/
    public StringProperty drugNameProperty(){
        return drugName;
    }
    
    public void setDrugName(String drug){
        this.drugName.set(drug);
    }

    public String toString(){
        return "Therapy: " + getID() + ", Daily Dose Remaining: " + getDailyDoseRemaining() + ", Quantity: " + getQuantityRemaining() + ", Directions: " + getDirections() + ", Drug: " + getDrugName();
    }
} 
