package controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DB_Model;
import model.Measurement;
import model.MeasurementPathology;
import model.MeasurementSymptom;
import model.MeasurementTherapy;
import model.Patient;
import model.Symptom;
import model.Therapy;


/**
 *The controller class for the Patient view
 */
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


    @FXML
    private Tab drugTab;
    
    private AlertHandler alert = AlertHandler.getInstance();
    
    @FXML
    private Button contactPhysician;

    
    /**
     * Sets the session for the controller
     *
     * @param session the patient who has authenticated.
     */
	public void setSession(Patient session)  {
		this.session = new Patient(session);
		
		try {
			db = DB_Model.getInstance();
		} catch (SQLException e) {
			alert.launchAlert(Alert.AlertType.ERROR, "Database Error", "There was an error accessing to the database, going to kill the application");
			Platform.exit();
		}
	}
	
	/**
	 * Initializes a lot of informations, session's labels, listview's content...
	 * @throws SQLException if there's an error while retrieving data from the database
	 * @throws IllegalArgumentException if either width or height is negative or zero.
	 */
	@FXML
	public void initInfo() throws SQLException {
		System.out.println("init");
		
		setLabelsInformations();
		
		/**/
		try {
			setCurrentTherapies();
		}catch(SQLException e) {
			alert.launchAlert(Alert.AlertType.ERROR, "Database Error", "There was an error accessing to the database");
		}
		
		
		this.tabpane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
		    
		    
		    if (newTab.getId().equals("measurementTab")) {
		    	try {
					setCurrentSymptoms();
				} catch (SQLException e) {
					alert.launchAlert(Alert.AlertType.ERROR, "Database Error", "There was an error accessing to the database");
				}
		    }
		    
		    if (newTab.getId().equals("drugTab")) {
		    	try {
					setCurrentTherapies();
				} catch (SQLException e) {
					alert.launchAlert(Alert.AlertType.ERROR, "Database Error", "There was an error accessing to the database");
				}
		    }
		    
		    if (newTab.getId().equals("backToLogin")) {
		    	this.session = null;
		    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/login.fxml"));
				Parent root = null;
				try {
					root = loader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("Switchamo Scene");
				/*Setting the scene*/
				Scene scene = new Scene(root);
				Stage stage = (Stage) tabpane.getScene().getWindow();
				stage.setScene(scene);
				stage.setMinWidth(500);
		        stage.setMinHeight(600);
		        stage.setResizable(true);
				stage.show();
		    }
		});
		

		textFieldQuantity.addEventFilter(KeyEvent.KEY_TYPED, event -> {
		    String input = event.getCharacter();
		    if (!input.matches("[0-9]")) {
		        event.consume();
		    }
		});
		
		textFieldSBP.addEventFilter(KeyEvent.KEY_TYPED, event -> {
		    String input = event.getCharacter();
		    if (!input.matches("[0-9]")) {
		        event.consume();
		    }
		});
		
		textFieldDBP.addEventFilter(KeyEvent.KEY_TYPED, event -> {
		    String input = event.getCharacter();
		    if (!input.matches("[0-9]")) {
		        event.consume();
		    }
		});
		
		

		
		
	}
	
    /**
	 * This method is called when the user clicks the "Contact Physician" button.
	 * It retrieves the email address of the physician associated with the current session from the database,
	 * and creates a mailto: URI with the recipient, subject, and body.
	 * Then, it opens the default mail application with the mailto: URI to allow the user to send an email to the physician.
	 *	
	 * @param event the event triggered by the user clicking the "Contact Physician" button
	 * @throws SQLException if there is an error retrieving the email address of the physician from the database
	 */
	@FXML
    void contactPhysicianOnClicked(ActionEvent event) throws SQLException {
    	String q = "SELECT email FROM Physician WHERE CF='" + session.getCFPhysician() + "';";
    	ResultSet rs = db.runQuery(q);
    	
    	String recipient = rs.getString(1);
        String subject = "";
        String body = "";
        try {
            // Create a mailto: URI with the recipient, subject, and body
            URI mailtoUri = new URI("mailto:" + recipient +
                                    "?subject=" + subject +
                                    "&body=" + body);
             // Open the default mail application with the mailto: URI
            Desktop.getDesktop().mail(mailtoUri);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * This method retrieves from the database the therapies of the patient that have not yet ended, and check if the drugs have been taken for the current day.
	 * If not, it creates a warning message and launches it as an alert.
	 *
	 * @throws SQLException if an error occurs while retrieving data from the database
	 */
	@FXML
	public void firstAlert() throws SQLException {
		
		String q = "SELECT * FROM Therapy\n"
				+ "WHERE CFpatient='" + session.getCF()
				+ "' AND endDate IS NULL";
		
		ResultSet rs;
		try {
			rs = db.runQuery(q); 
		}catch(SQLException e) {
			return;
		}
		ResultSet rs2, rs3;
		
		String queryName, countIntakes, nameDrug;
		
		LocalDate today = LocalDate.now();
    	LocalDateTime startOfDay = LocalDateTime.of(today, LocalTime.MIDNIGHT);
    	LocalDateTime endOfDay = LocalDateTime.of(today, LocalTime.MAX);
    	long startDay = startOfDay.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    	long endDay = endOfDay.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		int taken;
		String message = "You still have to take\n";
		int count = 0;
		while(rs.next()) {
			count++;
			queryName = "SELECT name FROM Drug WHERE id='" + rs.getInt("IDdrug") + "';";
			rs2 = db.runQuery(queryName);
			nameDrug = rs2.getString(1);
			countIntakes = "SELECT SUM(quantity) FROM drugIntakes\n" +
					"WHERE IDtherapy='" + rs.getInt("id") + "' AND datetime>='" + startDay + "' AND datetime<='" + endDay+ "';";
			rs3 = db.runQuery(countIntakes);
			taken = rs3.getInt(1);
			if (taken < rs.getInt("dailydose") * rs.getInt("quantity")) {
				message += nameDrug + ", Quantity Remaining: " + (rs.getInt("dailydose") * rs.getInt("quantity") - taken) + "\n";
			}
		 
		}
		
		if (count!=0)alert.launchAlert(Alert.AlertType.WARNING, "Warning Daily Doses", message);	
	}
	
	
	
	/**
	 * This method sets the information labels in the UI to display the information of the logged in physician. It gets the
 	 * information from the current session object and from the database by running a query. It sets the text of the	
	 * labels with the retrieved information. If an exception occurs during the query, it sets the labels to "error".
	 *
	 * @throws SQLException If an error occurs during the database query.
	 */
	@FXML
	void setLabelsInformations() throws SQLException {
		labelCF.setText(session.getCF());
		labelName.setText(session.getName());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = session.getBirthdate().format(formatter);		
		labelBirthdate.setText(formattedDate);	
		labelCivicNumber.setText(session.getStreet() + " " + session.getCivicNumber() + ", " + session.getCAP() + " " + session.getCity());
		labelSurname.setText(session.getSurname());
		labelPhoneNumber.setText(session.getPhoneNumber());
		labelSex.setText(session.getSex());
		labelNationality.setText(session.getNationality());
		labelEmail.setText(session.getEmail());
		//labelInformations.setText(session.getInformations());
		
		String q = "SELECT * FROM physician\n" +
				   "WHERE CF= '" + session.getCFPhysician() + "';";
		
		ResultSet physician = null;
		try {
			physician = db.runQuery(q);
		}catch(SQLException e) {
			labelPhysicianName.setText("error");
			labelPhysicianSurname.setText("error");
			labelPhysicianPhoneNumber.setText("error");
			labelPhysicianEmail.setText("error");
			return;
		}
		 
		
		System.out.println(physician.getString("email"));
		
		labelPhysicianName.setText(physician.getString("name"));
		labelPhysicianSurname.setText(physician.getString("surname"));
		labelPhysicianPhoneNumber.setText(physician.getString("phonenumber"));
		labelPhysicianEmail.setText(physician.getString("email"));
	}
	
	
	/**
	 * Sets the current symptoms in the symptom list view.
	 * Uses the database to retrieve all available symptoms.
	 * Sets the selection mode of the list view to MULTIPLE and
	 * populates it with the retrieved symptoms.
	 *
	 * @throws SQLException if there is an error retrieving the symptoms from the database
	 */
	@FXML
	void setCurrentSymptoms() throws SQLException {
		ObservableList<Symptom> allSymptoms = db.getSymptoms();
		listViewSymptoms.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		listViewSymptoms.setItems(allSymptoms);
	}
	
	/**
	 * Set the current therapies for the patient by querying the database and adding them to an observable list,
	 * and then setting the list view to show these therapies. If a therapy's daily dose is not fully taken for the day,
	 * it will be added to the list with the remaining daily dose and the total quantity remaining. If the patient has
	 * taken all their daily doses but missed some pills, an error alert will be displayed.
	 *
	 * @throws SQLException if there is an error executing the SQL query to retrieve the current therapies from the database
     */
	@FXML
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
				temp.setDrug(rs3.getString(1));
				temp.setTotalQuantityRemaining(max - rs2.getInt(1));
				if (temp.getDailyDoseRemaining() == 0 && temp.getTotalQuantityRemaining() != 0) {
					alert.launchAlert(Alert.AlertType.ERROR, "Warning Daily Doses", "You have already taken pills for your daily dose indications, but you did something wrong because you probably missed some pills, pls contact your medic!");

				}
				if (max - rs2.getInt(1) < temp.getQuantity()) {
					temp.setQuantityRemaining(max - rs2.getInt(1));
				}
				
				currentTherapies.add(temp);
			}
			
		}
		
		listViewCurrentTherapies.setItems(currentTherapies);
	}
	
	/**
	 * This method is called when the "Insert Measurement" button is clicked.
	 * It validates the input fields, inserts a new measurement record into the database,
	 * and updates the MeasurementSymptom, MeasurementPathology, and MeasurementTherapy tables accordingly.
	 *
	 * @param event The ActionEvent triggered by clicking the "Insert Measurement" button.
	 * @throws ParseException If the date/time cannot be parsed from the system time zone.
	 * @throws SQLException If there is an error with the database connection or query.
	 */
	@FXML
    void btnInsertMeasurementClicked(ActionEvent event) throws ParseException, SQLException {
		
		if (textFieldSBP.getText().isEmpty() || textFieldDBP.getText().isEmpty()) {
			alert.launchAlert(Alert.AlertType.ERROR, "Fields Error", "You need to insert values in the label");
			return;
		}
		
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
			alert.launchAlert(Alert.AlertType.ERROR, "Database Error", "Could not insert the new record");
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
				alert.launchAlert(Alert.AlertType.ERROR, "Database Error", "Could not insert the new symptom");
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
				alert.launchAlert(Alert.AlertType.ERROR, "Database Error", "Could not insert the new pathology reference");
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
					alert.launchAlert(Alert.AlertType.ERROR, "Database Error", "Could not insert the new therapy reference");
					//System.out.println();
					return;
				}
			}
			
		alert.launchAlert(Alert.AlertType.INFORMATION, "All Done", "Insert Completed");
	
	    listViewSymptoms.getSelectionModel().clearSelection();

	    textFieldSBP.setText("");
	    textFieldDBP.setText("");
	    textFieldInformations.setText("");
		
		
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
	
	
	/**
	 * This method is called when the user clicks on the Insert Intake button.
	 * It gets the selected item from the current therapies list view and inserts a new drug intake into the database
	 * with the selected item's ID and the quantity entered in the quantity text field.
	 * If no item is selected, or if the quantity text field is empty, or if the quantity entered is more than the remaining quantity,
	 * appropriate error messages are displayed.
	 *	
	 * @param event the event that triggered the method call
	 * @throws SQLException if there is an error while inserting the drug intake into the database
	 */
	@FXML
    void btnInsertIntakeOnClicked(ActionEvent event) throws SQLException {
		
		MultipleSelectionModel<Therapy> selectionModel = listViewCurrentTherapies.getSelectionModel();

		
		Therapy selected = selectionModel.getSelectedItem();
		
		if (selected == null) {
			alert.launchAlert(Alert.AlertType.ERROR, "Selection Error", "You have not selected a therapy, please select one!");
			return;
		}
		
		if (textFieldQuantity.getText().equals("")) {
			alert.launchAlert(Alert.AlertType.ERROR, "Error Quantity Field", "Quantity input is empty!");
			
	        return;
		}
		
		if (Integer.parseInt(textFieldQuantity.getText()) > selected.getQuantityRemaining()) {
			alert.launchAlert(Alert.AlertType.ERROR, "Watch out!", "You have taken more drugs that you should have, please contact you Physician");

		}
		
		try {
			db.insertDrugIntake(0, LocalDateTime.now(), Integer.parseInt(textFieldQuantity.getText()), selected.getID());
		}catch(SQLException e) {
			alert.launchAlert(Alert.AlertType.ERROR, "Database Error", "Could not insert the new symptom");
			//System.out.println();
			return;
		} catch (ParseException e) {
			e.printStackTrace();
			return;
		}
		setCurrentTherapies();
		textFieldQuantity.setText("");
		System.out.println("Intake Button Clicked");
    }
	
}
