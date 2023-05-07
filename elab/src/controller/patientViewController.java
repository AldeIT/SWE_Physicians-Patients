package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.DB_Model;
import model.Measurement;
import model.MeasurementPathology;
import model.MeasurementSymptom;
import model.MeasurementTherapy;
import model.Patient;
import model.Symptom;
import model.Therapy;

public class patientViewController {
	@FXML
	private AnchorPane root;
	
	@FXML
	private Label welcomeLabel;
	
	@FXML
	private Label relevationLabel;

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
    private ListView<Therapy> listViewCurrentTherapies;
    
    @FXML
    private Button btnInsertMeasurement;
    
    @FXML
    private Button btnInsertIntake;
    
    @FXML
    private TextField textFieldInformations;
    
    @FXML
    private TextField textFieldQuantity;
    
    @FXML
    private TabPane tabpane;

    
    /**
     * Sets the session for the controller
     *
     * @param the patient who has authenticated.
     * @throws SQLException if there are any problems getting the instance of the db model.
     */
	public void setSession(Patient session) throws SQLException {
		this.session = new Patient(session);
		db = DB_Model.getInstance();
	}
	
	/**
	 * Initializes a lot of informations, session's labels, listview's content...
	 * @throws IllegalArgumentException if either width or height is negative or zero.
	 */
	public void initInfo() throws SQLException {
		System.out.println("init");
		
		setLabelsInformations();
		/**/
		setCurrentSymptoms();
		/**/
		setCurrentTherapies();
		
		this.tabpane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
		    if (newTab.getId().equals("profileTab")) {
		        System.out.println("HERE");
		    }
		});
		
		
	}
	
	/**
	 * Calculates the area of a rectangle with the given width and height.
	 *
	 * @param width The width of the rectangle.
	 * @param height The height of the rectangle.
	 * @return The area of the rectangle.
	 * @throws IllegalArgumentException if either width or height is negative or zero.
	 */
	void setLabelsInformations() throws SQLException {
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
	}
	
	void setCurrentSymptoms() throws SQLException {
		ObservableList<Symptom> allSymptoms = db.getSymptoms();
		listViewSymptoms.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		listViewSymptoms.setItems(allSymptoms);
	}
	
	void setCurrentTherapies() throws SQLException {
		ObservableList<Therapy> currentTherapies = FXCollections.<Therapy>observableArrayList(
                therapy -> new Observable[] {
                        therapy.idProperty(), 
                        therapy.dailydoseProperty(),
                        therapy.quantityProperty(),
                        therapy.directionsProperty(),
                        therapy.startDateProperty(),
                        therapy.endDateProperty(),
                        therapy.IDDrugProperty(),
                        therapy.CFPatientProperty(),
                        therapy.CFPhysicianProperty()
                        }
        );
		
		String q = "SELECT * FROM Therapy\n"+
				   "WHERE CFpatient='" + session.getCF() + "' AND endDate IS NULL;";
		ResultSet rs = db.runQuery(q);
		ResultSet rs2, rs3;
		int max;
		LocalDate today = LocalDate.now();
    	LocalDateTime startOfDay = LocalDateTime.of(today, LocalTime.MIDNIGHT);
    	LocalDateTime endOfDay = LocalDateTime.of(today, LocalTime.MAX);
    	long startDay = startOfDay.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    	long endDay = endOfDay.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    	Therapy temp = null;
		
		while(rs.next()) {
			max = rs.getInt("dailydose") * rs.getInt("quantity");
			
			q = "SELECT SUM(quantity), COUNT(quantity) FROM drugIntakes\n" +
					"WHERE IDtherapy='" + rs.getInt("id") + "' AND datetime>='" + startDay + "' AND datetime<='" + endDay+ "';";
				
				
				
			rs2 = db.runQuery(q);
				
			if (rs2.getInt(1)<max) {
				temp = new Therapy(
						rs.getInt("id"),
						rs.getInt("dailydose"),
						rs.getInt("quantity"),
						rs.getString("directions"),
						rs.getDate("startDate").toLocalDate(),
						null,
						rs.getInt("IDdrug"),
						rs.getString("CFpatient"),
						rs.getString("CFphysician")
						);
				
				q = "SELECT name FROM drug\n" +
					"WHERE id='" + temp.getIDDrug() + "';";
				
				rs3 = db.runQuery(q);
				
				temp.setDailyDoseRemaining(temp.getDailydose() - rs2.getInt(2));
				temp.setDrugName(rs3.getString(1));
				temp.setTotalQuantityRemaining(max - rs2.getInt(1));
				if (temp.getDailyDoseRemaining() == 0 && temp.getTotalQuantityRemaining() != 0) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
			        alert.setTitle("Warning Daily Doses");
			        alert.setHeaderText("You have already taken pills for your daily dose indications, but you did something wrong because you probably missed some pills, pls contact your medic!");
			        alert.showAndWait();
				}
				if (max - rs2.getInt(1) < temp.getQuantity()) {
					temp.setQuantityRemaining(max - rs2.getInt(1));
				}
				
				currentTherapies.add(temp);
			}
			
			
			
		}
		
		
    	
    	
		/*for (Therapy p: currentTherapies) {
			max = p.getQuantity() * p.getDailyDose();
			q = "SELECT SUM(quantity) FROM drugIntakes\n" +
				"WHERE IDtherapy='" + p.getID() + "' AND datetime>='" + startDay + "' AND datetime<='" + endDay+ "';";
			
			
			
			rs = db.runQuery(q);
			
			if (rs.getInt(1)>=max) {
				currentTherapies.remove(p);
			}
		}*/
		
		/*for (Therapy t:currentTherapies) {
			System.out.println(t);
		}*/
		
		listViewCurrentTherapies.setItems(currentTherapies);
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
		
		q = "SELECT id FROM Therapy\n"+
				"WHERE CFpatient = '" + session.getCF() + "' AND endDate IS NULL;";
			 
			res = db.runQuery(q);
			MeasurementTherapy mt = null;
			while(res.next()){
				try {
					mt = db.insertMeasurementTherapy(idMeasurement, res.getInt(1));
				}catch (SQLException e) {
					e.printStackTrace();
					//System.out.println();
					return;
				}
			}
			
	    listViewSymptoms.getSelectionModel().clearSelection();


		
		
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
		
		ObservableList<MeasurementTherapy> measurementsTherapies = db.getMeasurementTherapies();
		
		for (MeasurementTherapy m:measurementsTherapies) {
			System.out.println(m);
		}
		
		
		
    }
	
	@FXML
    void btnInsertIntakeOnClicked(ActionEvent event) throws SQLException {
		
		MultipleSelectionModel<Therapy> selectionModel = listViewCurrentTherapies.getSelectionModel();

		
		Therapy selected = selectionModel.getSelectedItem();
		
		if (selected == null) {
			//non ha selezionato
			return;
		}
		
		if (textFieldQuantity.getText().equals("")) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Error Quantity");
	        alert.setHeaderText("You need to insert a quantity!");
	        alert.showAndWait();
	        return;
		}
		
		if (Integer.parseInt(textFieldQuantity.getText()) > selected.getQuantityRemaining()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Warning Quantity");
	        alert.setHeaderText("You have taken too much!");
	        alert.showAndWait();
		}
		
		try {
			db.insertDrugIntake(0, LocalDateTime.now(), Integer.parseInt(textFieldQuantity.getText()), selected.getID());
		}catch(SQLException e) {
			e.printStackTrace();
			//System.out.println();
			return;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		setCurrentTherapies();
		textFieldQuantity.setText("");
		System.out.println("Intake Button Clicked");
    }
	

}
