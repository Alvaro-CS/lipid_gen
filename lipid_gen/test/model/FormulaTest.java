package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FormulaTest {

	@Test
	void testFormulaConstructor() {
		String regex = "Ca3LiN4(Mg2O10He)3H2(Cd4)2";
		Formula formula = new Formula("njnjk");
		System.out.println(formula.isValidFormula(regex));
		// System.out.println(formula.getFormulaSPData(regex));
		// System.out.println(formula.getFormulaPGData(regex));

		assertTrue(Boolean.TRUE);

	}

}
