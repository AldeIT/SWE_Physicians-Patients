package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a drug in the system.
 */
public class Drug {
    
    private final SimpleIntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty name = new SimpleStringProperty(this, "name");
    private StringProperty description = new SimpleStringProperty(this, "description");

    /**
     * Constructs a new drug with the specified id, name, and description.
     *
     * @param id the drug id.
     * @param name the drug name.
     * @param description the drug description.
     */
    public Drug(int id, String name, String description) {
        this.id.set(id);
        this.name.set(name);
        this.description.set(description);
    }

    /**
     * Returns the drug id.
     *
     * @return the drug id.
     */
    public final int getId() {
        return id.get();
    }

    /**
     * Returns a read-only integer property representing the drug id.
     *
     * @return a read-only integer property representing the drug id.
     */
    public final SimpleIntegerProperty idProperty() {
        return id;
    }

    /**
     * Returns the drug name.
     *
     * @return the drug name.
     */
    public String getName() {
        return name.get();
    }

    /**
     * Returns a string property representing the drug name.
     *
     * @return a string property representing the drug name.
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Returns the drug description.
     *
     * @return the drug description.
     */
    public String getDescription() {
        return description.get();
    }

    /**
     * Returns a string property representing the drug description.
     *
     * @return a string property representing the drug description.
     */
    public StringProperty descriptionProperty() {
        return description;
    }

    /**
     * Returns a string representation of the drug.
     *
     * @return a string representation of the drug.
     */
    @Override
    public String toString() {
        return getName();
    }

    /**
     * Determines whether this drug is equal to another object.
     *
     * @param other the object to compare to this drug.
     * @return true if the object is a drug with the same name and description as this drug, false otherwise.
     */
    public boolean equals(Object other) {
        if (!(other instanceof Drug)) {
            return false;
        }
        return this.getName().equals(((Drug) other).getName()) && this.getDescription().equals(((Drug) other).getDescription());
    }
}
