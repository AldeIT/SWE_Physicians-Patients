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

/**
 * The Therapy class represents a therapy associated with a patient.
 * A therapy consists of a drug, a daily dose, a quantity, directions for use,
 * a start date, an end date, an ID for the drug, the CF of the patient and
 * the CF of the physician who prescribed the therapy, as well as information
 * about the remaining daily dose and quantity.
 */
public class Therapy {
    /**
     * Class'attributes
     */
    private ReadOnlyIntegerWrapper id = new ReadOnlyIntegerWrapper(this, "id");
    private IntegerProperty dailydose = new SimpleIntegerProperty(this, "dailydose");
    private IntegerProperty quantity = new SimpleIntegerProperty(this, "quantity");
    private StringProperty directions = new SimpleStringProperty(this, "directions");
    private ObjectProperty<LocalDate> startDate = new SimpleObjectProperty<>(this, "startDate");
    private ObjectProperty<LocalDate> endDate = new SimpleObjectProperty<>(this, "endDate");
    private IntegerProperty IDDrug = new SimpleIntegerProperty(this, "IDdrug");
    private StringProperty CFPatient = new SimpleStringProperty(this, "CFpatient");
    private StringProperty CFPhysician = new SimpleStringProperty(this, "CFphysician");
    private IntegerProperty daily_dose_remaining = new SimpleIntegerProperty(this, "daily_dose_remaining");
    private IntegerProperty quantity_remaining = new SimpleIntegerProperty(this, "quantity_dose_remaining");
    private IntegerProperty total_quantity_remaining = new SimpleIntegerProperty(this, "total_quantity_dose_remaining");
    private StringProperty drug = new SimpleStringProperty(this, "drug");

