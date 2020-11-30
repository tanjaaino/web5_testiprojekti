package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Asiakas;


	/**
	 * Asiakas-tietokohteen tietokantakäsittelypalvelut kuten
		 * findAll() - hae kaikki asiakkaat tietokannasta
		 * findById() - hae yhden asiakkaan tiedot annetulla asiakasid:llä  TODO
		 * addAsiakas() - lisää asiakas tietokantaan
		 * removeAsiakas() - poista asiakkaan tiedot tietokannasta
		 * updateAsiakas() - päivitä asiakkaan tiedot tietokantaan  TODO
	 *
	 */

	public class AsiakasDAO extends DAO {

		
		/**
		 * Hakee tietokannan taulusta kaikki asiakkaat ja luo ja palauttaa tiedot Asiakas-tyyppisten olioiden listana (ArrayList) 
		 * 
		 * @return ArrayList<Asiakas> Lista asiakkaat
		 */
		public ArrayList<Asiakas> findAll() {	
			Connection connection = null;  // tietokantayhteys
			PreparedStatement statement = null;  // sql-lause
			ResultSet resultset = null;   // select-lauseen tulostaulu
			ArrayList<Asiakas> asiakkaat = new ArrayList<Asiakas>();  // tyhjä asiakaslista
			Asiakas asiakas = null; 
			try {
				// Luodaan yhteys
				connection = getDBConnection();
				// Luodaan komento: haetaan kaikki rivit asiakas-taulusta
				String sqlSelect = 
						"SELECT id, firstname, lastname, birthyear, sex, streetaddress, postcode, email, bonusscore FROM customer;";
				// Valmistellaan komento:
				statement = connection.prepareStatement(sqlSelect);
				// Lähetetään select-komento suoritettavaksi tietokantapalvelimelle:
				resultset = statement.executeQuery();
				// Käydään tulostaulun rivit läpi ja luetaan readAsiakas()-metodilla:
				while (resultset.next()) {
					asiakas = readAsiakas(resultset);
					// lisätään asiakas listaan
					asiakkaat.add(asiakas);
				}
			} catch (SQLException e) {
				e.printStackTrace(); // consoleen näkyviin Exception-tilanteen tarkemmat tiedot vianjäljitystä varten
				throw new RuntimeException(e);
			} finally {
				closeDBConnection(resultset, statement, connection); // Suljetaan
			}
		
			return asiakkaat;
		}
		
		
		
		/**
		 * Lukee kyselyn tulostaulusta yhdeltä aktiiviseltä tietoriviltä (asiakkaan tiedot). 
		 * Luo ja palauttaa tietojen perusteella Asiakas-tyyppisen olion
		 * 
		 * @param resultset
		 *            tietokannasta kyselyllä haettu tulostaulu
		 * @return Asiakas asiakas-olio
		 */
		private Asiakas readAsiakas(ResultSet resultset) {	
			try {
				// Haetaan yhden asiakkaan tiedot kyselyn tulostaulun (ResultSet-tyyppinen resultset-olion) aktiiviselta tietoriviltä
				int id = resultset.getInt("id");
				String etunimi = resultset.getString("firstname");
				String sukunimi = resultset.getString("lastname");
				int syntymavuosi = resultset.getInt("birthyear");
				String sukupuoli = resultset.getString("sex");
				String katuosoite = resultset.getString("streetaddress");
				String postinro = resultset.getString("postcode");
				String email = resultset.getString("email");
				double bonuspisteet = resultset.getDouble("bonusscore");
			
			// Luodaan ja palautetaan uusi asiakas
				return new Asiakas(id, etunimi, sukunimi, syntymavuosi, sukupuoli, katuosoite, postinro, email, bonuspisteet);
			} catch (SQLException e) {
				e.printStackTrace(); // consoleen näkyviin Exception-tilanteen tarkemmat tiedot vianjäljitystä varten
				throw new RuntimeException(e);
			}
		}
				
	
		/**
		 * Lisää asiakkaan tiedot tietokantaan
		 * 
		 * @param asiakas
		 *            Asiakas-luokan olio
		 * @throws SQLException 
		 */
		/*
		public boolean addAsiakas(Asiakas asiakas)  {
			Connection connection = null;
			PreparedStatement stmtInsert = null;
			boolean updateSuccessed = false; 
		

			try {
				// Luodaan tietokantayhteys
				connection = getDBConnection();
				// Luodaan komento: luodaan uusi asiakas tietokannan tauluun
				String sqlInsert = 
				"INSERT INTO customer (firstname, lastname, birthyear, sex, streetaddress, postcode, email, bonusscore) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				// Valmistellaan komento:
				stmtInsert = connection.prepareStatement(sqlInsert);
				// Asetetaan parametrisoidun komennon parametrit yksi kerrallaan 
		        // customer-taulussa id-sarake auto_increment-tyyppinen
				stmtInsert.setString(1, asiakas.getEtunimi());
				stmtInsert.setString(2, asiakas.getSukunimi());
				stmtInsert.setInt(3, asiakas.getSyntymavuosi());
				stmtInsert.setString(4, asiakas.getSukupuoli());
				stmtInsert.setString(5, asiakas.getKatuosoite());
				stmtInsert.setString(6, asiakas.getPostinro());
				stmtInsert.setString(7, asiakas.getEmail());
				stmtInsert.setDouble(8, asiakas.getBonuspisteet());
				//Lähetetään INSERT-komento suoritettavaksi tietokantapalvelimelle
				int rowAffected = stmtInsert.executeUpdate();
				if (rowAffected == 1) updateSuccessed = true;
				
			} catch (SQLException e) {
				e.printStackTrace(); // consoleen näkyviin Exception-tilanteen tarkemmat tiedot vianjäljitystä varten
				throw new RuntimeException(e);
			} finally {
				closeDBConnection(stmtInsert, connection); // Suljetaan statement ja yhteys
			}
			return updateSuccessed;
		}
		*/
		
		// addAsiakas-metodi lisää parametrina saadun asiakkaan tiedot customer-tauluun uudeksi tietoriviksi
		public boolean addAsiakas(Asiakas asiakas) {
			boolean executionSuccessed = false;
			Connection connection = null; 
			String sql = null; 
			PreparedStatement stmtInsert = null;
			
			try {
				connection = getDBConnection();
				sql = "INSERT INTO customer " + 
						"(firstname, lastname, birthyear, sex, streetaddress, postcode, email, bonusscore) " + 
						"VALUES " + 
						"(?, ?, ?, ?, ?, ?, ?, ?)";
				
				stmtInsert = connection.prepareStatement(sql);
				stmtInsert.setString(1, asiakas.getEtunimi());
				stmtInsert.setString(2, asiakas.getSukunimi());
				stmtInsert.setInt(3, asiakas.getSyntymavuosi());
				stmtInsert.setString(4, asiakas.getSukupuoli());
				stmtInsert.setString(5, asiakas.getKatuosoite());
				stmtInsert.setString(6, asiakas.getPostinro());
				stmtInsert.setString(7, asiakas.getEmail());
				stmtInsert.setDouble(8, asiakas.getBonuspisteet());
				
				int rowAffected = stmtInsert.executeUpdate();
				if (rowAffected == 1) executionSuccessed = true;
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				throw new RuntimeException();
			}
			finally {
				closeDBConnection(stmtInsert, connection);
			}
			
		   return executionSuccessed;
			
		}
		
		 // removeAsiakas()-metodipoistaa tietokannasta yhden yhden asiakkaan parametrina saadun asiakasid-arvon perusteella
		
		public boolean removeAsiakas(int asiakastunnus) {
			
			boolean executionSuccessed = false;
			Connection connection = null;
			PreparedStatement stmtDelete = null;
			String sql = "delete from customer where id = ?;";
			
			try {
				connection = getDBConnection();
				stmtDelete = connection.prepareStatement(sql);
				stmtDelete.setInt(1, asiakastunnus);
				int rowAffected = stmtDelete.executeUpdate();
				if (rowAffected == 1) {
					 executionSuccessed = true;
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
				throw new RuntimeException();
				
			} finally {
				closeDBConnection(stmtDelete, connection);
			}
			
			
			return executionSuccessed;
		}
		
		
		
		

		/**
		 * Poistaa asiakkaan tiedot tietokannasta
		 * 
		 * @param asiakasId
		 *            poistettavan asiakkaan id-arvo
		 * @throws SQLException 
		 */
	/*	
		public boolean removeAsiakas(int asiakasId) {
			Connection connection = null;
			PreparedStatement stmtDelete = null;
			boolean updateSuccessed = false;

			try {
				// Luodaan yhteys
				connection = getDBConnection();
				//Poistetaan henkilo tietokantasta:
				String sql = "DELETE FROM customer WHERE id = ?";
				stmtDelete = connection.prepareStatement(sql);
				stmtDelete.setInt(1, asiakasId);
				//Lähetetään DELETE-komento suoritettavaksi tietokantapalvelimelle
				int rowAffected = stmtDelete.executeUpdate();
				if (rowAffected == 1) updateSuccessed = true;
				
			} catch (SQLException e) {
				e.printStackTrace(); // consoleen näkyviin Exception-tilanteen tarkemmat tiedot vianjäljitystä varten
				throw new RuntimeException(e);
			} finally {
				closeDBConnection(stmtDelete, connection); // Suljetaan statement ja yhteys
			}
			return updateSuccessed;
		}
*/

	
			
		
	}

