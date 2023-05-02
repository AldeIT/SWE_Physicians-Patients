package controller;

import java.sql.SQLException;

import javafx.stage.Stage;
import model.DB_Model;

public class stageController {
	
	private Stage stage;
	
	public void setStage(Stage stage) {
        this.stage = stage;
    }
	
	/*Handling the closing event*/
	public void handleCloseRequest(javafx.stage.WindowEvent windowEvent) {
        // Handle the close request event
        // Here you can show a confirmation dialog or perform any necessary cleanup
        // before closing the application
		try {
			DB_Model db = DB_Model.getInstance();
			db.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Chiusura");
        windowEvent.consume(); // Consume the event to prevent the default close action
        closeApplication();
    }

	/*Closes the application*/
    private void closeApplication() {
        // Close the application
        //Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
}