    /**
     * Creates a new Therapy object with the given parameters.
     *
     * @param id         The ID of the therapy.
     * @param daily_dose The daily dose of the drug.
     * @param quantity   The quantity of the drug.
     * @param directions The directions for use of the drug.
     * @param startDate  The start date of the therapy.
     * @param endDate    The end date of the therapy.
     * @param IDDrug     The ID of the drug.
     * @param CFPatient  The CF of the patient.
     * @param CFPhysician The CF of the physician who prescribed the therapy.
     */
    public Therapy(int id, int daily_dose, int quantity, String directions, LocalDate startDate, LocalDate endDate, int IDDrug, String CFPatient, String CFPhysician) {
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

    /**
     * Returns the ID of the therapy.
     * 
     * @return the ID of the therapy
     */
    public int getID(){
        return id.get();
    }

    /**
     * Returns the read-only property representing the ID of the therapy.
     * 
     * @return the read-only property representing the ID of the therapy
     */
    public ReadOnlyIntegerProperty idProperty(){
        return id.getReadOnlyProperty();
    }

    /**
     * Returns the daily dose of the therapy.
     * 
     * @return the daily dose the therapy
     */
    public int getDailydose(){
        return dailydose.get();
    }
    
    /**
     * Set the daily dose of the therapy.
     * 
     * @param daily_dose the daily dose of the therapy
     */
    public void setDailydose(int daily_dose) {
    	this.dailydose.set(daily_dose);
    }

    /**
     * Returns the property representing the daily dose of the therapy.
     * 
     * @return the property representing the daily dose of the therapy
     */
    public IntegerProperty dailydoseProperty(){
        return dailydose;
    }

    /**
     * Returns the quantity of the therapy.
     * 
     * @return the quantity dose the therapy
     */
    public int getQuantity(){
        return quantity.get();
    }

	/**
     * Set the quantity of the therapy.
     * 
     * @param quantity the quantity of the therapy
     */
    public void setQuantity(int quantity) {
    	this.quantity.set(quantity);
    }
    
    /**
     * Returns the property representing the quantity of the therapy.
     * 
     * @return the property representing the quantity of the therapy
     */
    public IntegerProperty quantityProperty(){
        return quantity;
    }

    /**
     * Returns the directions of the therapy.
     * 
     * @return the directions dose the therapy
     */
    public String getDirections(){
        return directions.get();
    }
	
	/**
     * Set the directions for the therapy.
     * 
     * @param directions the directions for the therapy
     */
    public void setDirections(String directions) {
    	this.directions.set(directions);
    }
    
    /**
     * Returns the property representing the directions for the therapy.
     * 
     * @return the property representing the directions for the therapy
     */
    public StringProperty directionsProperty(){
        return directions;
    }

	/**
     * Returns the start date of the therapy.
     * 
     * @return the start date dose the therapy
     */
    public LocalDate getStartDate() {
    	return startDate.get();
    }
    
    /**
     * Set the starting date of the therapy.
     * 
     * @param startDate the starting date of the therapy
     */
    public void setStartDate(LocalDate startDate) {
    	this.startDate.set(startDate);
    }
    
    /**
     * Returns the property representing the starting day of the therapy.
     * 
     * @return the property representing the starting day of the therapy
     */
    public ObjectProperty<LocalDate> startDateProperty(){
    	return startDate;
    }
    
    /**
     * Returns the end date of the therapy.
     * 
     * @return the end date dose the therapy
     */
    public LocalDate getEndDate() {
    	return endDate.get();
    }
    
    /**
     * Set the ending date of the therapy.
     * 
     * @param endDate the ending date of the therapy
     */
    public void setEndDate(LocalDate endDate) {
    	this.endDate.set(endDate);
    }
    
    /**
     * Returns the property representing the ending day of the therapy.
     * 
     * @return the property representing the ending day of the therapy
     */
    public ObjectProperty<LocalDate> endDateProperty(){
    	return endDate;
    }

    /**
     * Returns the drug's id of the therapy.
     * 
     * @return the drug's id dose the therapy
     */
    public int getIDDrug(){
        return IDDrug.get();
    }
	
	/**
     * Set the drug's id of the therapy.
     * 
     * @param IDDrug the drug's id of the therapy
     */
    public void setIDDrug(int IDDrug) {
    	this.IDDrug.set(IDDrug);
    }
    
    /**
     * Returns the property representing the drug's id of the therapy.
     * 
     * @return the property representing the drug's id of the therapy
     */
    public IntegerProperty IDDrugProperty(){
        return IDDrug;
    }

    /**
     * Returns the cf of the patient that will follow the therapy.
     * 
     * @return the cf of the patient that will follow the therapy
     */
    public String getCFPatient(){
        return CFPatient.get();
    }

    /**
     * Returns the property representing the cf of the patient who will follow the therapy.
     * 
     * @return the property representing the cf of the patient who will follow the therapy.
     */
    public StringProperty CFPatientProperty(){
        return CFPatient;
    }

    /**
     * Returns the cf of the physician that created the therapy.
     * 
     * @return the cf of the physician that created the therapy
     */
    public String getCFPhysician(){
        return CFPhysician.get();
    }

    /**
     * Returns the property representing the cf of the physician who inserted the therapy.
     * 
     * @return the property representing the cf of the physician who inserted the therapy.
     */
    public StringProperty CFPhysicianProperty(){
        return CFPhysician;
    }
    
    /**
     * Returns the remaining daily dose of the therapy for the day.
     * 
     * @return the remaining daily dose of the therapy for the day
     */
    public int getDailyDoseRemaining(){
        return daily_dose_remaining.get();
    }
    
    /**
     * Set the the daily dose remaining of the therapy for the day.
     * 
     * @param daily_dose_remaining the daily dose remaining of the therapy for the day.
     */
    public void setDailyDoseRemaining(int daily_dose_remaining) {
    	this.daily_dose_remaining.set(daily_dose_remaining);
    }

    /**
     * Returns the property representing the daily dose remaining of the therapy for the day.
     * 
     * @return the property representing the daily dose remaining of the therapy for the day.
     */
    public IntegerProperty dailyDoseRemainingProperty(){
        return daily_dose_remaining;
    }
    
    /**
     * Returns the remaining quantity of the therapy for the day.
     * 
     * @return the remaining quantity of the therapy for the day.
     */
    public int getQuantityRemaining(){
        return quantity_remaining.get();
    }
    
    /**
     * Set the the quantity remaining of the therapy for the day.
     * 
     * @param quantity_remaining the quantity remaining of the therapy for the day.
     */
    public void setQuantityRemaining(int quantity_remaining) {
    	this.quantity_remaining.set(quantity_remaining);
    }

    /**
     * Returns the property representing the quantity remaining of the therapy for the day.
     * 
     * @return the property representing the quantity remaining of the therapy for the day.
     */
    public IntegerProperty quantityRemainingProperty(){
        return quantity_remaining;
    }
    
    /**
     * Returns the total remaining quantity of the therapy for the day.
     * 
     * @return the total remaining quantity of the therapy for the day.
     */
    public int getTotalQuantityRemaining(){
        return total_quantity_remaining.get();
    }
    
    /**
     * Set the the total quantity remaining of the therapy for the day.
     * 
     * @param total_quantity_remaining the total quantity remaining of the therapy for the day.
     */
    public void setTotalQuantityRemaining(int total_quantity_remaining) {
    	this.total_quantity_remaining.set(total_quantity_remaining);
    }

    /**
     * Returns the property representing the total quantity remaining of the therapy for the day.
     * 
     * @return the property representing the total quantity remaining of the therapy for the day.
     */
    public IntegerProperty totalQuantityRemainingProperty(){
        return total_quantity_remaining;
    }
       
    /**
     * Returns the drug of the therapy.
     * 
     * @return the drug of the therapy.
     */
    public String getDrug(){
        return drug.get();
    }

    /**
     * Returns the property representing the drug of the therapy.
     * 
     * @return the property representing the drug of the therapy.
     */
    public StringProperty drugProperty(){
        return drug;
    }
    
    /**
     * Set the drug of the therapy.
     * 
     * @param drug the drug of the therapy
     */
    public void setDrug(String drug){
        this.drug.set(drug);
    }

	/**
     * Returns a string representation of the Therapy.
     * 
     * @return a string representation of the Therapy
     */
    public String toString(){
    	//String out = "Therapy: " + getID() + ", Directions: " + getDirections() + ", Drug: " + getDrug(); 	
        return "Therapy: " + getID() + ", Total Pills Remaining : " + getTotalQuantityRemaining() + ", Daily Dose Remaining: " + getDailyDoseRemaining() + ", Quantity: " + getQuantityRemaining() + ", Directions: " + getDirections() + ", Drug: " + getDrug();
    }
} 
