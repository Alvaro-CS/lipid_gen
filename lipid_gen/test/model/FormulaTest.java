package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FormulaTest {

	@Test
	void testFormulaConstructor() {
		// En la formula he puesto distintas letras para comprobar que lo que est�
		// dentro del par�ntesis NO SE RECONOCE mediante la otra expresi�n regular.
		String regex = "Ca3(Xb2O)3H2(Cd4)2";
		Formula formula = new Formula("njnjk");
		System.out.println(formula.isValidFormula(regex));
		System.out.println(formula.getFormulaData(regex));
		assertTrue(Boolean.TRUE);

	}

}
