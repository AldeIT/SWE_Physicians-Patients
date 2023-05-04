package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class MeasurementTherapy {
    private IntegerProperty IDMeasurement = new SimpleIntegerProperty(this,"IDMeasurement");
    private IntegerProperty IDTherapy = new SimpleIntegerProperty(this,"IDSymptom");

    public MeasurementTherapy(int IDMeasurement, int IDTherapy){
        this.IDMeasurement.set(IDMeasurement);
        this.IDTherapy.set(IDTherapy);
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
    public int getIDTherapy(){
        return IDTherapy.get();
    }

    /*gets the IDSymptom*/
    public IntegerProperty IDTherapyProperty(){
        return IDTherapy;
    }

    public String toString(){
        return "MeasurementTherapy: " + getIDMeasurement() + ", " + getIDTherapy();
    }
}
