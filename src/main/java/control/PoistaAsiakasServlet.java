package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AsiakasDAO;

@WebServlet("/poista-asiakas")
public class PoistaAsiakasServlet extends HttpServlet {
	

	/**
	 * Vastaanottaa selaimelta tulleen asiakkaan poistopyynnön
	 * 
	 * @param request
	 *            pyyntö
	 * @param response
	 *            vastaus
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// Sijoitetaan muuttujaan pyynnön parametrina tullut poistettavan asiakkaan id-arvo
			String idStr = request.getParameter("asiakasid");
			int asiakasId = new Integer(idStr);
			// Luodaan asiakasdao
			AsiakasDAO asiakasdao = new AsiakasDAO();
			// Poistetaan asiakkaan tiedot tietokannasta
			asiakasdao.removeAsiakas(asiakasId);
			// uudelleenohjataan pyyntö /listaa-asiakkaat-endpointiin
			response.sendRedirect("/listaa-asiakkaat");
			
		} catch (Exception e) {
			e.printStackTrace();	
			request.setAttribute("viesti", "Sovelluksessa tapahtui virhe,");
			// servlet kutsuu jsp:tä
			request.getRequestDispatcher("/WEB-INF/tapahtumaraportti.jsp").forward(request, response);
		}

		
	}

}
