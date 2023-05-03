package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Therapy {
    private ReadOnlyIntegerWrapper id = new ReadOnlyIntegerWrapper(this,"id");
    private IntegerProperty daily_dose = new SimpleIntegerProperty(this,"dailydose");
    private IntegerProperty quantity = new SimpleIntegerProperty(this,"quantity");
    private StringProperty directions = new SimpleStringProperty(this,"directions");
    private IntegerProperty done = new SimpleIntegerProperty(this,"done");
    private IntegerProperty IDDrug = new SimpleIntegerProperty(this,"IDdrug");
    private StringProperty CFPatient = new SimpleStringProperty(this,"CFpatient");
    private StringProperty CFPhysician = new SimpleStringProperty(this,"CFphysician");

    public Therapy(int id, int daily_dose, int quantity, String directions, int done, int IDDrug, String CFPatient, String CFPhysician){
        this.id.set(id);
        this.daily_dose.set(daily_dose);
        this.quantity.set(quantity);
        this.directions.set(directions);
        this.done.set(done);
        this.IDDrug.set(IDDrug);
        this.CFPatient.set(CFPatient);
        this.CFPhysician.set(CFPhysician);
    }

    /*gets the id*/
    public int getID(){
        return id.get();
    }

    /*gets the idProperty*/
    public ReadOnlyIntegerProperty idProperty(){
        return id.getReadOnlyProperty();
    }

    /*gets the dailyDose*/
    public int getDailyDose(){
        return daily_dose.get();
    }

    /*gets the dailyDoseProperty*/
    public IntegerProperty dailyDoseProperty(){
        return daily_dose;
    }

    /*gets the quantity*/
    public int getQuantity(){
        return quantity.get();
    }

    /*gets the quantityProperty*/
    public IntegerProperty quantityProperty(){
        return quantity;
    }

    /*gets the directions*/
    public String getDirections(){
        return directions.get();
    }

    /*gets the directionsProperty*/
    public StringProperty directionsProperty(){
        return directions;
    }

    /*gets the done*/
    public int getDone(){
        return done.get();
    }

    /*gets the doneProperty*/
    public IntegerProperty doneProperty(){
        return done;
    }

    /*gets the IDDrug*/
    public int getIDDrug(){
        return IDDrug.get();
    }

    /*gets the IDDrugProperty*/
    public IntegerProperty IDDrugProperty(){
        return IDDrug;
    }

    /*gets the CFPatient*/
    public String getCFPatient(){
        return CFPatient.get();
    }

    /*gets the CFPatientProperty*/
    public StringProperty CFPatientProperty(){
        return CFPatient;
    }

    /*gets the CFPhysician*/
    public String getCFPhysician(){
        return CFPhysician.get();
    }

    /*gets the CFPhysicianProperty*/
    public StringProperty CFPhysicianProperty(){
        return CFPhysician;
    }

    public String toString(){
        return "Therapy: " + getID() + ", " + getDailyDose() + ", " + getQuantity() + ", " + getDirections() + ", " + getDone() + ", " + getIDDrug() + ", " + getCFPatient() + ", " + getCFPhysician();
    }
} 
