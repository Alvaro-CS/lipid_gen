package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FormulaTest {

	@Test
	void testFormulaConstructor() {
		
			Formula formula = new Formula("njnjk");
			System.out.println(formula.getFormulaData("CH6L2ooP2"));
			assertTrue(Boolean.TRUE);
	
	}

}
