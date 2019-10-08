package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FormulaTest {

	@Test
	void testFormulaConstructor() {
		
			Formula formula = new Formula("njnjk");
			System.out.println(formula.getFormulaData("Ca3H6L2oo(CH)2P2"));
			assertTrue(Boolean.TRUE);
	
	}

}
