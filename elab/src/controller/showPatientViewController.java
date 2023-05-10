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
import javafx.scene.control.Alert;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import model.DB_Model;
import model.Drug;
import model.Pathology;
import model.Patient;
import model.PatientPathology;
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
    private Button btnEndTherapy;
    
    @FXML
    private Button btnUpdateTherapy;
    
    @FXML
    private TextField textFieldDailyDose;

    @FXML
    private TextField textFieldDirections;

    @FXML
    private TextField textFieldQuantity;
    
    @FXML
    private Button btnAddPathologies;

    @FXML
    private Button btnEndPathology;
    
    @FXML
    private ChoiceBox<Pathology> choiceBoxPathologies;
    
    @FXML
    private TableView<PatientPathology> tableViewMyPathologies;

    @FXML
    private TableColumn<PatientPathology, String> tableViewMyPathologiesDescription;

    @FXML
    private TableColumn<PatientPathology, LocalDate> tableViewMyPathologiesEndDate;

    @FXML
    private TableColumn<PatientPathology, Integer> tableViewMyPathologiesID;

    @FXML
    private TableColumn<PatientPathology, LocalDate> tableViewMyPathologiesStartDate;
    
    private AlertHandler alert = AlertHandler.getInstance();
    
    private HashMap<Integer, String> drugNames = new HashMap<>();

	
	LocalDateTime defaultStart;
	
	LocalDateTime defaultEnd;
	
	 @FXML
	 void btnUpdateInformationsOnClicked(ActionEvent event) throws SQLException, ParseException {
		 String content = textFieldInformations.getText();
		 
		 if (!content.equals(patient.getInformations()) && !content.isEmpty()) {
			 try {
				 db.updatePatient(patient.getCF(), content);
			 }catch (SQLException e) {
				 alert.launchAlert(Alert.AlertType.ERROR, "Database Error", "Couldn't update informations");
				 return;
			 }
			 
			 db.insertLog(myPhysician.getCF(), LocalDateTime.now(), myPhysician.getCF() + " has modified the informations of the patient: " + patient.getCF());
			 this.patient.setInformations(content);
		 }
	 }
	
	void setSession(Patient patient, Physician physician) {
		this.patient = new Patient(patient);
		this.myPhysician = new Physician (physician);
	}
	
	void setLabels() {
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
	}
	
	void initInfo() throws SQLException {
		
		try {
			db = DB_Model.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("init");
		
		setLabels();	
		
		defaultStart = LocalDateTime.of(2000, 1, 1, 9, 0, 0);
		defaultEnd = LocalDateTime.of(2040, 1, 1, 9, 0, 0);

		String q = "SELECT * FROM Drug";
		
		ResultSet st = db.runQuery(q);
		
		while(st.next()) {
			drugNames.put(st.getInt("id"), st.getString("name"));
		}
			
		textFieldDailyDose.addEventFilter(KeyEvent.KEY_TYPED, event -> {
		    String input = event.getCharacter();
		    if (!input.matches("[0-9]")) {
		        event.consume();
		    }
		});
		
		textFieldQuantity.addEventFilter(KeyEvent.KEY_TYPED, event -> {
		    String input = event.getCharacter();
		    if (!input.matches("[0-9]")) {
		        event.consume();
		    }
		});
		
		
		
		this.tabpane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
		    if (newTab.getId().equals("backToPhysician")) {
		    	
		    	/*Getting the fxml*/
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/physicianView.fxml"));
				Parent root = null;
				try {
					root = loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					alert.launchAlert(Alert.AlertType.ERROR, "Loading Error", "Error while changing view");
				}
				/*Getting the controller*/
				physicianViewController controller = loader.getController();
				controller.setSession(myPhysician);
				try {
					controller.initInfo();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					alert.launchAlert(Alert.AlertType.ERROR, "Database Error", "Error while working on the database");
				} catch (ParseException e) {
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
				this.patient = null;
		    	this.myPhysician = null;
		    }
		    if (newTab.getId().equals("measurementTab")) {
		    	try {
		    		LocalDateTime start = datePickerMeasurementStart.getValue() == null ? null : datePickerMeasurementStart.getValue().atTime(LocalTime.of(1, 0));
		    		LocalDateTime end = datePickerMeasurementEnd.getValue() == null ? null : datePickerMeasurementEnd.getValue().atTime(LocalTime.of(23, 59));
					setLineChartMeasurement(start, end);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    
		    if (newTab.getId().equals("dashboardTab")) {
		    	setLabels();
		    }
		    
		    if (newTab.getId().equals("therapiesTab")) {
		    	try {
					setAllTherapies();
					setDrugChoiceBox();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					alert.launchAlert(Alert.AlertType.ERROR, "Database Error", "Error while working on the database");
				}
		    }
		    
		    if (newTab.getId().equals("pathologiesTab")) {
		    	try {
		    		setMyPathologies();
		    		
		    		setChoiceBoxPathologies();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
	}
	
	private void setDrugChoiceBox() throws SQLException {
		String q = "SELECT * FROM Drug;";
		
		ResultSet rs = db.runQuery(q);
		
		ObservableList<Drug> allDrugs = FXCollections.observableArrayList();
		
		
		
		while(rs.next()) {
			allDrugs.add(new Drug(rs.getInt(1), rs.getString(2), rs.getString(3)));
		}
		
		choiceBoxDrug.setItems(allDrugs);
		
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
		
		if (start.isAfter(end)) {
			alert.launchAlert(Alert.AlertType.ERROR, "Date Error", "The first date should become before the second!");
			return;
		}
		
		datePickerMeasurementStart.setValue(start.toLocalDate());
		datePickerMeasurementEnd.setValue(end.toLocalDate());
		
        long timestamp = start.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long timestampNow = end.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        
		
		String q = "SELECT datetime, sbp, dbp, id FROM Measurement\n" +
				   "WHERE CFpatient='" + patient.getCF() + "' AND datetime>='" + timestamp + "' AND datetime<='" + timestampNow + "' ORDER BY datetime;"
				;
		
		ResultSet rs = db.runQuery(q);
		
		ResultSet rsSymptoms, rsTherapies, rsPathologies, rsDrugs;
		
		String getSymptoms, getTherapies, getPathologies, getDrugName; 
		
		HashMap<Integer, Symptom> uniqueSymptom = new HashMap<>();
		HashMap<Integer, Therapy> uniqueTherapies = new HashMap<>();
		HashMap<Integer, Pathology> uniquePathologies = new HashMap<>();
		Therapy temp;
		while (rs.next()) {
			System.out.println("Datetime: " + rs.getTimestamp("datetime"));
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
					temp = new Therapy(rsTherapies.getInt("id"), rsTherapies.getInt("dailydose"), rsTherapies.getInt("quantity"), rsTherapies.getString("directions") ,rsTherapies.getDate("startdate").toLocalDate(), rsTherapies.getDate("enddate")==null ? null : rsTherapies.getDate("enddate").toLocalDate(), rsTherapies.getInt("IDdrug"), rsTherapies.getString("CFpatient"), rsTherapies.getString("CFphysician"));
					temp.setDrug(drugNames.get(rsTherapies.getInt("IDdrug")));
					uniqueTherapies.put(rsTherapies.getInt("id"), temp);
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
		tableViewTherapiesIDDrug.setCellValueFactory(new PropertyValueFactory<>("drug"));
		
		ObservableList<Pathology> tablePathologyContent = FXCollections.<Pathology>observableArrayList(uniquePathologies.values());
		
		tableViewPathologies.setItems(tablePathologyContent);
		tableViewPathologiesID.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableViewPathologiesDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		linechartMeasurement.getData().clear();
		// Set the name of the series
		sbpSeries.setName("SBP");
		dbpSeries.setName("DBP");
		
		linechartMeasurement.getData().add(sbpSeries);
		linechartMeasurement.getData().add(dbpSeries);
	}
	
	@FXML
    void datePickerMeasurementOnAction(ActionEvent event) throws SQLException {
		System.out.println("ORA");
		LocalDateTime start = datePickerMeasurementStart.getValue() == null ? null : datePickerMeasurementStart.getValue().atTime(LocalTime.of(1, 0));
		LocalDateTime end = datePickerMeasurementEnd.getValue() == null ? null : datePickerMeasurementEnd.getValue().atTime(LocalTime.of(23, 59));
		

		
		
		setLineChartMeasurement(start, end);
    }
	
	void setAllTherapies() throws SQLException {
		String q = "SELECT * FROM Therapy\n" +
				   "WHERE CFpatient='" + patient.getCF() +"';"
				
				, secondQuery;

		ResultSet rs = db.runQuery(q), secondRs;
		
		ObservableList<Therapy> allTherapies = FXCollections.<Therapy>observableArrayList();
		Therapy temp = null;
		while(rs.next()) {
			secondQuery = "SELECT name FROM Drug WHERE id='" + rs.getInt("IDdrug") + "';";
			secondRs = db.runQuery(secondQuery);
			temp = new Therapy(
					rs.getInt("id"),
					rs.getInt("dailydose"),
					rs.getInt("quantity"),
					rs.getString("directions"),
					rs.getDate("startDate").toLocalDate(),
					rs.getDate("endDate") == null ? null : rs.getDate("enddate").toLocalDate(),
					rs.getInt("IDdrug"),
					patient.getCF(),
					myPhysician.getCF()
					);
			temp.setDrug(secondRs.getString(1));
			
			allTherapies.add(temp);
		}
		
		tableViewAllTherapies.setItems(allTherapies);
		tableViewAllTherapiesID.setCellValueFactory(new PropertyValueFactory<>("id"));

		tableViewAllTherapiesID.setEditable(true);

		tableViewAllTherapiesDailyDose.setCellValueFactory(new PropertyValueFactory<>("dailydose"));

		tableViewAllTherapiesDailyDose.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableViewAllTherapiesDailyDose.setEditable(true);
		
		tableViewAllTherapiesQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

		tableViewAllTherapiesQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableViewAllTherapiesQuantity.setEditable(true);
		
		tableViewAllTherapiesStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
		tableViewAllTherapiesStartDate.setEditable(true);
		
		tableViewAllTherapiesEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
		tableViewAllTherapiesEndDate.setEditable(true);
		
		tableViewAllTherapiesDirections.setCellValueFactory(new PropertyValueFactory<>("directions"));

		tableViewAllTherapiesDirections.setCellFactory(TextFieldTableCell.forTableColumn());
		tableViewAllTherapiesDirections.setEditable(true);
		
		tableViewAllTherapiesIDDrug.setCellValueFactory(new PropertyValueFactory<>("drug"));
        tableViewAllTherapiesIDDrug.setEditable(true);
        tableViewAllTherapies.setEditable(true);

		
	}
	

    @FXML
    void btnInsertNewTherapyOnClicked(ActionEvent event) throws SQLException, ParseException {
    	
    	if (textFieldDailyDose.getText().isEmpty() || textFieldQuantity.getText().isEmpty() || textFieldDirections.getText().isEmpty()) {
			alert.launchAlert(Alert.AlertType.ERROR, "Fields Error", "You need to insert all of the fields");
			return;
    	}
    	
    	int daily_dose = Integer.parseInt(textFieldDailyDose.getText());
    	int quantity = Integer.parseInt(textFieldQuantity.getText());
    	String directions = textFieldDirections.getText();
    	
    	Drug selectedItem = choiceBoxDrug.getValue();
    	
    	if (selectedItem == null) {
			alert.launchAlert(Alert.AlertType.ERROR, "Selection Error", "You need to select at least one Drug!");
    		return;
    	}
    	
    	try{
    		db.insertTherapy(daily_dose, quantity, directions, LocalDate.now(), null, selectedItem.getId(), patient.getCF(), myPhysician.getCF());
    	}catch(SQLException e) {
			alert.launchAlert(Alert.AlertType.ERROR, "Database Error", "Could not insert a new Therapy");
			setAllTherapies();
			return;
    	}
    	
    	setAllTherapies();
		alert.launchAlert(Alert.AlertType.INFORMATION, "All Done", "A new Therapy was inserted");
		textFieldDailyDose.setText("");
		textFieldQuantity.setText("");
		textFieldDirections.setText("");
		choiceBoxDrug.getSelectionModel().clearSelection();
    	
    	System.out.println("Insert New Therapy Clicked");
    }
    
    @FXML
    void bntUpdateTherapyOnClicked(ActionEvent event) throws SQLException {
    	
    	ObservableList<Therapy> allItems = tableViewAllTherapies.getItems();
    	System.out.println("Size: " + allItems.size());
    	if (allItems.size() == 0) {
			alert.launchAlert(Alert.AlertType.ERROR, "Fields Error", "No Therapies to update");
    		return;
    	}
    	String q, directions;
    	int dailyDose, quantity;
    	for (Therapy t: allItems) {
    		dailyDose = (Integer) tableViewAllTherapies.getColumns().get(1).getCellObservableValue(t).getValue();
            quantity = (Integer) tableViewAllTherapies.getColumns().get(2).getCellObservableValue(t).getValue();
            directions = (String) tableViewAllTherapies.getColumns().get(3).getCellObservableValue(t).getValue();
    		q = "UPDATE Therapy SET dailydose='" + dailyDose + "', quantity='" + quantity + "', directions='" + directions + "' WHERE id='" + t.getID() + "';";
    		db.runStatement(q);
    		
    		System.out.println("Modified : " + dailyDose + ",   " + quantity);
    	}
    	
    	
    	
    	
    	setAllTherapies();
    }

    @FXML
    void btnEndTherapyOnClicked(ActionEvent event) throws SQLException, ParseException {
    	
    	SelectionModel<Therapy> selectionModel = tableViewAllTherapies.getSelectionModel();
    	
    	Therapy selectedRow = selectionModel.getSelectedItem();
    	
    	if (selectedRow == null) {
			alert.launchAlert(Alert.AlertType.ERROR, "Fields Error", "No Therapy to end");
    		return;
    	}
    	
    	int idDelete = selectedRow.getID();
    	
    	LocalDate now = LocalDate.now();
    	
    	Long timestamp = db.LocalDateToLong(now);
    	
    	String q = "UPDATE Therapy SET endDate='" + timestamp + "' WHERE id='" + idDelete + "';";
    	
    	db.runStatement(q);
    	
    	System.out.println("Delete Therapy: " + selectedRow);
    	setAllTherapies();
    }
    
    void setMyPathologies() throws SQLException {
    	String q = "SELECT IDpathology, description, startDate, endDate FROM patient_pathology INNER JOIN pathology on pathology.id=patient_pathology.IDpathology \n" +
    				"WHERE CFpatient='" + patient.getCF() + "';"
    			;
    	
    	ResultSet rs = db.runQuery(q);
		
		ObservableList<PatientPathology> allMyPathologies = FXCollections.<PatientPathology>observableArrayList();
		
		while(rs.next()) {
			
			allMyPathologies.add(new PatientPathology(
					rs.getTimestamp("startDate").toLocalDateTime().toLocalDate(),
					rs.getTimestamp("endDate") == null ? null : rs.getTimestamp("endDate").toLocalDateTime().toLocalDate(),
					patient.getCF(),
					rs.getInt("IDpathology"),
					rs.getString("description")
					));
		}
		
		tableViewMyPathologies.setItems(allMyPathologies);
		tableViewMyPathologiesDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		tableViewMyPathologiesStartDate.setCellValueFactory(new PropertyValueFactory<>("startdate"));
		tableViewMyPathologiesEndDate.setCellValueFactory(new PropertyValueFactory<>("enddate"));
    	
    	
    }
    
    void setChoiceBoxPathologies() throws SQLException {
    	String q = "SELECT * FROM Pathology;";
		
		ResultSet rs = db.runQuery(q);
		
		ObservableList<Pathology> allPathologies = FXCollections.observableArrayList();
		
		
		
		while(rs.next()) {
			allPathologies.add(new Pathology(rs.getInt(1), rs.getString(2)));
		}
		
		choiceBoxPathologies.setItems(allPathologies);
    }

    @FXML
    void btnAddPathologiesOnClicked(ActionEvent event) throws SQLException {
    	
    	Pathology selectedItem = choiceBoxPathologies.getValue();
    	
    	if (selectedItem == null) {
			alert.launchAlert(Alert.AlertType.ERROR, "Fields Error", "No Pathology Selected");
    		return;
    	}
    	
    	try {
			db.insertPatientPathology(patient.getCF(), selectedItem.getID(), LocalDate.now());
		} catch (SQLException e) {
			
		} catch (ParseException e) {
		}
    	
    	System.out.println("ZIOBOIA");
    	setMyPathologies();
    }

    @FXML
    void btnEndPathologyOnClicked(ActionEvent event) throws ParseException, SQLException {
    	
    	SelectionModel<PatientPathology> selectionModel = tableViewMyPathologies.getSelectionModel();
    	
    	PatientPathology selectedRow = selectionModel.getSelectedItem();
    	
    	if (selectedRow == null) {
			alert.launchAlert(Alert.AlertType.ERROR, "Fields Error", "No Pathology to End");
    		return;
    	}
    	
    	int idDelete = selectedRow.getIdPathology();
    	
    	LocalDate now = LocalDate.now();
    	
    	Long timestamp = db.LocalDateToLong(now);
    	
    	String q = "UPDATE patient_pathology SET endDate='" + timestamp + "' WHERE IDpathology='" + idDelete + "' AND endDate IS NULL;";
    	
    	db.runStatement(q);
    	
    	System.out.println("Delete PatientPathology: " + selectedRow);
    	setMyPathologies();
    	System.out.println("ZIOSANTO");
    } 
    
    

}
