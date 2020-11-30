package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Asiakas;
import model.dao.AsiakasDAO;

@WebServlet("/lisaa-uusiasiakas")
public class LisaaUusiAsiakasServlet extends HttpServlet {

	/*
	 * Lähettää selaimelle asiakkaan tietojen tyhjän lisäyslomake
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/asiakas-lomake.jsp").forward(request, response);
	}

	/*
	 * Vastaanottaa tietoa selaimelta: Otetaan lomakkeella syötetyn asiakkaan tiedot
	 * request (pyyntö)-olion parametritiedoista ja luodaan saaduista tiedoista
	 * Asiakas-luokan olio
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Pyydetään lomakkeella syötetyn asiakkaan tiedot request-oliolta

		try {

			String etunimi = request.getParameter("etunimi"); // etunimi
			String sukunimi = request.getParameter("sukunimi"); // sukunimi

			String syntymavuosiStr = request.getParameter("syntymavuosi"); // syntymavuosi
			int syntymavuosi = Integer.parseInt(syntymavuosiStr);

			String katuosoite = request.getParameter("katuosoite"); // katuosoite
			String postinro = request.getParameter("postinro"); // postinro
			String sukupuoli = request.getParameter("sukupuoli"); // katuosoite
			String email = request.getParameter("email"); // postinro
			String bonuspisteetStr = request.getParameter("bonuspisteet"); // bonuspisteet
			bonuspisteetStr = bonuspisteetStr.replace(",", ".");
			double bonuspisteet = Double.parseDouble(bonuspisteetStr);

			// Luodaan uusi Asiakas-luokan olio edellisillä parametreillä
			Asiakas asiakas = new Asiakas(etunimi, sukunimi, syntymavuosi, sukupuoli, katuosoite, postinro, email,
					bonuspisteet);
			System.out.println(asiakas);
			
			AsiakasDAO asiakasdao = new AsiakasDAO();
			// tallennetaan tiedot tietokantaan 
			boolean lisaysOnnistui = asiakasdao.addAsiakas(asiakas);
			if (lisaysOnnistui == true)
				// uudelleenohjaus /listaa-asiakkaat endpointtiin  .jps-käsittelyn sijaan 
				response.sendRedirect("/listaa-asiakkaat"); 
			else {

				request.setAttribute("viesti", "Asiakkaan lisäyksessä tietokantaan tapahtui virhe.");
				// servlet kutsuu jsp:tä
				request.getRequestDispatcher("/WEB-INF/tapahtumaraportti.jsp").forward(request, response);
			}

		} catch (NumberFormatException e) {
			
			e.printStackTrace();  // tulostetaan Consoleen virhetilanteessa metodikutsupinoa, josta näkee rivinumeron, jossa Exception tapahtuu

			request.setAttribute("viesti", "Asiakas-lomakkeella syötetyt tiedot eivät olleet kelvolliset.");
			// servlet kutsuu jsp:tä
			request.getRequestDispatcher("/WEB-INF/tapahtumaraportti.jsp").forward(request, response);
		}
	}
}
