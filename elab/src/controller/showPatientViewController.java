package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DB_Model;
import model.Drug;
import model.Pathology;
import model.Patient;
import model.Physician;
import model.Symptom;
import model.Therapy;

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
    private DatePicker datePickerMeasurementEnd;

    @FXML
    private DatePicker datePickerMeasurementStart;
	
	@FXML
    private LineChart<String, Integer> linechartMeasurement;
	
	@FXML
	private TableView<Pathology> tableViewPathologies;

	@FXML
	private TableView<Symptom> tableViewSymptoms;

	@FXML
	private TableView<Therapy> tableViewTherapies;
	
	@FXML
    private TableColumn<Symptom, String> tableViewSymptomDescription;

    @FXML
    private TableColumn<Symptom, Integer> tableViewSymptomID;
    
    @FXML
    private TableColumn<Therapy, String> tableViewTherapiesDirections;

    @FXML
    private TableColumn<Therapy, Integer> tableViewTherapiesID;

    @FXML
    private TableColumn<Therapy, Integer> tableViewTherapiesIDDrug;
    
    @FXML
    private TableColumn<Pathology, String> tableViewPathologiesDescription;

    @FXML
    private TableColumn<Pathology, Integer> tableViewPathologiesID;
    
    @FXML
    private ChoiceBox<Drug> choiceBoxDrug;
    
    @FXML
    private TableView<Therapy> tableViewAllTherapies;

    @FXML
    private TableColumn<Therapy, Integer> tableViewAllTherapiesDailyDose;

    @FXML
    private TableColumn<Therapy, String> tableViewAllTherapiesDirections;

    @FXML
    private TableColumn<Therapy, LocalDate> tableViewAllTherapiesEndDate;

    @FXML
    private TableColumn<Therapy, Integer> tableViewAllTherapiesID;

    @FXML
    private TableColumn<Therapy, Integer> tableViewAllTherapiesIDDrug;

    @FXML
    private TableColumn<Therapy, Integer> tableViewAllTherapiesQuantity;

    @FXML
    private TableColumn<Therapy, LocalDate> tableViewAllTherapiesStartDate;
    
    @FXML
    private Button btnInsertNewTherapy;
    
    @FXML
    private Button btnDeleteTherapy;
    
    @FXML
    private Button btnUpdateTherapy;
	
	LocalDateTime defaultStart;
	
	LocalDateTime defaultEnd;
	
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
	
	void initInfo() throws SQLException {
		
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
			
		
		defaultStart = LocalDateTime.of(2000, 1, 1, 9, 0, 0);
		defaultEnd = LocalDateTime.of(2040, 1, 1, 9, 0, 0);
		setLineChartMeasurement(defaultStart, defaultEnd);

		setAllTherapies();
			
		
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
	
	void setLineChartMeasurement(LocalDateTime start, LocalDateTime end) throws SQLException {
		linechartMeasurement.getData().clear();
		XYChart.Series<String, Integer> sbpSeries = new XYChart.Series<>();
		XYChart.Series<String, Integer> dbpSeries = new XYChart.Series<>();
		
		
		if (start == null) {
			start = defaultStart;
		}
		
		if (end == null) {
			end = defaultEnd;
		}
		
        long timestamp = start.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long timestampNow = end.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        
		
		String q = "SELECT datetime, sbp, dbp, id FROM Measurement\n" +
				   "WHERE CFpatient='" + patient.getCF() + "' AND datetime>='" + timestamp + "' AND datetime<='" + timestampNow + "' ORDER BY datetime;"
				;
		
		ResultSet rs = db.runQuery(q);
		
		ResultSet rsSymptoms, rsTherapies, rsPathologies;
		
		String getSymptoms, getTherapies, getPathologies; 
		
		HashMap<Integer, Symptom> uniqueSymptom = new HashMap<>();
		HashMap<Integer, Therapy> uniqueTherapies = new HashMap<>();
		HashMap<Integer, Pathology> uniquePathologies = new HashMap<>();
		
		while (rs.next()) {
			getSymptoms = "SELECT id, description FROM Symptom INNER JOIN measurement_symptom ms ON Symptom.id=ms.IDsymptom WHERE IDmeasurement='" + rs.getInt(4) + "';";
			getTherapies = "SELECT * FROM Therapy INNER JOIN measurement_therapy ms ON Therapy.id=ms.IDtherapy WHERE IDmeasurement='" + rs.getInt(4) + "';";
			getPathologies = "SELECT * FROM Pathology INNER JOIN measurement_pathology ms ON Pathology.id=ms.IDpathology WHERE IDmeasurement='" + rs.getInt(4) + "';";
			
			rsSymptoms = db.runQuery(getSymptoms);
			
			while(rsSymptoms.next()) {
				
				if (!uniqueSymptom.containsKey(rsSymptoms.getInt(1))) {
					uniqueSymptom.put(rsSymptoms.getInt(1), new Symptom(rsSymptoms.getInt(1), rsSymptoms.getString(2)));
				}
			}
			
			rsTherapies= db.runQuery(getTherapies);
			
			while(rsTherapies.next()) {
				
				if (!uniqueTherapies.containsKey(rsTherapies.getInt("id"))) {
					uniqueTherapies.put(rsTherapies.getInt("id"), new Therapy(rsTherapies.getInt("id"), rsTherapies.getInt("dailydose"), rsTherapies.getInt("quantity"), rsTherapies.getString("directions") ,rsTherapies.getDate("startdate").toLocalDate(), rsTherapies.getDate("enddate")==null ? null : rsTherapies.getDate("enddate").toLocalDate(), rsTherapies.getInt("IDdrug"), rsTherapies.getString("CFpatient"), rsTherapies.getString("CFphysician")));
				}
			}
			
			rsPathologies = db.runQuery(getPathologies);
			
			while(rsPathologies.next()) {
				
				if (!uniquePathologies.containsKey(rsPathologies.getInt("id"))) {
					uniquePathologies.put(rsPathologies.getInt("id"), new Pathology(rsPathologies.getInt(1), rsPathologies.getString(2)));
				}
			}
			
			
			sbpSeries.getData().add(new XYChart.Data<String, Integer>(rs.getTimestamp(1).toLocalDateTime().toString(), rs.getInt(2)));
			dbpSeries.getData().add(new XYChart.Data<String, Integer>(rs.getTimestamp(1).toLocalDateTime().toString(), rs.getInt(3)));
			
		}
		ObservableList<Symptom> tableSymptomContent = FXCollections.<Symptom>observableArrayList(uniqueSymptom.values());
		
		tableViewSymptoms.setItems(tableSymptomContent);
		tableViewSymptomID.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableViewSymptomDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		ObservableList<Therapy> tableTherapyContent = FXCollections.<Therapy>observableArrayList(uniqueTherapies.values());
		
		tableViewTherapies.setItems(tableTherapyContent);
		tableViewTherapiesID.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableViewTherapiesDirections.setCellValueFactory(new PropertyValueFactory<>("directions"));
		tableViewTherapiesIDDrug.setCellValueFactory(new PropertyValueFactory<>("IDDrug"));
		
		ObservableList<Pathology> tablePathologyContent = FXCollections.<Pathology>observableArrayList(uniquePathologies.values());
		
		tableViewPathologies.setItems(tablePathologyContent);
		tableViewPathologiesID.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableViewPathologiesDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		
		// Set the name of the series
		sbpSeries.setName("SBP");
		dbpSeries.setName("DBP");
		
		linechartMeasurement.getData().add(sbpSeries);
		linechartMeasurement.getData().add(dbpSeries);
	}
	
	@FXML
    void datePickerMeasurementOnAction(ActionEvent event) throws SQLException {
		System.out.println("ORA");
		LocalDateTime start = datePickerMeasurementStart.getValue() == null ? null : datePickerMeasurementStart.getValue().atTime(LocalTime.now());
		LocalDateTime end = datePickerMeasurementEnd.getValue() == null ? null : datePickerMeasurementEnd.getValue().atTime(LocalTime.now());
		setLineChartMeasurement(start, end);
    }
	
	void setAllTherapies() throws SQLException {
		String q = "SELECT * FROM Therapy\n" +
				   "WHERE CFpatient='" + patient.getCF() +"';"
				
				;
		
		ResultSet rs = db.runQuery(q);
		
		ObservableList<Therapy> allTherapies = FXCollections.<Therapy>observableArrayList();
		
		while(rs.next()) {
			
			System.out.println("Directions: " + rs.getString("directions"));
			allTherapies.add(new Therapy(
					rs.getInt("id"),
					rs.getInt("dailydose"),
					rs.getInt("quantity"),
					rs.getString("directions"),
					rs.getDate("startDate").toLocalDate(),
					rs.getDate("endDate") == null ? null : rs.getDate("enddate").toLocalDate(),
					rs.getInt("IDdrug"),
					patient.getCF(),
					myPhysician.getCF()
					));
		}
		
		tableViewAllTherapies.setItems(allTherapies);
		tableViewAllTherapiesID.setCellValueFactory(new PropertyValueFactory<>("id"));

		tableViewAllTherapiesID.setEditable(true);

		tableViewAllTherapiesDailyDose.setCellValueFactory(new PropertyValueFactory<>("daily_dose"));
		tableViewAllTherapiesDailyDose.setEditable(true);
		
		tableViewAllTherapiesQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		tableViewAllTherapiesQuantity.setEditable(true);
		
		tableViewAllTherapiesStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
		tableViewAllTherapiesStartDate.setEditable(true);
		
		tableViewAllTherapiesEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
		tableViewAllTherapiesEndDate.setEditable(true);
		
		tableViewAllTherapiesDirections.setCellValueFactory(new PropertyValueFactory<>("directions"));
		tableViewAllTherapiesDirections.setEditable(true);
		
		tableViewAllTherapiesIDDrug.setCellValueFactory(new PropertyValueFactory<>("IDDrug"));
        tableViewAllTherapiesIDDrug.setEditable(true);
        tableViewAllTherapies.setEditable(true);

		
	}
	

    @FXML
    void btnInsertNewTherapyOnClicked(ActionEvent event) {
    	System.out.println("Insert New Therapy Clicked");
    }
    
    @FXML
    void bntUpdateTherapyOnClicked(ActionEvent event) {
    	SelectionModel<Therapy> selectionModel = tableViewAllTherapies.getSelectionModel();
    	
    	Therapy selectedRow = selectionModel.getSelectedItem();
    	
    	if (selectedRow == null) {
    		return;
    	}
    	
    	System.out.println("Update Therapy: " + selectedRow);
    }

    @FXML
    void btnDeleteTherapyOnClicked(ActionEvent event) {
    	System.out.println("Delete Therapy");
    }

	  

}
