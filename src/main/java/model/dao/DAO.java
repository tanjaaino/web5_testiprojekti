package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 
 * Tietokantakäsittelyn yliluokka, josta tietokantataulun käsittelevä xDAO-aliluokka voi periä 
 * tietokantayhteyden muodostavan getDBConnection-metodin 
 * ja tietokantayhteyden sulkevan closeDBConnection()-metodin
 *
 */
public class DAO {

	/**
	 * Antaa tietokantayhteyden
	 * 
	 * @return connection - tietokantayhteys
	 */
	protected static Connection getDBConnection() {
		Connection connection = null;			

		// Alkumääritykset  
		String username = DBAccounts.DBUSERNAME; 
		String password = DBAccounts.DBPASSWORD;
		String url = DBAccounts.DBURL;

		try {
			// Ladataan ajuri
			Class.forName("org.mariadb.jdbc.Driver").newInstance();

			// Avataan yhteys connection-nimiseen muuttujaan
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return connection;
	}

	/**
	 * Sulkee Statementin ja Connectionin
	 * 
	 * @param SQL-statement
	 * @param Tietokantayhteys
	 */
	protected static void closeDBConnection(Statement stmt, Connection connection) {
		closeDBConnection(null, stmt, connection);
	}

	/**
	 * Sulkee ResultSetin, Statementin ja Connectionin
	 */
	protected static void closeDBConnection(ResultSet rs, Statement stmt, Connection conn) {

		try {
			if (rs != null) { // Suljetaan rs (palautettu tulostaulu), mikäli
								// olemassa
				rs.close();
			}
			if (stmt != null) { // Suljetaan stmt (SQL-statement), mikäli
								// olemassa
				stmt.close();
			}
			if (conn != null) { // Suljetaan conn (yhteys), mikäli olemassa
				conn.close();
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}