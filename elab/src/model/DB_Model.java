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
        /*clearAll();*/
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
        deleteDataFromTable("patient");
        deleteDataFromTable("physician");
        
        
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
        
        
        try {
			Physician p = insertPhysician("LDGLSN02S18F861T", "alealde012@gmail.com", "password", "Alessandro", "Aldegheri", "M", LocalDate.of(2002, 11, 18), "Italian", "Danieli", 21, 37141, "Verona", "3497086640");
			Physician p1 = insertPhysician("LDGLSN02S18F861Z", "alealde012@gmail.com", "password", "Alessandro", "Aldegheri", "M", LocalDate.of(2002, 11, 18), "Italian", "Danieli", 21, 37141, "Verona", "3497086640");
			Physician p2 = insertPhysician("LDGLSN02S18F861I", "alealde012@gmail.com", "password", "Alessandro", "Aldegheri", "M", LocalDate.of(2002, 11, 18), "Italian", "Danieli", 21, 37141, "Verona", "3497086640");
			Patient pat = insertPatient("VNTDVD02D17L949I", "venturi.davide17@gmail.com", "password", "Davide", "Venturi", "M", LocalDate.of(2002,04,17), "Italian", "Marconi", 89, 37060, "Verona", "3402938423", "Ansia", "LDGLSN02S18F861T");
			Patient pat1 = insertPatient("VNTDVD02D17L949Z", "venturi.davide17@gmail.com", "password", "Davide", "Venturi", "M", LocalDate.of(2002,04,17), "Italian", "Marconi", 89, 37060, "Verona", "3402938423", "Ansia", "LDGLSN02S18F861Z");
			Patient pat2 = insertPatient("VNTDVD02D17L949T", "venturi.davide17@gmail.com", "password", "Davide", "Venturi", "M", LocalDate.of(2002,04,17), "Italian", "Marconi", 89, 37060, "Verona", "3402938423", "Ansia", "LDGLSN02S18F861I");
        		
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
        	System.out.println("Problema nel db");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
        
        System.out.println("Physicians: ");
        allPhys = getPhysicians();
        for (Physician p: allPhys) {
        	System.out.println(p);
        }
        
        
        System.out.println("Patients: ");
        allPat = getPatients();
        for (Patient p: allPat) {
        	System.out.println(p);
        }
        
        //conn.close();
        /*loadPeople();
        loadOccupations();*/
    }
    
    /*Resets and creates the Measurent_Symptom Table*/
    public void resetMeasurementSymptomTable() throws SQLException{
    	String s = "DROP TABLE IF EXISTS measurement_symptom;" +
                "CREATE TABLE measurement_symptom( " +
                "IDmeasurement VARCHAR(16), " +
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
                "done INTEGER, " + 
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
        String q = "DROP TABLE measurement_symptom;" +
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
        System.out.println("qui");
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
