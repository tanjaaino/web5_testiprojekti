package control;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Asiakas;
import model.dao.AsiakasDAO;


@WebServlet("/listaa-asiakkaat")
public class ListaaAsiakkaatServlet extends HttpServlet {

	/**
	 * Lähettää selaimelle asiakaslista

	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	

		// Luodaan asikasDAO ja haetaan kaikki asiakkaat tietokannan taulusta
		AsiakasDAO asiakasdao = new AsiakasDAO();
		List<Asiakas> asiakkaat = asiakasdao.findAll();

		// Talletetaan request-olion alle asiakaslista, jotta tiedot ovat käytössä jsp:llä
		request.setAttribute("asiakkaat", asiakkaat);   

		// lähetä selaimelta tullut pyyntö servletiltä edelleen sopivalle jsp:lle
		request.getRequestDispatcher("/WEB-INF/asiakaslista.jsp").forward(request, response);

	}

}
