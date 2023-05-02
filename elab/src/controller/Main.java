package controller;
	
import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DB_Model;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		
		/*Starting the database*/
		try {
			DB_Model db = DB_Model.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
