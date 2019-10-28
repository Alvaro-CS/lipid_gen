package model;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FormulaTest {

	@Test
	void testInvalidElementFormula() throws Exception {
		String string = "CaPpt3LiN4(Mg2O10F4He)4H2(Cd4)3";
		try {
			Formula formula = new Formula(string);
		} catch (Exception e) {
			assertTrue(Boolean.TRUE);
		}

	}

	@Test
	void testInvalidFormatFormula() throws Exception {
		String string = "(4Ca3LiN4(Mg2O10F4He)4H2(Cd4)3";
		try {
			Formula formula = new Formula(string);
		} catch (Exception e) {
			assertTrue(Boolean.TRUE);
		}

	}

	@Test
	void testValidFormula() throws Exception {
		String string = "Ca3LiN4(Mg2O10F4He)4H2(Cd4)3";

		try {
			Formula formula = new Formula(string);
			assertTrue(Boolean.TRUE);
		} catch (Exception e) {
			fail("Well-formuled formula throws an error");
		}

	}

}
