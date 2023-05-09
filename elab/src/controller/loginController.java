package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DB_Model;
import model.Physician;
import model.Patient;

/*The controller for the login view*/
public class loginController {
	
	@FXML
    private AnchorPane root;
	
	@FXML
	private Button btnLogin;
	@FXML
	private TextField labelCF;
	@FXML
	private PasswordField labelPassword;
	
	@FXML
    private RadioButton radioBtnPatient;

    @FXML
    private RadioButton radioBtnPhysician;

	
    /**
     * Performs login operation on both Physician and Patient.
     *
     * @param the Action Event.
     * @throws IOException if there is a problem loading the new view.
     * @throws SQLException 
     */
	@FXML
	public void btnLoginOnClicked(ActionEvent event) throws IOException, SQLException  {
		
		boolean isRadioBtnPhysicianSelected = radioBtnPhysician.isSelected();
		boolean isRadioBtnPatientSelected = radioBtnPatient.isSelected();
		
		DB_Model db = null;
		String password = null;
		
		try {
			db = DB_Model.getInstance();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			System.out.println("Error during the initialization of the Database");
		}
		String q;
		
		if (labelCF.getText().equals("") || labelPassword.getText().equals("")) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Blank Input");
	        alert.setHeaderText("You should write something in the labels");
	        alert.showAndWait();
		}
		
		if (isRadioBtnPhysicianSelected) {
			q = "SELECT * FROM physician WHERE CF='" + labelCF.getText() + 
					"' AND password='" + db.hashPassword(labelPassword.getText()) + "';";
		}else if(isRadioBtnPatientSelected){
			q = "SELECT * FROM patient WHERE CF='" + labelCF.getText() + 
					"' AND password='" + db.hashPassword(labelPassword.getText()) + "';";
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Selection Error");
	        alert.setHeaderText("You need to select one of the radio button!");
	        alert.showAndWait();
			return;
		}
		
		ResultSet st = null;
		try {
			st = db.runQuery(q);
		}catch(SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Error Database");
	        alert.setHeaderText("Error Performing a query");
	        alert.showAndWait();
	        return;
		}
		
		if (st.getString("CF") == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Unable to find the account");
	        alert.setHeaderText("Wrong CF/password!");
	        alert.showAndWait();
	        return;
		}
		
		password = labelPassword.getText();
		labelCF.setText("");
		labelPassword.setText("");
		
		if (isRadioBtnPhysicianSelected) {
			openPhysician(st, password, event);
		}else if(isRadioBtnPatientSelected){
			openPatient(st, password, event);
		}
		
		
		
		
		//System.out.println("CF: "+ labelCF.getText() + ", Password: " + labelPassword.getText());
	}
	
	void openPhysician(ResultSet st, String password, ActionEvent event) throws SQLException, IOException {
		System.out.println(st.getString("CF"));
		System.out.println("Andiamo in un'altra schermata Physician");
		Physician session = new Physician(st.getString("CF"), 
				st.getString("email"), 
				password, 
				st.getString("name"), 
				st.getString("surname"), 
				st.getString("sex"), 
				st.getDate("birthdate").toLocalDate(), 
				st.getString("nationality"), 
				st.getString("street"), 
				st.getInt("civicnumber"), 
				st.getInt("cap"), 
				st.getString("city"), 
				st.getString("phonenumber"));
		/*Getting the fxml*/
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/physicianView.fxml"));
		Parent root = loader.load();
		/*Getting the controller*/
		physicianViewController controller = loader.getController();
		controller.setSession(session);
		try {
			controller.initInfo();
		} catch (ParseException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Parse Error");
	        alert.setHeaderText("Could not change scene because of a parse error");
	        alert.showAndWait();
	        return;
		}
		System.out.println("Switchamo Scene");
		/*Setting the scene*/
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setMinWidth(1000);
        stage.setMinHeight(1000);
        stage.setResizable(true);
		stage.show();
	}
	
	void openPatient(ResultSet st, String password, ActionEvent event) throws SQLException, IOException {
		System.out.println(st.getString("CF"));
		System.out.println("Andiamo in un'altra schermata Patient");
		
		Patient session = new Patient(st.getString("CF"), 
				st.getString("email"), 
				password, 
				st.getString("name"), 
				st.getString("surname"), 
				st.getString("sex"), 
				st.getDate("birthdate").toLocalDate(), 
				st.getString("nationality"), 
				st.getString("street"), 
				st.getInt("civicnumber"), 
				st.getInt("cap"), 
				st.getString("city"), 
				st.getString("phonenumber"),
				st.getString("informations"),
				st.getString("CFphysician"));
		/*Getting the fxml*/
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/patientView.fxml"));
		Parent root = loader.load();
		/*Getting the controller*/
		patientViewController controller = loader.getController();
		controller.setSession(session);
		controller.initInfo();
		System.out.println("Switchamo Scene");
		/*Setting the scene*/
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setMinWidth(1000);
        stage.setMinHeight(1000);
        stage.setResizable(true);
		stage.show();
		controller.firstAlert();
	}
	
	/**
	 * Gets called when you click on the radioButton, makes sure the other is not selected
	 *
	 * @param the Action Event.
	 */
	@FXML
    void radioBtnPatientSelected(ActionEvent event) {
		System.out.println("Patient selected/non selected");
		radioBtnPhysician.setSelected(false);
    }

	
	/**
	 * Gets called when you click on the radioButton, makes sure the other is not selected
	 *
	 * @param the Action Event.
	 */
    @FXML
    void radioBtnPhysicianSelected(ActionEvent event) {
    	System.out.println("Physician selected/non selected");
    	radioBtnPatient.setSelected(false);
    }
	
	
    
	
}
