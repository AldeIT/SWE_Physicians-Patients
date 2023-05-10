package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import org.junit.jupiter.api.Test;

import model.DB_Model;
import model.Drug;


import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


class DB_ModelTest {

	@Test
	void testConnect() throws SQLException {
		
		String url = "jdbc:sqlite:table.db";
		Connection conn = DriverManager.getConnection(url);
		assertNotNull("Failed Connection", conn);
	}

	@Test
	void testTableExists() throws SQLException {
		var m = DB_Model.getInstance();
		assertTrue(m.tableExists("physician"));
		assertFalse(m.tableExists("bla"));
	}

	@Test
	void testRunQuery() throws SQLException {
		var m = DB_Model.getInstance();
		String query = "SELECT * FROM physician";
		ResultSet rs = m.runQuery(query);
		// verifico risultato query non sia null
		assertNotNull("ResultSet is null", rs);
		// verifico risultato contenga almeno una riga
		assertTrue("ResultSet is empty", rs.next());
	}

	@Test
	void testGetInstance() throws SQLException {
		var m1 = DB_Model.getInstance();
		assertNotNull("Model instance is null", m1);
		var m2 = DB_Model.getInstance();
		assertNotNull("Model instance is null", m2);
		assertSame("Model instances are not the same object", m1, m2);
	}
	
}
