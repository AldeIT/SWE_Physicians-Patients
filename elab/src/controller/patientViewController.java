package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import model.Patient;

public class patientViewController {
	@FXML
	private AnchorPane root;
	
	@FXML
	private Label welcomeLabel;
	
	@FXML
	private Label relevationLabel;
	
	@FXML
	private Label drugLabel;

	private Patient session;

	public void setSession(Patient session) {
		this.session = session;
	}
	
	public void setWelcomeLabelText(String s) {
		welcomeLabel.setText(s);
	}

}
