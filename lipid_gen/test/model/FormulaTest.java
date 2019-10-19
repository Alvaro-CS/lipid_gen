package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FormulaTest {

	@Test
	void testFormulaConstructor() {
		String regex = "Ca3LiRo4(Xb2O)3H2(Cd4)2";
		Formula formula = new Formula("njnjk");
		System.out.println(formula.isValidFormula(regex));
		System.out.println(formula.getFormulaData(regex));
		assertTrue(Boolean.TRUE);

	}

}
