package model;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Symptom {
    private ReadOnlyIntegerWrapper id = new ReadOnlyIntegerWrapper(this,"id");
    private StringProperty description = new SimpleStringProperty(this,"description");

    public Symptom(int id, String description){
        this.id.set(id);
        this.description.set(description);
    }

    /*gets the id*/
    public int getID(){
        return id.get();
    }

    /*gets the idProperty*/
    public ReadOnlyIntegerProperty idProperty(){
        return id.getReadOnlyProperty();
    }

    /*gets the description*/
    public String getDescription(){
        return description.get();
    }

    /*gets the descriptionProperty*/
    public StringProperty descriptionProperty(){
        return description;
    }

    public String toString(){
        return "Symptom: " + getID() + ", " + getDescription();
    }
}
