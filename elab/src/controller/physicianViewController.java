package controller;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Physician;

public class physicianViewController{
	
	@FXML
    private AnchorPane root;
	
	@FXML
	private Label patientLabel;
	
	@FXML
	private Label drugLabel;
	
	@FXML
    private Label labelBirthdate;

    @FXML
    private Label labelCAP;

    @FXML
    private Label labelCF;

    @FXML
    private Label labelCity;

    @FXML
    private Label labelCivicNumber;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelName;

    @FXML
    private Label labelNationality;

    @FXML
    private Label labelPhoneNumber;

    @FXML
    private Label labelSex;

    @FXML
    private Label labelStreet;

    @FXML
    private Label labelSurname;
	
	private Physician session;
	
	public void setSession(Physician session) {
		this.session = new Physician(session);
		System.out.println("sessione" + this.session.getCF());
	}

	public void initInfo() {
		System.out.println("init");
		labelCF.setText(session.getCF());
		labelName.setText(session.getName());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = session.getBirthdate().format(formatter);		
		labelBirthdate.setText(formattedDate);	
		labelStreet.setText(session.getStreet());
		labelSurname.setText(session.getSurname());
		labelPhoneNumber.setText(session.getPhoneNumber());
		labelSex.setText(session.getSex());
		labelNationality.setText(session.getNationality());
		labelEmail.setText(session.getEmail());
		labelCAP.setText(Integer.toString(session.getCAP()));
		labelCity.setText(session.getCity());
		labelCivicNumber.setText(Integer.toString(session.getCivicNumber()));
	}
		

}
