package model;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Drug {
    private final ReadOnlyIntegerWrapper id = new ReadOnlyIntegerWrapper(this, "id");
    private StringProperty name = new SimpleStringProperty(this, "name");
	private StringProperty description = new SimpleStringProperty(this, "description");

    public Drug(int id, String name, String description){
        this.id.set(id);
        this.name.set(name);
        this.description.set(description);
    }
    /*gets the id*/
    public final int getId() {
        return id.get();
    }
    /*gets the idproperty*/
    public final ReadOnlyIntegerProperty idProperty() {
        return id.getReadOnlyProperty();
    }

    /*gets the name*/
	public String getName() {
		return name.get();
	}
	
	/*gets the nameProperty*/
	public StringProperty nameProperty() {
		return name;
	}

    /*gets the description*/
	public String getDescription() {
		return description.get();
	}
	
	/*gets the descriptionProperty*/
	public StringProperty descriptionProperty() {
		return description;
	}

    @Override
	public String toString() {
		return "Drug: " + getId() + ", " + getName() + ", " + getDescription();
	}
}
