package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * This class represents a measurement-pathology association, linking a measurement to a pathology.
 */
public class MeasurementPathology {
    private IntegerProperty IDMeasurement = new SimpleIntegerProperty(this,"IDMeasurement");
    private IntegerProperty IDPathology = new SimpleIntegerProperty(this,"IDSymptom");

    /**
     * Constructs a new MeasurementPathology object with the given IDMeasurement and IDPathology.
     *
     * @param IDMeasurement the ID of the measurement
     * @param IDPathology the ID of the pathology
     */
    public MeasurementPathology(int IDMeasurement, int IDPathology){
        this.IDMeasurement.set(IDMeasurement);
        this.IDPathology.set(IDPathology);
    }

    /**
     * Gets the IDMeasurement.
     *
     * @return the IDMeasurement
     */
    public int getIDMeasurement(){
        return IDMeasurement.get();
    }

    /**
     * Gets the IDMeasurement property.
     *
     * @return the IDMeasurement property
     */
    public IntegerProperty IDMeasurementProperty(){
        return IDMeasurement;
    }

    /**
     * Gets the IDPathology.
     *
     * @return the IDPathology
     */
    public int getIDPathology(){
        return IDPathology.get();
    }

    /**
     * Gets the IDPathology property.
     *
     * @return the IDPathology property
     */
    public IntegerProperty IDPathologyProperty(){
        return IDPathology;
    }

    /**
     * Returns a string representation of the MeasurementPathology object.
     *
     * @return a string representation of the MeasurementPathology object
     */
    public String toString(){
        return "MeasurementPathology: " + getIDMeasurement() + ", " + getIDPathology();
    }
}

