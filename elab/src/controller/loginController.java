package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	
	// Event Listener on Button[#btnLogin].onAction
	@FXML
	public void btnLoginOnClicked(ActionEvent event) throws IOException {
		
		boolean isRadioBtnPhysicianSelected = radioBtnPhysician.isSelected();
		boolean isRadioBtnPatientSelected = radioBtnPatient.isSelected();
		
		DB_Model db = null;
		String password = null;
		try {
			db = DB_Model.getInstance();
			if (isRadioBtnPhysicianSelected) {
				String q = "SELECT * FROM physician WHERE CF='" + labelCF.getText() + 
						"' AND password='" + db.hashPassword(labelPassword.getText()) + "';";
				ResultSet st = null;
				st = db.runQuery(q);
				password = labelPassword.getText();
				labelCF.setText("");
				labelPassword.setText("");
				if (st.getString("CF") == null) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
			        alert.setTitle("Error Physician");
			        alert.setHeaderText("No result");
			        alert.showAndWait();
				}else {
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
				}
			}else if(isRadioBtnPatientSelected){
				String q = "SELECT * FROM patient WHERE CF='" + labelCF.getText() + 
						"' AND password='" + db.hashPassword(labelPassword.getText()) + "';";
				ResultSet st = null;
				st = db.runQuery(q);
				password = labelPassword.getText();
				labelCF.setText("");
				labelPassword.setText("");
				if (st.getString("CF") == null) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
			        alert.setTitle("Error Patient");
			        alert.setHeaderText("No result");
			        alert.showAndWait();
				}else {
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
					
				}
			}else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
		        alert.setTitle("Devi selezionare almeno uno dei due campi");
		        alert.setHeaderText("Mona");
		        alert.showAndWait();
				return;
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("CF: "+ labelCF.getText() + ", Password: " + labelPassword.getText());
		System.out.println("Button Clicked!");
	}
	
	@FXML
    void radioBtnPatientSelected(ActionEvent event) {
		System.out.println("Patient selected/non selected");
		radioBtnPhysician.setSelected(false);
    }

    @FXML
    void radioBtnPhysicianSelected(ActionEvent event) {
    	System.out.println("Physician selected/non selected");
    	radioBtnPatient.setSelected(false);
    }
	
	
    
	
}
