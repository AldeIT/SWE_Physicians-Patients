package model;

import java.time.LocalDateTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;


/**
 * This class represents a drug intake that a patient takes for a specific therapy.
 */
public class DrugIntake {
    private final ReadOnlyIntegerWrapper id = new ReadOnlyIntegerWrapper(this, "id");
	private ObjectProperty<LocalDateTime> datetime = new SimpleObjectProperty<>(this, "datetime", null);
	private IntegerProperty quantity = new SimpleIntegerProperty(this, "quantity");
	private IntegerProperty IDtherapy = new SimpleIntegerProperty(this, "IDtherapy");
	
	/**
	 * Constructs a DrugIntake with the given id, datetime, quantity, and IDtherapy.
	 * @param id The id of the drug intake.
	 * @param datetime The date and time when the drug was taken.
	 * @param quantity The quantity of the drug that was taken.
	 * @param IDtherapy The ID of the therapy for which the drug was taken.
	 */
	public DrugIntake(int id, LocalDateTime datetime, int quantity, int IDtherapy){
	    this.id.set(id);
	    this.datetime.set(datetime);
	    this.quantity.set(quantity);
	    this.IDtherapy.set(IDtherapy);
	}
	
	/**
	 * Returns the id of the drug intake.
	 * @return The id of the drug intake.
	 */
	public final int getId() {
	    return id.get();
	}
	
	/**
	 * Returns the read-only integer property of the drug intake id.
	 * @return The read-only integer property of the drug intake id.
	 */
	public final ReadOnlyIntegerProperty idProperty() {
	    return id.getReadOnlyProperty();
	}
	
	/**
	 * Returns the date and time when the drug was taken.
	 * @return The date and time when the drug was taken.
	 */
	public LocalDateTime getDateTime(){
	    return datetime.get();
	}
	
	/**
	 * Returns the object property of the date and time when the drug was taken.
	 * @return The object property of the date and time when the drug was taken.
	 */
	public ObjectProperty<LocalDateTime> datetimeProperty(){
	    return datetime;
	}
	
	/**
	 * Returns the quantity of the drug that was taken.
	 * @return The quantity of the drug that was taken.
	 */
	public int getQuantity(){
	    return quantity.get();
	}
	
	/**
	 * Returns the integer property of the quantity of the drug that was taken.
	 * @return The integer property of the quantity of the drug that was taken.
	 */
	public IntegerProperty quantityProperty(){
	    return quantity;
	}
	
	/**
	 * Returns the ID of the therapy for which the drug was taken.
	 * @return The ID of the therapy for which the drug was taken.
	 */
	public int getIDTherapy(){
	    return IDtherapy.get();
	}
	
	/**
	 * Returns the integer property of the ID of the therapy for which the drug was taken.
	 * @return The integer property of the ID of the therapy for which the drug was taken.
	 */
	public IntegerProperty IDTherapyProperty(){
	    return IDtherapy;
	}
	
	/**
	 * Returns a string representation of the drug intake.
	 * @return A string representation of the drug intake.
	 */
	@Override
	public String toString() {
	    return "DrugIntake: " + getId() + ", " + getDateTime() + ", " + getIDTherapy();
	}
}
