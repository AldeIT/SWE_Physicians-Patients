package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.DB_Model;
import model.Measurement;
import model.MeasurementPathology;
import model.MeasurementSymptom;
import model.Patient;
import model.Symptom;

public class patientViewController {
	@FXML
	private AnchorPane root;
	
	@FXML
	private Label welcomeLabel;
	
	@FXML
	private Label relevationLabel;
	
	@FXML
	private Label drugLabel;

	private Patient session;
	
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
	private Label labelInformations;
	
	@FXML
    private Label labelPhysicianEmail;

    @FXML
    private Label labelPhysicianName;

    @FXML
    private Label labelPhysicianPhoneNumber;

    @FXML
    private Label labelPhysicianSurname;
	
	private DB_Model db;
	
	@FXML
    private TextField textFieldDBP;

    @FXML
    private TextField textFieldSBP;
    
    @FXML
    private ListView<Symptom> listViewSymptoms;
    
    @FXML
    private Button btnInsertMeasurement;
    
    @FXML
    private TextField textFieldInformations;

	public void setSession(Patient session) throws SQLException {
		this.session = new Patient(session);
		db = DB_Model.getInstance();
	}
	
	public void initInfo() throws SQLException {
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
		labelInformations.setText(session.getInformations());
		
		String q = "SELECT * FROM physician\n" +
				   "WHERE CF= '" + session.getCFPhysician() + "';";
		
		ResultSet physician = db.runQuery(q);
		
		System.out.println(physician.getString("email"));
		
		labelPhysicianName.setText(physician.getString("name"));
		labelPhysicianSurname.setText(physician.getString("surname"));
		labelPhysicianPhoneNumber.setText(physician.getString("phonenumber"));
		labelPhysicianEmail.setText(physician.getString("email"));
		
		ObservableList<Symptom> allSymptoms = db.getSymptoms();
		listViewSymptoms.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		listViewSymptoms.setItems(allSymptoms);
	}
	
	@FXML
    void btnInsertMeasurementClicked(ActionEvent event) throws ParseException, SQLException {
		int sbp = Integer.parseInt(textFieldSBP.getText());
		
		int dbp = Integer.parseInt(textFieldDBP.getText());
		
		List<Symptom> selectedSymptoms = listViewSymptoms.getSelectionModel().getSelectedItems();
		
		HashSet<Symptom> uniqueSymptoms = new HashSet<>(selectedSymptoms);
		
		LocalDateTime now = LocalDateTime.now();
		long date = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		
		String informations = textFieldInformations.getText();
		Measurement measurement = null;
		try {
			measurement = db.insertMeasurement(sbp, dbp, now, informations, session.getCF());
		}catch (SQLException e) {
			e.printStackTrace();
			//System.out.println();
			return;
		}
		String q = "SELECT id FROM Measurement\n" +
				"WHERE CFpatient ='" + session.getCF() + "' AND datetime='" + date + "';";
		ResultSet res = db.runQuery(q);
		int idMeasurement = res.getInt(1);
		int idSymptom;
		MeasurementSymptom ms = null;
		
		
		
		for (Symptom s:uniqueSymptoms) {
			q = "SELECT id FROM Symptom\n" +
					"WHERE description ='" + s.getDescription() + "';";
			res = db.runQuery(q);
			idSymptom = res.getInt(1);
			
			try {
				ms = db.insertMeasurementSymptom(idMeasurement, idSymptom);
			}catch (SQLException e) {
				e.printStackTrace();
				//System.out.println();
				return;
			}
			
		}
		
		q = "SELECT IDpathology FROM patient_pathology\n"+
			"WHERE CFpatient = '" + session.getCF() + "' AND endDate IS NULL;";
		 
		res = db.runQuery(q);
		MeasurementPathology mp = null;
		while(res.next()){
			try {
				mp = db.insertMeasurementPathology(idMeasurement, res.getInt(1));
			}catch (SQLException e) {
				e.printStackTrace();
				//System.out.println();
				return;
			}
		}
		
		

		
		
		ObservableList<Measurement> measurements = db.getMeasurements();
		
		for (Measurement m:measurements) {
			System.out.println(m);
		}
		
		ObservableList<MeasurementSymptom> measurementsSymptoms = db.getMeasurementSymptoms();
		
		for (MeasurementSymptom m:measurementsSymptoms) {
			System.out.println(m);
		}
		
		
		ObservableList<MeasurementPathology> measurementsPathologies = db.getMeasurementPathology();
		
		for (MeasurementPathology m:measurementsPathologies) {
			System.out.println(m);
		}
		
		
		
		
		
		
		
		

    }

}
