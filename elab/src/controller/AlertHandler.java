package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * The AlertHandler class is a singleton class that manages the display of alert messages
 * in a JavaFX application.
 */
public class AlertHandler {
	
	/**
	 * The single instance of the AlertHandler class.
	 */
	private static AlertHandler single_instance = null;
	
	/**
	 * The Alert object used to display alert messages.
	 */
	private static Alert alert;
	
	/**
	 * Private constructor for the AlertHandler class. Initializes the alert instance.
	 */
	private AlertHandler() {
		alert = null;
	}
	
	/**
	 * Returns the single instance of the AlertHandler class, initializing it if necessary.
	 * 
	 * @return the single instance of the AlertHandler class
	 */
	public static synchronized AlertHandler getInstance() {
		if (single_instance == null) {
			single_instance = new AlertHandler();
			alert = new Alert(null);
		}
		
		return single_instance;
	}
	
	/**
	 * Launches an alert message with the specified AlertType, title, and body text.
	 * 
	 * @param at the AlertType of the alert message
	 * @param title the title of the alert message
	 * @param bodyText the body text of the alert message
	 */
	public void launchAlert(AlertType at, String title, String bodyText) {
		alert.setAlertType(at);
		alert.setTitle(title);
		alert.setHeaderText(bodyText);
		alert.showAndWait();
	}
}

