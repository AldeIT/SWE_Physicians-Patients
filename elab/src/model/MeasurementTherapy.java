package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents a measurement associated with a therapy in a medical record.
 */
public class MeasurementTherapy {
    private IntegerProperty IDMeasurement = new SimpleIntegerProperty(this, "IDMeasurement");
    private IntegerProperty IDTherapy = new SimpleIntegerProperty(this, "IDTherapy");

    /**
     * Constructs a new MeasurementTherapy instance with the specified measurement ID and therapy ID.
     * @param IDMeasurement the ID of the measurement
     * @param IDTherapy the ID of the therapy
     */
    public MeasurementTherapy(int IDMeasurement, int IDTherapy) {
        this.IDMeasurement.set(IDMeasurement);
        this.IDTherapy.set(IDTherapy);
    }

    /**
     * Returns the ID of the measurement.
     * @return the ID of the measurement
     */
    public int getIDMeasurement() {
        return IDMeasurement.get();
    }

    /**
     * Returns the IntegerProperty object representing the ID of the measurement.
     * @return the IntegerProperty object representing the ID of the measurement
     */
    public IntegerProperty IDMeasurementProperty() {
        return IDMeasurement;
    }

    /**
     * Returns the ID of the therapy.
     * @return the ID of the therapy
     */
    public int getIDTherapy() {
        return IDTherapy.get();
    }

    /**
     * Returns the IntegerProperty object representing the ID of the therapy.
     * @return the IntegerProperty object representing the ID of the therapy
     */
    public IntegerProperty IDTherapyProperty() {
        return IDTherapy;
    }

    /**
     * Returns a string representation of the MeasurementTherapy instance.
     * @return a string representation of the MeasurementTherapy instance
     */
    public String toString() {
        return "MeasurementTherapy: " + getIDMeasurement() + ", " + getIDTherapy();
    }
}

