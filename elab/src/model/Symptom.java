package model;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a symptom that a patient may experience.
 */
public class Symptom {
    
    private ReadOnlyIntegerWrapper id = new ReadOnlyIntegerWrapper(this, "id");
    private StringProperty description = new SimpleStringProperty(this, "description");
    
    /**
     * Creates a new Symptom object with the given ID and description.
     * 
     * @param id the ID of the symptom
     * @param description the description of the symptom
     */
    public Symptom(int id, String description) {
        this.id.set(id);
        this.description.set(description);
    }
    
    /**
     * Returns the ID of the symptom.
     * 
     * @return the ID of the symptom
     */
    public int getID() {
        return id.get();
    }
    
    /**
     * Returns the read-only property representing the ID of the symptom.
     * 
     * @return the read-only property representing the ID of the symptom
     */
    public ReadOnlyIntegerProperty idProperty() {
        return id.getReadOnlyProperty();
    }
    
    /**
     * Returns the description of the symptom.
     * 
     * @return the description of the symptom
     */
    public String getDescription() {
        return description.get();
    }
    
    /**
     * Returns the property representing the description of the symptom.
     * 
     * @return the property representing the description of the symptom
     */
    public StringProperty descriptionProperty() {
        return description;
    }
    
    /**
     * Returns a string representation of the symptom.
     * 
     * @return a string representation of the symptom
     */
    public String toString() {
        return getDescription();
    }
}

