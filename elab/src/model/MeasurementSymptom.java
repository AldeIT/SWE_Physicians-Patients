package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents a relationship between a measurement and a symptom.
 */
public class MeasurementSymptom {
    private IntegerProperty IDMeasurement = new SimpleIntegerProperty(this,"IDMeasurement");
    private IntegerProperty IDSymptom = new SimpleIntegerProperty(this,"IDSymptom");

    /**
     * Constructs a new instance of the class with the given measurement and symptom IDs.
     * @param IDMeasurement the ID of the measurement
     * @param IDSymptom the ID of the symptom
     */
    public MeasurementSymptom(int IDMeasurement, int IDSymptom){
        this.IDMeasurement.set(IDMeasurement);
        this.IDSymptom.set(IDSymptom);
    }

    /**
     * Gets the ID of the measurement.
     * @return the ID of the measurement
     */
    public int getIDMeasurement(){
        return IDMeasurement.get();
    }

    /**
     * Gets the property object for the ID of the measurement.
     * @return the property object for the ID of the measurement
     */
    public IntegerProperty IDMeasurementProperty(){
        return IDMeasurement;
    }

    /**
     * Gets the ID of the symptom.
     * @return the ID of the symptom
     */
    public int getIDSymptom(){
        return IDSymptom.get();
    }

    /**
     * Gets the property object for the ID of the symptom.
     * @return the property object for the ID of the symptom
     */
    public IntegerProperty IDSymptomProperty(){
        return IDSymptom;
    }

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object
     */
    public String toString(){
        return "MeasurementSymptom: " + getIDMeasurement() + ", " + getIDSymptom();
    }
}

