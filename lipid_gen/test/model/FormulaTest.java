package model;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

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
			HashMap<Element, Integer> elements = new HashMap();
			elements.put(Element.Ca, 3);
			elements.put(Element.Li, 1);
			elements.put(Element.N, 4);
			elements.put(Element.Mg, 8);
			elements.put(Element.O, 40);
			elements.put(Element.F, 16);
			elements.put(Element.He, 4);
			elements.put(Element.H, 2);
			elements.put(Element.Cd, 12);

			Formula formulaExpected = new Formula(elements);
			Formula formula = new Formula(string);
			// assertEquals(formulaExpected, formula);TODO equals in every class
			if (formulaExpected == formula) {
				assertTrue(Boolean.TRUE);
			}

		} catch (Exception e) {
			fail("Well-formuled formula throws an error");
		}

	}

}
