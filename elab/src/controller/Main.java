package controller;
	
import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DB_Model;

/**
 * The Main class is the entry point of the application. It initializes the database and loads the login view.
 * It also sets the closing event of the primary stage and shows the primary stage with the login view.
 */
public class Main extends Application {
	
	/**
	 * The start method is called after the init method has returned and after the system is ready for the application to begin running.
	 * It initializes the database and loads the login view.
	 * It also sets the closing event of the primary stage and shows the primary stage with the login view.
	 *
	 * @param primaryStage The primary stage for this application.	
	 * @throws IOException Signals that an I/O exception of some sort has occurred.
	 */
	public void start(Stage primaryStage) throws IOException {
		/*Starting the database*/
		try {
			DB_Model db = DB_Model.getInstance();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problem initializing the database");
		}
		
		/*Load the Login View*/
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        
        /*Handling the closing event of the scene*/
        //loginController controller = fxmlLoader.getController();
        stageController stageController = new stageController();
        stageController.setStage(primaryStage);
        primaryStage.setOnCloseRequest(stageController::handleCloseRequest);
        
        /*Showing the first View*/
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(600);
        primaryStage.setResizable(false);
        primaryStage.show();
	}
	
	/**
	 * The main method is the entry point of the application.
	 * It launches the JavaFX application.
	 * @param args The command line arguments passed to the application.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
