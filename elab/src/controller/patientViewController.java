package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;

public class patientViewController {
	@FXML
	private AnchorPane root;
	@FXML
	private Label welcomeLabel;
	
	public void setWelcomeLabelText(String s) {
		welcomeLabel.setText(s);
	}

}
