package model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;




//Singleton Instance
public class DB_Model {
	
	/*Printing Logs*/
	public void log(Object o){
        System.out.println(o);
    }
	
	/*Class Attributes*/
	private static DB_Model single_instance = null;
    private Connection conn;
    
    /*Connects to the db*/
    public void connect() throws SQLException {

        String url = "jdbc:sqlite:src/model/table.db";
        conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("PRAGMA foreign_keys = ON;");
        System.out.println("Connection to SQLite has been established.");

    }
	
    /*Checks if a table exists
     * True on Success, False otherwise*/
    public boolean tableExists(String table_name) throws SQLException {
        String q = "SELECT * FROM sqlite_master WHERE tbl_name = '" + table_name + "'";
        log(q);
        ResultSet rs = runQuery(q);
        return rs.next();
    }
    
    /*Performs a query and returns the ResultSet*/
    public ResultSet runQuery(String q) throws SQLException {
    	log(q);
        ResultSet rs = null;
        Statement stmt  = conn.createStatement();
        rs = stmt.executeQuery(q);
        return rs;

    }
    
    /*Performs a statement*/
    public void runStatement(String s) throws SQLException {

        Statement stmt  = conn.createStatement();
        stmt.executeUpdate(s);
    }
    
    /*Creates tables if necessary and inserts first Physicians/Patients records*/
    private DB_Model() throws SQLException
    {
        connect();
        clearAll();
        if (tableExists("physician"))
        {
            log("physician table exists");
        }else {
            log("physician table DO NOT exists");
            resetPhysicianTable();
        };
        
        if (tableExists("log"))
        {
            log("log table exists");
        }else {
            log("log table DO NOT exists");
            resetLogTable();
        };
       
        if (tableExists("patient"))
        {
            log("patient table exists");
        }else {
            log("patient table DO NOT exists");
            resetPatientTable();
        };
        
        if (tableExists("drug"))
        {
            log("drug table exists");
        }else {
            log("drug table DO NOT exists");
            resetDrugTable();
        };
        
        if (tableExists("therapy"))
        {
            log("therapy table exists");
        }else {
            log("therapy table DO NOT exists");
            resetTherapyTable();
        };
        
        if (tableExists("drugIntakes"))
        {
            log("drugIntakes table exists");
        }else {
            log("drugIntakes table DO NOT exists");
            resetDrugIntakesTable();
        };
        
        if (tableExists("pathology"))
        {
            log("pathology table exists");
        }else {
            log("pathology table DO NOT exists");
            resetPathologyTable();
        };
        
        if (tableExists("patient_pathology"))
        {
            log("patient_pathology table exists");
        }else {
            log("patient_pathology table DO NOT exists");
            resetPatientPathologyTable();
        };
        
        if (tableExists("measurement"))
        {
            log("measurement table exists");
        }else {
            log("measurement table DO NOT exists");
            resetMeasurementTable();
        };
        
        if (tableExists("symptom"))
        {
            log("symptom table exists");
        }else {
            log("symptom table DO NOT exists");
            resetSymptomTable();
        };
        
        if (tableExists("measurement_symptom"))
        {
            log("measurement_symptom table exists");
        }else {
            log("measurement_symptom table DO NOT exists");
            resetMeasurementSymptomTable();
        };
        
        if (tableExists("measurement_pathology"))
        {
            log("measurement_symptom table exists");
        }else {
            log("measurement_symptom table DO NOT exists");
            resetMeasurementPathologiesTable();
        };
        
        if (tableExists("measurement_therapy"))
        {
            log("measurement_symptom table exists");
        }else {
            log("measurement_symptom table DO NOT exists");
            resetMeasurementTherapiesTable();
        };
        

        deleteDataFromTable("measurement_therapy");
        deleteDataFromTable("measurement_pathology");
        deleteDataFromTable("measurement_symptom");
        deleteDataFromTable("symptom");
        deleteDataFromTable("measurement");
        deleteDataFromTable("patient_pathology");
        deleteDataFromTable("pathology");
        deleteDataFromTable("drugIntakes");
        deleteDataFromTable("therapy");
        deleteDataFromTable("drug");
        deleteDataFromTable("log");
        deleteDataFromTable("patient");
        deleteDataFromTable("physician");
        
        
        
        /*System.out.println("Physicians: ");
        ObservableList<Physician> allPhys = getPhysicians();
        for (Physician p: allPhys) {
        	System.out.println(p);
        }
        
        System.out.println("Patients: ");
        ObservableList<Patient> allPat = getPatients();
        for (Patient p: allPat) {
        	System.out.println(p);
        }*/
        
        
        try {
			Physician p = insertPhysician("LDGLSN02S18F861T", "alealde012@gmail.com", "password", "Alessandro", "Aldegheri", "M", LocalDate.of(2002, 11, 18), "Italian", "Danieli", 21, 37141, "Verona", "3497086640");
			Physician p1 = insertPhysician("LDGLSN02S18F861Z", "alealde012@gmail.com", "password", "Alessandro", "Aldegheri", "M", LocalDate.of(2002, 11, 18), "Italian", "Danieli", 21, 37141, "Verona", "3497086640");
			Physician p2 = insertPhysician("LDGLSN02S18F861I", "alealde012@gmail.com", "password", "Alessandro", "Aldegheri", "M", LocalDate.of(2002, 11, 18), "Italian", "Danieli", 21, 37141, "Verona", "3497086640");
			
			Patient pat = insertPatient("VNTDVD02D17L949I", "venturi.davide17@gmail.com", "password", "Davide", "Venturi", "M", LocalDate.of(2002,04,17), "Italian", "Marconi", 89, 37060, "Verona", "3402938423", "Ansia", "LDGLSN02S18F861T");
			Patient pat1 = insertPatient("VNTDVD02D17L949Z", "venturi.davide17@gmail.com", "password", "Davide", "Venturi", "M", LocalDate.of(2002,04,17), "Italian", "Marconi", 89, 37060, "Verona", "3402938423", "Ansia", "LDGLSN02S18F861T");
			Patient pat2 = insertPatient("VNTDVD02D17L949T", "venturi.davide17@gmail.com", "password", "Davide", "Venturi", "M", LocalDate.of(2002,04,17), "Italian", "Marconi", 89, 37060, "Verona", "3402938423", "Ansia", "LDGLSN02S18F861T");
            Patient pat3 = insertPatient("ZRMNCL02S19L781E", "nico.zerman@gmail.com", "password", "Nicolò", "Zerman", "M", LocalDate.of(2002,11,19), "Italian", "Gaetano Tortelli", 29, 37059, "Verona", "3274537294", "Sempre rotto", "LDGLSN02S18F861T");
            Patient pat4 = insertPatient("CRZMRA63R09L781S", "mario.crozza@gmail.com", "password", "Mario", "Crozza", "M", LocalDate.of(2009,05,11), "Italian", "Tigli", 29, 37059, "Verona", "3274537294", "Sempre rotto", "LDGLSN02S18F861T");
			Patient pat5 = insertPatient("FRNFRC96C15F205E", "federico.fiorini@gmail.com", "password", "Federico", "Fiorini", "M", LocalDate.of(1996,03,21), "Italian", "Gaetano Tortelli", 29, 37059, "Verona", "3274537294", "Sempre rotto", "LDGLSN02S18F861T");
            
			Log log = insertLog("LDGLSN02S18F861T",LocalDateTime.of(2015, 5, 1, 14, 30, 0),  "Ha modificato la descrizione di un paziente");
			Log log1 = insertLog("LDGLSN02S18F861I",LocalDateTime.of(2016, 6, 2, 15, 30, 0),  "Ha modificato il nome di un paziente");
			Log log2 = insertLog("LDGLSN02S18F861I",LocalDateTime.of(2016, 6, 2, 15, 30, 10),  "Ha modificato la mail di un paziente");
			
			Drug drug = insertDrug(0, "Paracetamolo", "antidolorifico");
			Drug drug1 = insertDrug(0, "Xanax", "antidepressivo");
			Drug drug2 = insertDrug(0, "En", "anseolitico");
			
			Therapy therapy = insertTherapy(1, 1, "una volta al giorno", LocalDate.of(2015, 6, 6), null, 1, "VNTDVD02D17L949I", "LDGLSN02S18F861T");
			Therapy therapy1 = insertTherapy(2, 1, "due volte al giorno", LocalDate.of(2015, 5, 1), null, 2, "VNTDVD02D17L949I", "LDGLSN02S18F861Z");
			Therapy therapy2 = insertTherapy(2, 2, "blablabla", LocalDate.of(2019, 11, 15), null, 3, "VNTDVD02D17L949I", "LDGLSN02S18F861I");
			
			DrugIntake drugIntake = insertDrugIntake(0, LocalDateTime.now(), 1, 1);
			DrugIntake drugIntake1 = insertDrugIntake(0, LocalDateTime.of(2020, 1, 1, 15, 0, 0), 2, 2);
			//DrugIntake drugIntake2 = insertDrugIntake(0, LocalDateTime.now(), 2, 3);
			//DrugIntake drugIntake3 = insertDrugIntake(0, LocalDateTime.now(), 1, 3);
			
			Pathology pathology = insertPathology("sla");
			Pathology pathology1 = insertPathology("monucleosi");
			Pathology pathology2 = insertPathology("pressione bassa");
			
			PatientPathology patient_pathology = insertPatientPathology("VNTDVD02D17L949T", 1, LocalDate.of(2015, 5, 1), null);
			PatientPathology patient_pathology1 = insertPatientPathology("VNTDVD02D17L949T", 2, LocalDate.of(2015, 5, 1), null);
			PatientPathology patient_pathology2 = insertPatientPathology("VNTDVD02D17L949T", 3, LocalDate.of(2015, 5, 1), LocalDate.of(2016, 6, 5));
			
			Measurement measurement = insertMeasurement(120, 90, LocalDateTime.of(2014, 11, 18, 15, 0, 0), "speriamo tutto bene", "VNTDVD02D17L949I");
			Measurement measurement1 = insertMeasurement(210, 90, LocalDateTime.of(2015, 5, 1, 15, 0, 0), "segala >>>>", "VNTDVD02D17L949T");
			Measurement measurement2 = insertMeasurement(120, 90, LocalDateTime.of(2002, 4, 17, 15, 0, 0), "blabla", "VNTDVD02D17L949Z");
			
			Symptom symptom = insertSymptom("crampi");
			Symptom symptom1 = insertSymptom("congestione nasale");
			Symptom symptom2 = insertSymptom("mal di gomito");
			
			
			MeasurementSymptom measurementsymptom = insertMeasurementSymptom(1, 3);
			MeasurementSymptom measurementsymptom1 = insertMeasurementSymptom(2, 2);
			MeasurementSymptom measurementsymptom2 = insertMeasurementSymptom(3, 1);
			
        } catch (SQLException e) {
			e.printStackTrace();
        	System.out.println("Problema nel db");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
        
        System.out.println("Physicians: ");
        ObservableList<Physician> allPhys = getPhysicians();
        for (Physician p: allPhys) {
        	System.out.println(p);
        }
        
        
        System.out.println("Patients: ");
        ObservableList<Patient> allPat = getPatients();
        for (Patient p: allPat) {
        	System.out.println(p);
        }
        
        System.out.println("Logs: ");
        ObservableList<Log> logs = getLogs();
        for (Log l: logs) {
        	System.out.println(l);
        }
        
        System.out.println("Drugs: ");
        ObservableList<Drug> drugs = getDrugs();
        for (Drug l: drugs) {
        	System.out.println(l);
        }
        
        System.out.println("Therapies: ");
        ObservableList<Therapy> therapies = getTherapies();
        for (Therapy l: therapies) {
        	System.out.println(l);
        }
        
        System.out.println("Drug Intakes: ");
        ObservableList<DrugIntake> drugIntakes = getDrugIntakes();
        for (DrugIntake d: drugIntakes) {
        	System.out.println(d);
        }
        
        System.out.println("Pathologies: ");
        ObservableList<Pathology> pathologies = getPathologies();
        for (Pathology p: pathologies) {
        	System.out.println(p);
        }
        
        System.out.println("Patient_Pathologies: ");
        ObservableList<PatientPathology> patient_pathologies = getPatientPathologies();
        for (PatientPathology p: patient_pathologies) {
        	System.out.println(p);
        }
        
        System.out.println("Measurements: ");
        ObservableList<Measurement> measurements = getMeasurements();
        for (Measurement m: measurements) {
        	System.out.println(m);
        }
        
        System.out.println("Symptoms: ");
        ObservableList<Symptom> symptoms = getSymptoms();
        for (Symptom s: symptoms) {
        	System.out.println(s);
        }
        
        System.out.println("Measurement_Symptoms: ");
        ObservableList<MeasurementSymptom> measurementSymptoms = getMeasurementSymptoms();
        for (MeasurementSymptom m: measurementSymptoms) {
        	System.out.println(m);
        }
        
        //conn.close();
        /*loadPeople();
        loadOccupations();*/
    }
    
    /*Resets and creates the Measurent_Symptom Table*/
    public void resetMeasurementTherapiesTable() throws SQLException{
    	String s = "DROP TABLE IF EXISTS measurement_therapy;" +
                "CREATE TABLE measurement_therapy( " +
                "IDmeasurement INTEGER, " +
                "IDtherapy INTEGER, " +
                "FOREIGN KEY(IDmeasurement) REFERENCES measurement(id), " +
                "FOREIGN KEY(IDtherapy) REFERENCES therapy(id), " +
                "PRIMARY KEY(IDmeasurement,IDtherapy) " +
                ");";
        log(s);
        runStatement(s);
    }
    
    /*Resets and creates the Measurent_Symptom Table*/
    public void resetMeasurementPathologiesTable() throws SQLException{
    	String s = "DROP TABLE IF EXISTS measurement_pathology;" +
                "CREATE TABLE measurement_pathology( " +
                "IDmeasurement INTEGER, " +
                "IDpathology INTEGER, " +
                "FOREIGN KEY(IDmeasurement) REFERENCES measurement(id), " +
                "FOREIGN KEY(IDpathology) REFERENCES pathology(id), " +
                "PRIMARY KEY(IDmeasurement,IDpathology) " +
                ");";
        log(s);
        runStatement(s);
    }
    
    /*Resets and creates the Measurent_Symptom Table*/
    public void resetMeasurementSymptomTable() throws SQLException{
    	String s = "DROP TABLE IF EXISTS measurement_symptom;" +
                "CREATE TABLE measurement_symptom( " +
                "IDmeasurement INTEGER, " +
                "IDsymptom INTEGER, " +
                "FOREIGN KEY(IDmeasurement) REFERENCES measurement(id), " +
                "FOREIGN KEY(IDsymptom) REFERENCES symptom(id), " +
                "PRIMARY KEY(IDmeasurement,IDsymptom) " +
                ");";
        log(s);
        runStatement(s);
    }
    
    /*Resets and creates the Symptom Table*/
    public void resetSymptomTable() throws SQLException{
    	String s = "DROP TABLE IF EXISTS symptom;" +
    			"CREATE TABLE symptom( " +
    		    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
    		    "description VARCHAR(1024)" +
    		    ");";
        log(s);
        runStatement(s);
    }
    
    /*Resets and creates the Measurent Table*/
    public void resetMeasurementTable() throws SQLException{
    	String s = "DROP TABLE IF EXISTS measurement;" +
    			"CREATE TABLE measurement( " +
    		    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
    		    "sbp INTEGER, " +
    		    "dbp INTEGER, " +
    		    "datetime DATETIME, " +
    		    "informations VARCHAR(1024), " +
    		    "CFpatient VARCHAR(16), " +
    		    "FOREIGN KEY(CFpatient) REFERENCES patient(CF)" +
    		    ");";
        log(s);
        runStatement(s);
    }
    
    /*Resets and creates the Patient_Pathology Table*/
    public void resetPatientPathologyTable() throws SQLException{
    	String s = "DROP TABLE IF EXISTS patient_pathology;" +
                "CREATE TABLE patient_pathology( " +
                "CFpatient VARCHAR(16), " +
                "IDpathology INTEGER, " +
                "startDate DATETIME, " +
                "endDate DATETIME, " +
                "FOREIGN KEY(CFpatient) REFERENCES patient(CF), " +
                "FOREIGN KEY(IDpathology) REFERENCES pathology(id), " +
                "PRIMARY KEY(CFpatient,IDpathology) " +
                ");"; 
        log(s);
        runStatement(s);
    }
    
    /*Resets and creates the Pathology Table*/
    public void resetPathologyTable() throws SQLException{
    	String s = "DROP TABLE IF EXISTS pathology;" +
    			"CREATE TABLE pathology( " +
    		    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
    		    "description VARCHAR(1024)" +
    		    ");";
        log(s);
        runStatement(s);
    }
    
    /*Resets and creates the DrugIntakes Table*/
    public void resetDrugIntakesTable() throws SQLException{
        String s = "DROP TABLE IF EXISTS drugIntakes;" +
                "CREATE TABLE drugIntakes( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "datetime DATETIME, " +
                "quantity INTEGER, "+ 
                "IDtherapy INTEGER, " + 
                "FOREIGN KEY(IDtherapy) REFERENCES therapy(id)" +
                ");";
        log(s);
        runStatement(s);
    }

    /*Resets and creates the Therapy Table*/
    public void resetTherapyTable() throws SQLException{
        String s = "DROP TABLE IF EXISTS therapy;" +
                "CREATE TABLE therapy( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "dailydose INTEGER, " +
                "quantity INTEGER, "+ 
                "directions VARCHAR(1024), " + 
                "startDate DATE, " +
                "endDate DATE, " +
                "IDdrug INTEGER, " +
                "CFpatient VARCHAR(16), " +
                "CFphysician VARCHAR(16), " + 
                "FOREIGN KEY(IDdrug) REFERENCES drug(id), " +
                "FOREIGN KEY(CFpatient) REFERENCES patient(CF), " +
                "FOREIGN KEY(CFphysician) REFERENCES physician(CF)" +
                ");";
        log(s);
        runStatement(s);
    }
    
    /*Resets and creates the Drug Table*/
    public void resetDrugTable() throws SQLException{
        String s = "DROP TABLE IF EXISTS drug;" +
                "CREATE TABLE drug( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(255), " +
                "description VARCHAR(255)" +
                ");";
        log(s);
        runStatement(s);
    }
    
    /*Resets and creates the Patient Table*/
    public void resetPatientTable() throws SQLException{
        String s = "DROP TABLE IF EXISTS patient;" +
                "CREATE TABLE patient( " +
                "CF VARCHAR(16) PRIMARY KEY, " +
                "email VARCHAR(255), " +
                "password VARCHAR(255), " +
                "name VARCHAR(255), " +
                "surname VARCHAR(255), " +
                "sex VARCHAR(1), " +
                "birthdate DATE, " +
                "nationality VARCHAR(255), " +
                "street VARCHAR(255), " +
                "civicnumber INTEGER, " +
                "cap INTEGER, " +
                "city VARCHAR(255), " +
                "phonenumber VARCHAR(10), " +
                "informations VARCHAR(1024), " +
                "CFphysician VARCHAR(16) NOT NULL, " +
                "FOREIGN KEY (CFphysician) REFERENCES physician(CF) ON UPDATE CASCADE" + 
                ");";
        log(s);
        runStatement(s);
    }

    /*Resets and creates the Log Table*/
    public void resetLogTable() throws SQLException{
        String s = "DROP TABLE IF EXISTS log;" +
                "CREATE TABLE log( " +
                "CF VARCHAR(16), " +
                "datetime DATETIME, " +
                "informations VARCHAR(255), " +
                "FOREIGN KEY(CF) REFERENCES physician(CF), " +
                "PRIMARY KEY(CF, datetime)" +
                ");";
        log(s);
        runStatement(s);
    }
    
    /*Resets and creates the Physician Table*/
    public void resetPhysicianTable() throws SQLException{
        String s = "DROP TABLE IF EXISTS physician;" +
                "CREATE TABLE physician( " +
                "CF VARCHAR(16) PRIMARY KEY, " +
                "email VARCHAR(255), " +
                "password VARCHAR(255), " +
                "name VARCHAR(255), " +
                "surname VARCHAR(255), " +
                "sex VARCHAR(1), " +
                "birthdate DATE, " +
                "nationality VARCHAR(255), " +
                "street VARCHAR(255), " +
                "civicnumber INTEGER, " +
                "cap INTEGER, " +
                "city VARCHAR(255), " +
                "phonenumber VARCHAR(10)" +
                ");";
        log(s);
        runStatement(s);
    }
    
    /*Deletes all the tables from the db*/
    public void clearAll() throws SQLException{
        String q = "DROP TABLE measurement_therapy;"+
        		"DROP TABLE measurement_pathology;"+
        		"DROP TABLE measurement_symptom;" +
        		"DROP TABLE symptom;"+
        		"DROP TABLE measurement;"+
        		"DROP TABLE patient_pathology;"+
        		"DROP TABLE pathology;"+
        		"DROP TABLE drugIntakes;"+
        		"DROP TABLE therapy;"+ 
        		"DROP TABLE drug;" +
        		"DROP TABLE patient;" +
        		"DROP TABLE log;" +
        		"DROP TABLE physician;"
        		;
        log(q);
        runStatement(q);
    }
    
    /*Performs a statement and returns the number of rows affected*/
    public int runStatementWithOutput(String s) throws SQLException {

        int r;
        Statement stmt  = conn.createStatement();
        r = stmt.executeUpdate(s);
        return r;
    }
    
    /*Deletes all records from the specified table*/
    public void deleteDataFromTable(String table) throws SQLException {
    	String q = "DELETE FROM " + table;
    	log(q);
    	runStatement(q);
    }
    
    
    /*Returns a ObservableList of all MeasurementSymptom*/
    public ObservableList<MeasurementTherapy> getMeasurementTherapies() throws SQLException{
    	ObservableList<MeasurementTherapy> measurementTherapies = FXCollections.<MeasurementTherapy>observableArrayList(
                measurementTherapy -> new Observable[] {
                        measurementTherapy.IDMeasurementProperty(), 
                        measurementTherapy.IDTherapyProperty()
                        }
        );
    	
    	String q = "SELECT * FROM measurement_therapy";
    	log(q);
    	ResultSet rs = runQuery(q);
    	
    	while(rs.next()) {
    		measurementTherapies.add(new MeasurementTherapy(
    				rs.getInt("IDmeasurement"),
    				rs.getInt("IDtherapy")
    				));
    	}
    	
    	return measurementTherapies;
    }
    
    /*Tries to insert a new MeasurementSymptom*/
	public MeasurementTherapy insertMeasurementTherapy(int IdMeasurement, int IdTherapy) throws SQLException, ParseException {
		log("Add MeasurementTherapy " + IdMeasurement + " " + IdTherapy);
        MeasurementTherapy measurementTherapy = new MeasurementTherapy(IdMeasurement, IdTherapy);
        
        String q = "INSERT INTO measurement_therapy(IDmeasurement, IDtherapy)\n" +
                "VALUES ('"+ measurementTherapy.getIDMeasurement() + "', '"+ measurementTherapy.getIDTherapy() +"')\n" +
                ";";
        int id = runStatementWithOutput(q);
        if (id != 0)return measurementTherapy;
        return null;
	}
    
    
    
    
    
    
    
    /*Returns a ObservableList of all MeasurementPathology*/
    public ObservableList<MeasurementPathology> getMeasurementPathology() throws SQLException{
    	ObservableList<MeasurementPathology> measurementPathologies = FXCollections.<MeasurementPathology>observableArrayList(
                measurementPathology -> new Observable[] {
                        measurementPathology.IDMeasurementProperty(), 
                        measurementPathology.IDPathologyProperty()
                        }
        );
    	
    	String q = "SELECT * FROM measurement_pathology";
    	log(q);
    	ResultSet rs = runQuery(q);
    	
    	while(rs.next()) {
    		measurementPathologies.add(new MeasurementPathology(
    				rs.getInt("IDmeasurement"),
    				rs.getInt("IDpathology")
    				));
    	}
    	
    	return measurementPathologies;
    }
    
    /*Tries to insert a new MeasurementPathology*/
	public MeasurementPathology insertMeasurementPathology(int IdMeasurement, int IdPathology) throws SQLException, ParseException {
		log("Add MeasurementPathology " + IdMeasurement + " " + IdPathology);
        MeasurementPathology measurementPathology = new MeasurementPathology(IdMeasurement, IdPathology);
        
        String q = "INSERT INTO measurement_pathology(IDmeasurement, IDpathology)\n" +
                "VALUES ('"+ measurementPathology.getIDMeasurement() + "', '"+ measurementPathology.getIDPathology() +"')\n" +
                ";";
        int id = runStatementWithOutput(q);
        if (id != 0)return measurementPathology;
        return null;
	}
    
    
    
    
    
    
    
    /*Returns a ObservableList of all MeasurementSymptom*/
    public ObservableList<MeasurementSymptom> getMeasurementSymptoms() throws SQLException{
    	ObservableList<MeasurementSymptom> measurementSymptoms = FXCollections.<MeasurementSymptom>observableArrayList(
                measurementSymptom -> new Observable[] {
                        measurementSymptom.IDMeasurementProperty(), 
                        measurementSymptom.IDSymptomProperty()
                        }
        );
    	
    	String q = "SELECT * FROM measurement_symptom";
    	log(q);
    	ResultSet rs = runQuery(q);
    	
    	while(rs.next()) {
    		measurementSymptoms.add(new MeasurementSymptom(
    				rs.getInt("IDmeasurement"),
    				rs.getInt("IDsymptom")
    				));
    	}
    	
    	return measurementSymptoms;
    }
    
    
    /*Tries to insert a new MeasurementSymptom*/
	public MeasurementSymptom insertMeasurementSymptom(int IdMeasurement, int IdSymptom) throws SQLException, ParseException {
		log("Add MeasurementSymptom " + IdMeasurement + " " + IdSymptom);
        MeasurementSymptom measurementSymptom = new MeasurementSymptom(IdMeasurement, IdSymptom);
        
        String q = "INSERT INTO measurement_symptom(IDmeasurement, IDsymptom)\n" +
                "VALUES ('"+ measurementSymptom.getIDMeasurement() + "', '"+ measurementSymptom.getIDSymptom() +"')\n" +
                ";";
        int id = runStatementWithOutput(q);
        if (id != 0)return measurementSymptom;
        return null;
	}
	
	
	
	
	
	
	
	
	
	
	/*Returns a ObservableList of all Symp*/
    public ObservableList<Symptom> getSymptoms() throws SQLException{
    	ObservableList<Symptom> symptoms = FXCollections.<Symptom>observableArrayList(
                symptom -> new Observable[] {
                        symptom.idProperty(), 
                        symptom.descriptionProperty()
                        }
        );
    	
    	String q = "SELECT * FROM symptom";
    	log(q);
    	ResultSet rs = runQuery(q);
    	
    	while(rs.next()) {
    		symptoms.add(new Symptom(
					rs.getInt("id"),
					rs.getString("description")
					));
    	}
    	
    	return symptoms;
    }
    
    
	
	/*Tries to insert a new Symptom*/
	public Symptom insertSymptom(String description) throws SQLException, ParseException {
		log("Add Symptom " + description);
        Symptom symptom = new Symptom(0, description);
        String q = "INSERT INTO symptom(description)\n" +
                "VALUES ('"+ description + "')\n" +
                ";";
        int id = runStatementWithOutput(q);
        if (id != 0)return symptom;
        return null;
	}
    
    
    
    /*Returns a ObservableList of all Measurement*/
    public ObservableList<Measurement> getMeasurements() throws SQLException{
    	ObservableList<Measurement> measurements = FXCollections.<Measurement>observableArrayList(
                measurement -> new Observable[] {
                        measurement.idProperty(), 
                        measurement.sbpProperty(),
                        measurement.dbpProperty(),                   
                        measurement.dateProperty(),
                        measurement.informationsProperty(),
                        measurement.CFPatientProperty(),
                        }
        );
    	
    	String q = "SELECT * FROM measurement";
    	log(q);
    	ResultSet rs = runQuery(q);
    	
    	while(rs.next()) {
    		measurements.add(new Measurement(
					rs.getInt("id"),
					rs.getInt("sbp"),
					rs.getInt("dbp"),
    				rs.getTimestamp("datetime").toLocalDateTime(),
    				rs.getString("informations"), 
    				rs.getString("CFpatient")
    				));
    	}
    	
    	return measurements;
    }
    
    /*Tries to insert a new Measurement*/
	public Measurement insertMeasurement(int sbp, int dbp, LocalDateTime date, String informations, String CFpatient) throws SQLException, ParseException {
		log("Add Measurement " + sbp + "  " + dbp);
    	//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(((Date) df.parse(birthdate.toString())).getTime());
        //Long bdate = df.parse(birthdate.toString()).getTime();
        Measurement measurement = new Measurement(0, sbp, dbp, date, informations, CFpatient);
        long timestamp = date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        String q = "INSERT INTO Measurement(sbp, dbp, datetime, informations, CFpatient)\n" +
                "VALUES ('"+ sbp + "', '" + dbp + "', '" + timestamp + "', '" + informations + "', '" + CFpatient + "')\n" +
                "RETURNING id;";
        int id = runStatementWithOutput(q);
        if (id != 0) {
        	return measurement;
        }
        //System.out.println("qui");
        return null;
	}
    
    
  
  
  
  
    /*Returns a ObservableList of all PatientPathologies*/
    public ObservableList<PatientPathology> getPatientPathologies() throws SQLException{
    	ObservableList<PatientPathology> patientPathologies = FXCollections.<PatientPathology>observableArrayList(
                patientPathology -> new Observable[] {
                        patientPathology.startDateProperty(), 
                        patientPathology.endDateProperty(),
                        patientPathology.cfPatientProperty(),
                        patientPathology.idPathologyProperty()             
                        }
        );
    	
    	String q = "SELECT * FROM patient_pathology";
    	log(q);
    	ResultSet rs = runQuery(q);
    	
    	while(rs.next()) {
    		patientPathologies.add(new PatientPathology(
					rs.getDate("startDate").toLocalDate(),
    				rs.getDate("endDate") == null ? null : rs.getDate("endDate").toLocalDate(),
    				rs.getString("CFpatient"),
    				rs.getInt("IDpathology")			
    				));
    	}
    	
    	return patientPathologies;
    }
    
    /*Tries to insert a new PatientPathology*/
	public PatientPathology insertPatientPathology(String CFPatient, int idPathology, LocalDate startDate, LocalDate endDate) throws SQLException, ParseException {
		log("Add PatientPathology " + CFPatient+ "  " + idPathology + " " + startDate + " " + endDate);
        PatientPathology patientPathology = new PatientPathology(startDate, endDate, CFPatient, idPathology);      
        long timestamp = LocalDateToLong(startDate);
        long timestamp1;
        String q;
        if (endDate==null) {
        	q = "INSERT INTO patient_pathology(CFpatient, IDpathology, startDate)\n" +
                    "VALUES ('" + CFPatient + "', '"+ idPathology + "', '"+ timestamp + "')\n" +
                    ";";
        }else {
        	timestamp1 = LocalDateToLong(endDate);
        	q = "INSERT INTO patient_pathology(CFpatient, IDpathology, startDate, endDate)\n" +
                    "VALUES ('" + CFPatient + "', '"+ idPathology + "', '"+ timestamp + "', '" + timestamp1 + "')\n" +
                    ";";
        }
        
        int id = runStatementWithOutput(q);
        if (id != 0)return patientPathology;
        return null;
	}
    
    
    
    
    
    
    
    
    
    /*Returns a ObservableList of all Pathologies*/
    public ObservableList<Pathology> getPathologies() throws SQLException{
    	ObservableList<Pathology> pathologies = FXCollections.<Pathology>observableArrayList(
                pathology -> new Observable[] {
                        pathology.idProperty(), 
                        pathology.descriptionProperty()
                        }
        );
    	
    	String q = "SELECT * FROM pathology";
    	log(q);
    	ResultSet rs = runQuery(q);
    	
    	while(rs.next()) {
    		pathologies.add(new Pathology(
    				rs.getInt("id"),
    				rs.getString("description")
    				));
    	}
    	
    	return pathologies;
    }
    
    /*Tries to insert a new Pathology*/
	public Pathology insertPathology(String description) throws SQLException, ParseException {
		log("Add Pathology " + description);
    	//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(((Date) df.parse(birthdate.toString())).getTime());
        //Long bdate = df.parse(birthdate.toString()).getTime();
        Pathology pathology = new Pathology(0, description);
        

        String q = "INSERT INTO Pathology(description)\n" +
                "VALUES ('"+ pathology.getDescription() + "')\n" +
                ";";
        int id = runStatementWithOutput(q);
        if (id != 0)return pathology;
        //System.out.println("qui");
        return null;
	}
    
    
    
    
    
    
    /*Returns a ObservableList of all DrugIntakes*/
    public ObservableList<DrugIntake> getDrugIntakes() throws SQLException{
    	ObservableList<DrugIntake> drugIntakes = FXCollections.<DrugIntake>observableArrayList(
                drugIntake -> new Observable[] {
                        drugIntake.idProperty(), 
                        drugIntake.datetimeProperty(),
                        drugIntake.quantityProperty(),
                        drugIntake.IDTherapyProperty()
                        }
        );
    	
    	String q = "SELECT * FROM drugIntakes";
    	log(q);
    	ResultSet rs = runQuery(q);
    	
    	while(rs.next()) {
    		drugIntakes.add(new DrugIntake(
					rs.getInt("id"),
    				rs.getTimestamp("datetime").toLocalDateTime(),
    				rs.getInt("quantity"), 
    				rs.getInt("IDtherapy")
    				));
    	}
    	
    	return drugIntakes;
    }
    
    /*Tries to insert a new DrugIntake*/
	public DrugIntake insertDrugIntake(int idDrugIntake, LocalDateTime datetime, int quantity, int idTherapy) throws SQLException, ParseException {
		log("Add DrugIntake " + idDrugIntake + "  " + datetime + " " + quantity + " " + idTherapy);
        DrugIntake drugIntake = new DrugIntake(0, datetime, quantity, idTherapy);      
        long timestamp = datetime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        String q = "INSERT INTO drugIntakes(datetime, quantity, IDtherapy)\n" +
                "VALUES ('"+ timestamp + "', '"+ drugIntake.getQuantity() + "', '" + drugIntake.getIDTherapy() + "')\n" +
                ";";
        int id = runStatementWithOutput(q);
        if (id != 0)return drugIntake;
        return null;
	}
    
    
    
    
    
    /*Returns a ObservableList of all Therapies*/
    public ObservableList<Therapy> getTherapies() throws SQLException{
    	ObservableList<Therapy> therapies = FXCollections.<Therapy>observableArrayList(
                therapy -> new Observable[] {
                        therapy.idProperty(), 
                        therapy.dailyDoseProperty(),
                        therapy.quantityProperty(),
                        therapy.directionsProperty(),
                        therapy.startDateProperty(),
                        therapy.endDateProperty(),
                        therapy.IDDrugProperty(),
                        therapy.CFPatientProperty(),
                        therapy.CFPhysicianProperty()
                        }
        );
    	
    	String q = "SELECT * FROM therapy";
    	log(q);
    	ResultSet rs = runQuery(q);
    	
    	while(rs.next()) {
    		therapies.add(new Therapy(
    				rs.getInt("id"),
    				rs.getInt("dailydose"),
    				rs.getInt("quantity"),
    				rs.getString("directions"),
    				rs.getDate("startDate").toLocalDate(),
    				(rs.getDate("endDate") == null ? null : rs.getDate("endDate").toLocalDate()),
    				rs.getInt("IDdrug"),
    				rs.getString("CFpatient"),
    				rs.getString("CFphysician")
    				));
    	}
    	
    	return therapies;
    }
    
    /*Tries to insert a new Therapy*/
	public Therapy insertTherapy(int dailydose, int quantity, String directions, LocalDate startDate, LocalDate endDate, int IDdrug, String CFPatient, String CFPhysician) throws SQLException, ParseException {
		log("Add Therapy " + IDdrug + "  " + CFPatient + "  " + CFPhysician);
    	//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(((Date) df.parse(birthdate.toString())).getTime());
        //Long bdate = df.parse(birthdate.toString()).getTime();
		Therapy therapy = new Therapy(0, dailydose, quantity, directions, startDate, endDate, IDdrug, CFPatient, CFPhysician);
	        
		Long startDateLong =  LocalDateToLong(startDate);
		String q;
		if (endDate == null) {
			q = "INSERT INTO Therapy(dailydose, quantity, directions, startDate, IDdrug, CFpatient, CFphysician)\n" +
	                "VALUES ('"+ therapy.getDailyDose() + "', '"+ therapy.getQuantity() + "', '" + therapy.getDirections() + "', '" + startDateLong + "', '" + therapy.getIDDrug() + "', '" + therapy.getCFPatient() + "', '" + therapy.getCFPhysician() + "')\n" +
	                ";";
			
		}else {
			Long endDateLong =  LocalDateToLong(endDate);
			q = "INSERT INTO Therapy(dailydose, quantity, directions, startDate, endDate, IDdrug, CFpatient, CFphysician)\n" +
	                "VALUES ('"+ therapy.getDailyDose() + "', '"+ therapy.getQuantity() + "', '" + therapy.getDirections() + "', '" + startDateLong + "', '" + endDateLong + "', '" + therapy.getIDDrug() + "', '" + therapy.getCFPatient() + "', '" + therapy.getCFPhysician() + "')\n" +
	                ";";
		}    
        
        int id = runStatementWithOutput(q);
        if (id != 0)return therapy;
        //System.out.println("qui");
        return null;
	}
	
	
	
	
	
	
	
	/*Returns a ObservableList of all Drugs*/
    public ObservableList<Drug> getDrugs() throws SQLException{
    	ObservableList<Drug> drugs = FXCollections.<Drug>observableArrayList(
                drug -> new Observable[] {
                        drug.idProperty(), 
                        drug.nameProperty(),
                        drug.descriptionProperty()
                        }
        );
    	
    	String q = "SELECT * FROM drug";
    	log(q);
    	ResultSet rs = runQuery(q);
    	
    	while(rs.next()) {
    		drugs.add(new Drug(
    				rs.getInt("id"),
    				rs.getString("name"), 
    				rs.getString("description")
    				));
    	}
    	
    	return drugs;
    }
	
	/*Tries to insert a new Drug*/
	public Drug insertDrug(int idDrug, String name, String description) throws SQLException, ParseException {
		log("Add Drug " + idDrug + "  " + name + " " + description);
        Drug drug = new Drug(0, name, description);
        String q = "INSERT INTO drug(name, description)\n" +
                "VALUES ('"+ drug.getName() + "', '" + drug.getDescription() + "')\n" +
                ";";
        int id = runStatementWithOutput(q);
        if (id != 0)return drug;
        return null;
	}
    
    
    
    
    
    
        
    /*Returns a ObservableList of all Logs*/
    public ObservableList<Log> getLogs() throws SQLException{
    	ObservableList<Log> logs = FXCollections.<Log>observableArrayList(
                log -> new Observable[] {
                        log.cfPhysicianProperty(), 
                        log.dateTimeProperty(),
                        log.informationsProperty()
                        }
        );
    	
    	String q = "SELECT * FROM log";
    	log(q);
    	ResultSet rs = runQuery(q);
    	
    	while(rs.next()) {
    		logs.add(new Log(
    				rs.getTimestamp("datetime").toLocalDateTime(),
    				rs.getString("informations"), 
    				rs.getString("CF")
    				));
    	}
    	
    	return logs;
    }
    
    /*Tries to insert a new Log*/
	public Log insertLog(String CF, LocalDateTime datetime, String informations) throws SQLException, ParseException {
		log("Add Log " + CF + "  " + datetime);
    	//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(((Date) df.parse(birthdate.toString())).getTime());
        //Long bdate = df.parse(birthdate.toString()).getTime();
        Log log = new Log(datetime, informations, CF);
        
        long timestamp = datetime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        System.out.println(log.getCFPhysician());
        String q = "INSERT INTO Log(CF, datetime, informations)\n" +
                "VALUES ('"+ log.getCFPhysician() + "', '"+ timestamp + "', '" + log.getInformations() + "')\n" +
                ";";
        int id = runStatementWithOutput(q);
        if (id != 0)return log;
        //System.out.println("qui");
        return null;
	}
    
    
    
    
    
    
    
    /*Returns a ObservableList of all Physicians*/
    public ObservableList<Physician> getPhysicians() throws SQLException{
    	ObservableList<Physician> physicians = FXCollections.<Physician>observableArrayList(
                physician -> new Observable[] {
                        physician.CFProperty(), 
                        physician.emailProperty(),
                        physician.passwordProperty(), 
                        physician.nameProperty(), 
                        physician.surnameProperty(), 
                        physician.sexProperty(), 
                        physician.birthdateProperty(), 
                        physician.nationalityProperty(), 
                        physician.streetProperty(), 
                        physician.civicNumberProperty(),
                        physician.capProperty(), 
                        physician.cityProperty(), 
                        physician.phoneNumberProperty()}
        );
    	
    	String q = "SELECT * FROM physician";
    	log(q);
    	ResultSet rs = runQuery(q);
    	
    	while(rs.next()) {
    		physicians.add(new Physician(
    				rs.getString("CF"), 
    				rs.getString("email"), 
    				rs.getString("password"), 
    				rs.getString("name"), 
    				rs.getString("surname"), 
    				rs.getString("sex"), 
    				rs.getDate("birthdate").toLocalDate(), 
    				rs.getString("nationality"), 
    				rs.getString("street"), 
    				rs.getInt("civicnumber"), 
    				rs.getInt("cap"), 
    				rs.getString("city"), 
    				rs.getString("phonenumber")
    				));
    	}
    	
    	return physicians;
    }
    
    /*Tries to insert a new Physician*/
    public Physician insertPhysician(String CF, String email, String password, String name, String surname, String sex, LocalDate birthdate, String nationality, String street, int civic_number, int cap, String city, String phone_number) throws SQLException, ParseException {
    	log("Add Physician " + CF);
    	//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(((Date) df.parse(birthdate.toString())).getTime());
        //Long bdate = df.parse(birthdate.toString()).getTime();
        Long bdate = LocalDateToLong(birthdate);
        password = hashPassword(password);
        Physician physician = new Physician(CF, email, password, name, surname, sex, birthdate, nationality, street, civic_number, cap, city, phone_number);
        
        String q = "INSERT INTO Physician(CF, email, password, name, surname, sex, birthdate, nationality, street, civicnumber, cap, city, phonenumber)\n" +
                "VALUES ('"+ physician.getCF() + "', '"+ physician.getEmail() + "', '" + physician.getPassword() + "', '"+ physician.getName() +"', '"+ physician.getSurname() + "', '"+ physician.getSex() +"', '" + bdate + "', '"+ physician.getNationality() + "', '"+ physician.getStreet() + "', '"+ physician.getCivicNumber() + "', '"+ physician.getCAP() + "', '"+ physician.getCity() + "', '"+ physician.getPhoneNumber() +"')\n" +
                ";";
        int id = runStatementWithOutput(q);
        if (id != 0)return physician;
        return null;
    }
    
    
    
    
    
    
    /*Returns a ObservableList of all Patients*/
    public ObservableList<Patient> getPatients() throws SQLException{
    	ObservableList<Patient> patients = FXCollections.<Patient>observableArrayList(
                patient -> new Observable[] {
                        patient.CFProperty(), 
                        patient.emailProperty(), 
                        patient.passwordProperty(), 
                        patient.nameProperty(), 
                        patient.surnameProperty(), 
                        patient.sexProperty(), 
                        patient.birthdateProperty(), 
                        patient.nationalityProperty(), 
                        patient.streetProperty(), 
                        patient.civicNumberProperty(), 
                        patient.capProperty(), 
                        patient.cityProperty(), 
                        patient.phoneNumberProperty(), 
                        patient.informationsProperty(), 
                        patient.CFPhysicianProperty()}
        );
    	
    	String q = "SELECT * FROM patient";
    	log(q);
    	ResultSet rs = runQuery(q);
    	
    	while(rs.next()) {
    		patients.add(new Patient(
    				rs.getString("CF"), 
    				rs.getString("email"), 
    				rs.getString("password"), 
    				rs.getString("name"), 
    				rs.getString("surname"), 
    				rs.getString("sex"), 
    				rs.getDate("birthdate").toLocalDate(), 
    				rs.getString("nationality"), 
    				rs.getString("street"), 
    				rs.getInt("civicnumber"), 
    				rs.getInt("cap"), 
    				rs.getString("city"), 
    				rs.getString("phonenumber"),
    				rs.getString("informations"),
    				rs.getString("CFphysician")
    				));
    	}
    	
    	return patients;
    }

    /*Returns a ObservableList of all Patients of a specified Physician*/
    public ObservableList<Patient> getPatientsPhysician(String CFPhysician) throws SQLException{
    	ObservableList<Patient> patients = FXCollections.<Patient>observableArrayList(
                patient -> new Observable[] {
                        patient.CFProperty(), 
                        patient.emailProperty(), 
                        patient.passwordProperty(), 
                        patient.nameProperty(), 
                        patient.surnameProperty(), 
                        patient.sexProperty(), 
                        patient.birthdateProperty(), 
                        patient.nationalityProperty(), 
                        patient.streetProperty(), 
                        patient.civicNumberProperty(), 
                        patient.capProperty(), 
                        patient.cityProperty(), 
                        patient.phoneNumberProperty(), 
                        patient.informationsProperty(), 
                        patient.CFPhysicianProperty()}
        );
    	
    	String q = "SELECT * FROM patient WHERE CFPhysician='" + CFPhysician + "';";
    	log(q);
    	ResultSet rs = runQuery(q);
    	
    	while(rs.next()) {
    		patients.add(new Patient(
    				rs.getString("CF"), 
    				rs.getString("email"), 
    				rs.getString("password"), 
    				rs.getString("name"), 
    				rs.getString("surname"), 
    				rs.getString("sex"), 
    				rs.getDate("birthdate").toLocalDate(), 
    				rs.getString("nationality"), 
    				rs.getString("street"), 
    				rs.getInt("civicnumber"), 
    				rs.getInt("cap"), 
    				rs.getString("city"), 
    				rs.getString("phonenumber"),
    				rs.getString("informations"),
    				rs.getString("CFphysician")
    				));
    	}
    	
    	return patients;
    }

    /*Returns a ObservableList of all Patients of a specified Physician that contains the String 'like'*/
    public ObservableList<Patient> getSearchedPatients(String CFPhysician, String like) throws SQLException{
    	ObservableList<Patient> patients = FXCollections.<Patient>observableArrayList(
                patient -> new Observable[] {
                        patient.CFProperty(), 
                        patient.emailProperty(), 
                        patient.passwordProperty(), 
                        patient.nameProperty(), 
                        patient.surnameProperty(), 
                        patient.sexProperty(), 
                        patient.birthdateProperty(), 
                        patient.nationalityProperty(), 
                        patient.streetProperty(), 
                        patient.civicNumberProperty(), 
                        patient.capProperty(), 
                        patient.cityProperty(), 
                        patient.phoneNumberProperty(), 
                        patient.informationsProperty(), 
                        patient.CFPhysicianProperty()}
        );
    	
    	String q = "SELECT * FROM patient WHERE CFPhysician='" + CFPhysician + "' AND CF LIKE '" + like + "';";
    	log(q);
    	ResultSet rs = runQuery(q);
    	
    	while(rs.next()) {
    		patients.add(new Patient(
    				rs.getString("CF"), 
    				rs.getString("email"), 
    				rs.getString("password"), 
    				rs.getString("name"), 
    				rs.getString("surname"), 
    				rs.getString("sex"), 
    				rs.getDate("birthdate").toLocalDate(), 
    				rs.getString("nationality"), 
    				rs.getString("street"), 
    				rs.getInt("civicnumber"), 
    				rs.getInt("cap"), 
    				rs.getString("city"), 
    				rs.getString("phonenumber"),
    				rs.getString("informations"),
    				rs.getString("CFphysician")
    				));
    	}
    	
    	return patients;
    }

    /*Tries to update an existent Patient*/
    public void updatePatient(String CF,String informations) throws SQLException {
    	log("Update Patient " + CF);
        
        String q = "UPDATE patient SET informations = '" + informations + "' WHERE CF = '" + CF +"';";

        runStatementWithOutput(q);
        
    }


	
	/*Tries to insert a new Patient*/
	public Patient insertPatient(String CF, String email, String password, String name, String surname, String sex, LocalDate birthdate, String nationality, String street, int civic_number, int cap, String city, String phone_number,String informations, String CFPhysician) throws SQLException, ParseException {
		log("Add Patient " + CF);
    	//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(((Date) df.parse(birthdate.toString())).getTime());
        //Long bdate = df.parse(birthdate.toString()).getTime();
        
		Long bdate = LocalDateToLong(birthdate);
		password = hashPassword(password);
        Patient patient = new Patient(CF, email, password, name, surname, sex, birthdate, nationality, street, civic_number, cap, city, phone_number, informations, CFPhysician);
        
        String q = "INSERT INTO Patient(CF, email, password, name, surname, sex, birthdate, nationality, street, civicnumber, cap, city, phonenumber, informations, CFphysician)\n" +
                "VALUES ('"+ patient.getCF() + "', '"+ patient.getEmail() + "', '" + patient.getPassword() + "', '"+ patient.getName() +"', '"+ patient.getSurname() + "', '"+ patient.getSex() +"', '" + bdate + "', '"+ patient.getNationality() + "', '"+ patient.getStreet() + "', '"+ patient.getCivicNumber() + "', '"+ patient.getCAP() + "', '"+ patient.getCity() + "', '"+ patient.getPhoneNumber() +"', '"+ patient.getInformations() +"', '"+ patient.getCFPhysician() +"')\n" +
                ";";
        int id = runStatementWithOutput(q);
        if (id != 0)return patient;
        //System.out.println("qui");
        return null;
	}
	
	
	
	
	public String hashPassword(String password) {
		try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
		
	}
	
	public Long LocalDateToLong(LocalDate birthdate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(((Date) df.parse(birthdate.toString())).getTime());
        Long bdate = df.parse(birthdate.toString()).getTime();
        return bdate;
	}
	    
    /*Initializes the db instance or return the one already initialized*/
    public static synchronized DB_Model getInstance() throws SQLException
    {
        if (single_instance == null)
            single_instance = new DB_Model();

        return single_instance;
    }
    
    /*Closes the connection to the db*/
    public void closeConnection() throws SQLException {
    	conn.close();
    }
}
