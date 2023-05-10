package controller;

import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertHandler {
	private static AlertHandler single_instance = null;
	
	private static Alert alert;
	
	private AlertHandler() {
		alert = null;
	}
    
	/*Initializes the db instance or return the one already initialized*/
	public static synchronized AlertHandler getInstance()
	{
    if (single_instance == null) {
    	single_instance = new AlertHandler();
    	alert = new Alert(null);
    }
        

    return single_instance;
	}
	
	public void launchAlert(AlertType at, String title, String bodyText) {
		
		alert.setAlertType(at);
		alert.setTitle(title);
        alert.setHeaderText(bodyText);
        alert.showAndWait();
	}


}
