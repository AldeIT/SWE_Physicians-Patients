package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DB_Model;
import model.Patient;
import model.Physician;

public class showPatientViewController {
	@FXML
    private Tab backToPhysician;

    @FXML
    private Button btnUpdateInformations;

    @FXML
    private Tab dashboardPatients;

    @FXML
    private Label labelBirthdate;

    @FXML
    private Label labelCAP;

    @FXML
    private Label labelCF;

    @FXML
    private Label labelCity;

    @FXML
    private Label labelCivicNumber;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelName;

    @FXML
    private Label labelNationality;

    @FXML
    private Label labelPhoneNumber;

    @FXML
    private Label labelSex;

    @FXML
    private Label labelStreet;

    @FXML
    private Label labelSurname;

    @FXML
    private AnchorPane root;

    @FXML
    private TabPane tabpane;

    @FXML
    private Tab test;

    @FXML
    private TextField textFieldInformations;

    
	private Patient patient;
	
	private Physician myPhysician;
	
	private DB_Model db;
	
	 @FXML
	 void btnUpdateInformationsOnClicked(ActionEvent event) throws SQLException {
		 String content = textFieldInformations.getText();
		 
		 if (!content.equals(patient.getInformations())) {
			 db.updatePatient(patient.getCF(), content);
		 }
	 }
	
	void setSession(Patient patient, Physician physician) {
		this.patient = new Patient(patient);
		this.myPhysician = new Physician (physician);
	}
	
	void initInfo() {
		
		try {
			db = DB_Model.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("init");
		labelCF.setText(patient.getCF());
		labelName.setText(patient.getName());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = patient.getBirthdate().format(formatter);		
		labelBirthdate.setText(formattedDate);	
		labelStreet.setText(patient.getStreet());
		labelSurname.setText(patient.getSurname());
		labelPhoneNumber.setText(patient.getPhoneNumber());
		labelSex.setText(patient.getSex());
		labelNationality.setText(patient.getNationality());
		labelEmail.setText(patient.getEmail());
		labelCAP.setText(Integer.toString(patient.getCAP()));
		labelCity.setText(patient.getCity());
		labelCivicNumber.setText(Integer.toString(patient.getCivicNumber()));
		textFieldInformations.setText(patient.getInformations());
		
		
		this.tabpane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
		    if (newTab.getId().equals("backToPhysician")) {
		    	/*Getting the fxml*/
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/physicianView.fxml"));
				Parent root = null;
				try {
					root = loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*Getting the controller*/
				physicianViewController controller = loader.getController();
				controller.setSession(myPhysician);
				try {
					controller.initInfo();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Switchamo Scene");
				/*Setting the scene*/
				Scene scene = new Scene(root);
				Stage stage = (Stage) tabpane.getScene().getWindow();
				stage.setScene(scene);
				stage.setMinWidth(1000);
		        stage.setMinHeight(1000);
		        stage.setResizable(true);
				stage.show();
		    }
		});
	}
	
	@FXML
    void btnShowPatientOnClicked(ActionEvent event) {

    }

    @FXML
    void textFieldCFOnTyped(KeyEvent event) {

    }
	
	  

}
