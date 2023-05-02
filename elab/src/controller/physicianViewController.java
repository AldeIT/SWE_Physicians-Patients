package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class physicianViewController {
	@FXML
	private Label welcomeLabel;
	
	@FXML
    private AnchorPane root;
	
	public void setWelcomeLabelText(String s) {
		welcomeLabel.setText(s);
	}

}
