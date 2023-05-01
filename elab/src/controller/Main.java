package controller;
	
import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.DB_Model;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		try {
			DB_Model db = DB_Model.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done");
		
        /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Prova.fxml"));
        System.out.println("diocane");
        Scene scene = new Scene(fxmlLoader.load());
        System.out.println("diocane2");
        primaryStage.setScene(scene);
        primaryStage.show();*/
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
