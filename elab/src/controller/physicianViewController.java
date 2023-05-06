package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DB_Model;
import model.Patient;
import model.Physician;
import model.Therapy;

public class physicianViewController{
	
	@FXML
	private Button btnShowPatient;
	
	@FXML
    private AnchorPane root;
	
	@FXML
	private Label patientLabel;
	
	@FXML
	private Label drugLabel;
	
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
	
	private Physician session;
	
	@FXML
    private TextField textFieldCF;
	
	@FXML
    private ListView<Patient> listViewPatients;
	
	private ObservableList<Patient> currentPatients;
	
	private DB_Model db;
	
	@FXML
    void btnShowPatientOnClicked(ActionEvent event) throws IOException, SQLException {
		
		
		MultipleSelectionModel<Patient> selectionModel = listViewPatients.getSelectionModel();

		
		Patient selected = selectionModel.getSelectedItem();
		
		if (selected == null) {
			System.out.println("devi selezionare qualcosa");
			return;
		}
		
		/*Getting the fxml*/
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/showPatientView.fxml"));
		Parent root = loader.load();
		/*Getting the controller*/
		showPatientViewController controller = loader.getController();
		
		controller.setSession(selected, session);
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

    @FXML
    void textFieldCFOnTyped(KeyEvent event) throws SQLException {
    	String content = textFieldCF.getText();
    	if (content.equals("")) {
    		listViewPatients.setItems(currentPatients);
    		return;
    	}
    	ObservableList<Patient> searchedPatients = db.getSearchedPatients(session.getCF(), "%" + content + "%");
    	
    	listViewPatients.setItems(searchedPatients);
    	
    }

	void setCurrentPatients() throws SQLException {
		currentPatients = db.getPatientsPhysician(session.getCF());
		listViewPatients.setItems(currentPatients);
	}
	
	public void setSession(Physician session) {
		this.session = new Physician(session);
		System.out.println("sessione" + this.session.getCF());
	}

	public void initInfo() throws SQLException {
		
		try {
			db = DB_Model.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("init");
		labelCF.setText(session.getCF());
		labelName.setText(session.getName());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = session.getBirthdate().format(formatter);		
		labelBirthdate.setText(formattedDate);	
		labelStreet.setText(session.getStreet());
		labelSurname.setText(session.getSurname());
		labelPhoneNumber.setText(session.getPhoneNumber());
		labelSex.setText(session.getSex());
		labelNationality.setText(session.getNationality());
		labelEmail.setText(session.getEmail());
		labelCAP.setText(Integer.toString(session.getCAP()));
		labelCity.setText(session.getCity());
		labelCivicNumber.setText(Integer.toString(session.getCivicNumber()));

		setCurrentPatients();
	}
		

}
