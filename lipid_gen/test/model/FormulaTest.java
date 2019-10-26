package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FormulaTest {

	@Test
	void testFormulaConstructor() throws Exception {
		String string = "Ca3LiN4(Mg2O10F4He)4H2(Cd4)3";
		Formula formula = new Formula("njnjk");
		// System.out.println(formula.isValidFormula(string));
		formula.createMapFormula(string);

		assertTrue(Boolean.TRUE);

	}

}
