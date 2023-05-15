package model;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A class representing a pathology, identified by an integer ID and a description.
 */
public class Pathology {
    private ReadOnlyIntegerWrapper id = new ReadOnlyIntegerWrapper(this,"id");
    private StringProperty description = new SimpleStringProperty(this,"description");

    /**
     * Constructs a new Pathology object with the given ID and description.
     * @param id the ID of the pathology
     * @param description the description of the pathology
     */
    public Pathology(int id, String description){
        this.id.set(id);
        this.description.set(description);
    }

    /**
     * Returns the ID of the pathology.
     * @return the ID of the pathology
     */
    public int getID(){
        return id.get();
    }

    /**
     * Returns the read-only integer property representing the ID of the pathology.
     * @return the read-only integer property representing the ID of the pathology
     */
    public ReadOnlyIntegerProperty idProperty(){
        return id.getReadOnlyProperty();
    }

    /**
     * Returns the description of the pathology.
     * @return the description of the pathology
     */
    public String getDescription(){
        return description.get();
    }

    /**
     * Returns the string property representing the description of the pathology.
     * @return the string property representing the description of the pathology
     */
    public StringProperty descriptionProperty(){
        return description;
    }

    /**
     * Returns a string representation of the pathology object.
     * @return a string representation of the pathology object
     */
    public String toString(){
        return "Pathology: " + getID() + ", " + getDescription();
    }
}

