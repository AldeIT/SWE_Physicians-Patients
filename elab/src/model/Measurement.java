package model;

import java.time.LocalDateTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Measurement {
    private final ReadOnlyIntegerWrapper id = new ReadOnlyIntegerWrapper(this, "id");
    private IntegerProperty sbp = new SimpleIntegerProperty(this, "sbp");
    private IntegerProperty dbp = new SimpleIntegerProperty(this, "dbp");
	private ObjectProperty<LocalDateTime> datetime = new SimpleObjectProperty<>(this, "datetime", null);
    private StringProperty informations = new SimpleStringProperty(this, "informations");
    private StringProperty CFpatient = new SimpleStringProperty(this, "CFpatient");

    public Measurement(int id, int sbp, int dbp, LocalDateTime datetime, String informations, String CFpatient){
        this.id.set(id);
        this.sbp.set(sbp);
        this.dbp.set(dbp);
        this.datetime.set(datetime);
        this.informations.set(informations);
        this.CFpatient.set(CFpatient);
    }

    /*gets the id*/
    public final int getId() {
        return id.get();
    }
    /*gets the idProperty*/
    public final ReadOnlyIntegerProperty idProperty() {
        return id.getReadOnlyProperty();
    }

    /*gets the sbp*/
    public int getSbp(){
        return sbp.get();
    }
    /*gets the sbpProperty*/
    public IntegerProperty sbpProperty(){
        return sbp;
    }

    /*gets the dbp*/
    public int getDbp(){
        return dbp.get();
    }
    /*gets the dbpProperty*/
    public IntegerProperty dbpProperty(){
        return dbp;
    }

    /*gets the datetime*/
    public LocalDateTime getDateTime(){
        return datetime.get();
    }
    /*gets the datetimeProperty*/
    public ObjectProperty<LocalDateTime> datetimeProperty(){
        return datetime;
    }

    /*gets the informations*/
	public String getInformations() {
		return informations.get();
	}
	
	/*gets the informationsProperty*/
	public StringProperty informationsProperty() {
		return informations;
	}
    /*gets the CFpatient*/
	public String getCFPatient() {
		return CFpatient.get();
	}
	
	/*gets the descriptionProperty*/
	public StringProperty CFPatientProperty() {
		return CFpatient;
	}

    @Override
	public String toString() {
		return "Measurement: " + getId() + ", " + getSbp() + "/" + getDbp() + ", " + getDateTime() + ", " + getCFPatient();
	}






}
