package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.YearMonth;

import org.junit.jupiter.api.Test;

class AsiakasTest {

	@Test
	void getIkaTestKelvollinenSyntymavuosi() {
		int syntymavuosi = 1990;
		Asiakas asiakas = new Asiakas("Aava", "Aavanen", syntymavuosi, "N", "Merikatu 25", "00100",
				"aava.aavanen@mail.com", 25.5);
		int ika = YearMonth.now().getYear() - 1990;
		assertEquals(ika, asiakas.getIka());

	}

	
	@Test
	void getIkaTestKelvotonSyntymavuosi() {
		int syntymavuosi = 3000;
		Asiakas asiakas = new Asiakas("Aava", "Aavanen", syntymavuosi, "N", "Merikatu 25", "00100",
				"aava.aavanen@mail.com", 25.5);
		assertEquals(0, asiakas.getIka());

	}
}
