package model;

import java.time.LocalDateTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class DrugIntake {
    private final ReadOnlyIntegerWrapper id = new ReadOnlyIntegerWrapper(this, "id");
	private ObjectProperty<LocalDateTime> datetime = new SimpleObjectProperty<>(this, "datetime", null);
    private IntegerProperty quantity = new SimpleIntegerProperty(this, "quantity");
    private IntegerProperty IDtherapy = new SimpleIntegerProperty(this, "IDtherapy");

    public DrugIntake(int id, LocalDateTime datetime, int quantity, int IDtherapy){
        this.id.set(id);
        this.datetime.set(datetime);
        this.quantity.set(quantity);
        this.IDtherapy.set(IDtherapy);
    }

    /*gets the id*/
    public final int getId() {
        return id.get();
    }
    /*gets the idproperty*/
    public final ReadOnlyIntegerProperty idProperty() {
        return id.getReadOnlyProperty();
    }
    /*gets the datetime*/
    public LocalDateTime getDateTime(){
        return datetime.get();
    }
    /*gets the datetimeProperty*/
    public ObjectProperty<LocalDateTime> datetimeProperty(){
        return datetime;
    }
    /*gets the quantity*/
    public int getQuantity(){
        return quantity.get();
    }
    /*gets the quantityProperty*/
    public IntegerProperty quantityProperty(){
        return quantity;
    }
    /*gets the IDtherapy*/
    public int getIDTherapy(){
        return IDtherapy.get();
    }
    /*gets the IDtherapyProperty*/
    public IntegerProperty IDTherapyProperty(){
        return IDtherapy;
    }

    @Override
	public String toString() {
		return "DrugIntake: " + getId() + ", " + getDateTime() + ", " + getIDTherapy();
	}
}
