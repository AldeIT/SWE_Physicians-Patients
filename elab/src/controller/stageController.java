package controller;

import java.sql.SQLException;

import javafx.stage.Stage;
import model.DB_Model;


/**
 * This class is used to control the main stage of the application.
 * It has a method to set the stage and a method to handle the closing event.
 * When the user tries to close the window, the handleCloseRequest method is called
 * and it performs necessary cleanup before closing the application.
 */
public class stageController {
	
	private Stage stage;
		
	/**
	 * Sets the stage for this controller.
	 *
	 * @param stage The stage to set.
	 */
	public void setStage(Stage stage) {
        this.stage = stage;
    }
	
	/**
	 * Handles the closing event for the stage.
	 *
	 * @param windowEvent The WindowEvent associated with the closing event.
	 */
	public void handleCloseRequest(javafx.stage.WindowEvent windowEvent) {
        // Handle the close request event
        // Here you can show a confirmation dialog or perform any necessary cleanup
        // before closing the application
		try {
			DB_Model db = DB_Model.getInstance();
			db.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Chiusura");
        windowEvent.consume(); // Consume the event to prevent the default close action
        closeApplication();
    }

	/**
	 * Closes the application by closing the main stage.
	 */
    private void closeApplication() {
        stage.close();
    }
}
