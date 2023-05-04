package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class MeasurementSymptom {
    private IntegerProperty IDMeasurement = new SimpleIntegerProperty(this,"IDMeasurement");
    private IntegerProperty IDSymptom = new SimpleIntegerProperty(this,"IDSymptom");

    public MeasurementSymptom(int IDMeasurement, int IDSymptom){
        this.IDMeasurement.set(IDMeasurement);
        this.IDSymptom.set(IDSymptom);
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
    public int getIDSymptom(){
        return IDSymptom.get();
    }

    /*gets the IDSymptom*/
    public IntegerProperty IDSymptomProperty(){
        return IDSymptom;
    }

    public String toString(){
        return "MeasurementSymptom: " + getIDMeasurement() + ", " + getIDSymptom();
    }
}
