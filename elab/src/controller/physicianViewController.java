package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
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
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.BloodPressure;
import model.DB_Model;
import model.Patient;
import model.Physician;
import model.TherapyNotTaken;

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
	
    @FXML
    private TableView<Patient> tableViewBloodPressure;

    @FXML
    private TableColumn<Patient, String> tableViewBloodPressureBP;

    @FXML
    private TableColumn<Patient, String> tableViewBloodPressureEmail;

    @FXML
    private TableColumn<Patient, String> tableViewBloodPressureName;

    @FXML
    private TableColumn<Patient, String> tableViewBloodPressureSurname;
    
    @FXML
    private TableView<TherapyNotTaken> tableViewTherapiesNotTaken;

    @FXML
    private TableColumn<TherapyNotTaken, String> tableViewTherapiesNotTakenEmail;

    @FXML
    private TableColumn<TherapyNotTaken, String> tableViewTherapiesNotTakenName;

    @FXML
    private TableColumn<TherapyNotTaken, Integer> tableViewTherapiesNotTakenRemaining;

    @FXML
    private TableColumn<TherapyNotTaken, String> tableViewTherapiesNotTakenSurname;

    @FXML
    private TableColumn<TherapyNotTaken, String> tableViewTherapiesNotTakenTherapy;
    
    @FXML
    private TabPane tabpane;
	
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

	public void initInfo() throws SQLException, ParseException {
		
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

		
		setWarnings();
		
		setNotTaken();
		
		this.tabpane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
		    
		    
		    if (newTab.getId().equals("patientsTab")) {
		    	try {
		    		setCurrentPatients();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    
		    if (newTab.getId().equals("checkTab")) {
		    	try {
		    		setWarnings();
		    		setNotTaken();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    
		    if (newTab.getId().equals("backToLogin")) {
		    	this.session = null;
		    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/login.fxml"));
				Parent root = null;
				try {
					root = loader.load();
				} catch (IOException e) {
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
		
	void setWarnings() throws SQLException {
		String q = "SELECT * FROM Patient WHERE CFphysician='" + session.getCF() + "';";
		ResultSet rs = db.runQuery(q), rs2;
		
		String queryAvg;
		
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nowMinusOneWeek = now.minusWeeks(1);
		
		ObservableList<Patient> list = FXCollections.observableArrayList();
		
        long timestamp = nowMinusOneWeek.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Patient temp;
		int sbp, dbp;
		BloodPressure pressure = null;
		while(rs.next()) {
			queryAvg = "SELECT AVG(sbp), AVG(dbp) FROM Measurement WHERE CFpatient='" + rs.getString("CF") + "' AND datetime>='" + timestamp + "';";
			rs2 = db.runQuery(queryAvg);
			
			sbp = rs2.getInt(1);dbp = rs2.getInt(2);
			
			

			if(sbp<120 && dbp<80){
				pressure = BloodPressure.OPTIMAL;
			}
			else if(sbp>=140 && sbp<=149 && dbp<90){
				pressure = BloodPressure.ISOLATED_SYSTOLIC_BORDERLINE;				
			}
			else if(sbp>=150 && dbp<90){
				pressure = BloodPressure.ISOLATED_SYSTOLIC;
			}
			else if(sbp<130 && dbp<85){
				pressure = BloodPressure.NORMAL;				
			}
			else if(sbp<=139 && dbp<=89){
				pressure = BloodPressure.HIGH;				
			}
			else if(sbp<=149 && dbp<=94){
				pressure = BloodPressure.GRADE_1_BORDERLINE;				
			}
			else if(sbp<=159 && dbp<=99){
				pressure = BloodPressure.GRADE_1_MILD;				
			}
			else if(sbp<=179 && dbp<=109){
				pressure = BloodPressure.GRADE_2_MODERATE;				
			}
			else if(sbp>=180 && dbp>=110){
				pressure = BloodPressure.GRADE_3_SEVERE;				
			}

			temp = new Patient(rs.getString("CF"), rs.getString("email"), rs.getString("password"), rs.getString("name"), rs.getString("surname"), rs.getString("sex"), rs.getDate("birthdate").toLocalDate(), rs.getString("nationality"), rs.getString("street"), rs.getInt("civicnumber"), rs.getInt("cap"), rs.getString("city"), rs.getString("phonenumber"), rs.getString("informations"), session.getCF());
			
			temp.setBloodPressure(pressure);
			
			list.add(temp);
			
		}
		
		tableViewBloodPressure.setItems(list);
		tableViewBloodPressureName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableViewBloodPressureSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
		tableViewBloodPressureEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tableViewBloodPressureBP.setCellValueFactory(new PropertyValueFactory<>("bloodPressureString"));
	
		
	}
	
	void setNotTaken() throws SQLException, ParseException {
		String q="SELECT CF, name, surname, email FROM Patient WHERE CFphysician='" + session.getCF() + "';", queryTherapies, countIntakes, nameDrug;
		
		ResultSet rs = db.runQuery(q), rs2, rs3, rs4;
		int max;
		LocalDate check = LocalDate.now().minusDays(3);
		LocalDateTime threeDaysAgo = LocalDate.now().atTime(LocalTime.of(1, 0)).minusDays(3);
		long timestamp = db.LocalDateToLong(check);
		long timestampThreeDaysAgo =threeDaysAgo.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		ObservableList<TherapyNotTaken> list = FXCollections.observableArrayList();
		
		while (rs.next()) {
			queryTherapies = "SELECT id, IDdrug, dailydose, quantity FROM Therapy WHERE CFpatient='" + rs.getString("CF") + "' AND endDate IS NULL AND startDate<='" + timestamp + "';";
			rs2 = db.runQuery(queryTherapies);
			while(rs2.next()) {
				max = (rs2.getInt("dailydose") * rs2.getInt("quantity")) * 3;
				countIntakes = "SELECT SUM(quantity) FROM drugIntakes\n" +
						"WHERE IDtherapy='" + rs2.getInt("id") + "' AND datetime>='" + timestampThreeDaysAgo+ "';";
				nameDrug = "SELECT name FROM Drug WHERE id='" + rs2.getInt("IDdrug") + "';";
				
				rs4 = db.runQuery(nameDrug);
				
				rs3 = db.runQuery(countIntakes);
				
				if (max - rs3.getInt(1) > 0) {
					list.add(new TherapyNotTaken(rs.getString("name"), rs.getString("surname"), rs4.getString("name"), max - rs3.getInt(1), rs.getString("email")));
				}
				
				
			}
		}
		
		tableViewTherapiesNotTaken.setItems(list);
		tableViewTherapiesNotTakenName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableViewTherapiesNotTakenSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
		tableViewTherapiesNotTakenEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tableViewTherapiesNotTakenRemaining.setCellValueFactory(new PropertyValueFactory<>("remaining"));
		tableViewTherapiesNotTakenTherapy.setCellValueFactory(new PropertyValueFactory<>("drug"));
		
	}
	

}
