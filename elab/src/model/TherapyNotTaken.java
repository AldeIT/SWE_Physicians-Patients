package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TherapyNotTaken {
	private StringProperty name = new SimpleStringProperty(this, "name");
	private StringProperty surname = new SimpleStringProperty(this, "surname");
    private StringProperty drug = new SimpleStringProperty(this,"drug");
    private IntegerProperty remaining = new SimpleIntegerProperty(this,"remaining");
    private StringProperty email = new SimpleStringProperty(this,"email");

    public TherapyNotTaken(String name, String surname, String drug, int remaining, String email){
        this.name.set(name);
        this.surname.set(surname);
        this.drug.set(drug);
        this.remaining.set(remaining);
        this.email.set(email);
    }

    /*gets the nameProperty*/
	public StringProperty nameProperty() {
		return name;
	}

    /*gets the name*/
	public String getName() {
		return name.get();
	}

    /*gets the surnameProperty*/
	public StringProperty surnameProperty() {
		return surname;
	}

    /*gets the surname*/
	public String getSurname() {
		return surname.get();
	}

    /*gets the drugProperty*/
	public StringProperty drugProperty() {
		return drug;
	}

    /*gets the drug*/
	public String getDrug() {
		return drug.get();
	}

    /*gets the remainingProperty*/
	public IntegerProperty remainingProperty() {
		return remaining;
	}

    /*gets the remaining*/
    public int getRemaining(){
        return remaining.get();
    }

    /*gets the emailProperty*/
	public StringProperty emailProperty() {
		return email;
	}

    /*gets the email*/
	public String getEmail() {
		return email.get();
	}

    public String toString(){
        return "Therapy Not Taken: " + getName() + ", " + getSurname() + ", " + getDrug() + ", " + getRemaining() + ", " + getEmail();
    }
}
