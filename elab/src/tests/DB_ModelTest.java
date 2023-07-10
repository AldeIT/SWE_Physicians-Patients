package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.DB_Model;

/**
 * This class contains unit tests for the DB_Model class.
 */
public class DB_ModelTest {

	
	/**
	 * Test for the connect method of the DB_Model class. It tries to connect to a database
	 * and checks if the connection is not null.
	 *
	 * @throws SQLException if there is an error with the database connection
	 */
	@Test
	public void testConnect() throws SQLException {
		
		String url = "jdbc:sqlite:table.db";
		Connection conn = DriverManager.getConnection(url);
		assertNotNull("Failed Connection", conn);
	}

	
	/**
	 * Test for the tableExists method of the DB_Model class. It checks if a table exists in the database.
	 *
	 * @throws SQLException if there is an error with the database connection
	 */
	@Test
	public void testTableExists() throws SQLException {
		var m = DB_Model.getInstance();
		assertTrue(m.tableExists("physician"));
		assertFalse(m.tableExists("bla"));
	}

	/**
	 * Test for the runQuery method of the DB_Model class. It runs a SELECT query and checks if the
	 * ResultSet object is not null and contains at least one row.
	 *
	 * @throws SQLException if there is an error with the database connection or query
	 */
	@Test
	public void testRunQuery() throws SQLException {
		var m = DB_Model.getInstance();
		String query = "SELECT * FROM physician";
		ResultSet rs = m.runQuery(query);
		// verifico risultato query non sia null
		assertNotNull("ResultSet is null", rs);
		// verifico risultato contenga almeno una riga
		assertTrue("ResultSet is empty", rs.next());
	}

	
	/**
	 * Test for the getInstance method of the DB_Model class. It checks if the DB_Model instance is not null
	 * and if two instances are the same object.
	 * @throws SQLException if there is an error with the database connection
	 */
	@Test
	public void testGetInstance() throws SQLException {
		var m1 = DB_Model.getInstance();
		assertNotNull("Model instance is null", m1);
		var m2 = DB_Model.getInstance();
		assertNotNull("Model instance is null", m2);
		assertSame("Model instances are not the same object", m1, m2);
	}
	
}
