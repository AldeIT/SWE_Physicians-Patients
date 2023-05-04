package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class MeasurementPathology {
    private IntegerProperty IDMeasurement = new SimpleIntegerProperty(this,"IDMeasurement");
    private IntegerProperty IDPathology = new SimpleIntegerProperty(this,"IDSymptom");

    public MeasurementPathology(int IDMeasurement, int IDPathology){
        this.IDMeasurement.set(IDMeasurement);
        this.IDPathology.set(IDPathology);
    }

    /*gets the IDMeasurement*/
    public int getIDMeasurement(){
        return IDMeasurement.get();
    }

    /*gets the IDMeasurement*/
    public IntegerProperty IDMeasurementProperty(){
        return IDMeasurement;
    }

    /*gets the IDSymptom*/
    public int getIDPathology(){
        return IDPathology.get();
    }

    /*gets the IDSymptom*/
    public IntegerProperty IDPathologyProperty(){
        return IDPathology;
    }

    public String toString(){
        return "MeasurementPathology: " + getIDMeasurement() + ", " + getIDPathology();
    }
}
