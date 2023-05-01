package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




//Singleton Instance
public class DB_Model {
	public void log(Object o){
        System.out.println(o);
    }
	
	private static DB_Model single_instance = null;
    private Connection conn;
    
    public void connect() throws SQLException {

        String url = "jdbc:sqlite:src/model/table.db";
        conn = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite has been established.");

    }
	
    public boolean tableExists(String table_name) throws SQLException {
        String q = "SELECT * FROM sqlite_master WHERE tbl_name = '" + table_name + "'";
        log(q);
        ResultSet rs = runQuery(q);
        return rs.next();
    }
    
    public ResultSet runQuery(String q) throws SQLException {

        ResultSet rs = null;
        Statement stmt  = conn.createStatement();
        rs = stmt.executeQuery(q);
        return rs;

    }
    
    public void runStatement(String s) throws SQLException {

        Statement stmt  = conn.createStatement();
        stmt.executeUpdate(s);
    }
    
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
        
        log("DONE");
        
        
     
        /*loadPeople();
        loadOccupations();*/
    }
    
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
    
    public void resetSymptomTable() throws SQLException{
    	String s = "DROP TABLE IF EXISTS symptom;" +
    			"CREATE TABLE symptom( " +
    		    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
    		    "description VARCHAR(1024)" +
    		    ");";
        log(s);
        runStatement(s);
    }
    
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
    
    public void resetPathologyTable() throws SQLException{
    	String s = "DROP TABLE IF EXISTS pathology;" +
    			"CREATE TABLE pathology( " +
    		    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
    		    "description VARCHAR(1024)" +
    		    ");";
        log(s);
        runStatement(s);
    }
    
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
                "CFphysician VARCHAR(16), " +
                "FOREIGN KEY(CFphysician) REFERENCES physician(CF)" + 
                ");";
        log(s);
        runStatement(s);
    }

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
    public void clearAll() throws SQLException{
        String s = "DROP TABLE physician;" +
                "DROP TABLE log;" +
                "DROP TABLE patient;" +
                "DROP TABLE drug;" +
                "DROP TABLE therapy;"+ 
                "DROP TABLE drugIntakes;";
        log(s);
        runStatement(s);
    }
    
    
    
    
    public static synchronized DB_Model getInstance() throws SQLException
    {
        if (single_instance == null)
            single_instance = new DB_Model();

        return single_instance;
    }
}
