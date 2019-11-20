package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.TreeMap;

import org.junit.jupiter.api.Test;

import utilities.ElementsComparator;

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
		String string = "Ca3LiN4(Mg2O10F4C)4H2(Cd4)3";

		try {
			TreeMap<Element, Integer> elements = new TreeMap<Element, Integer>(new ElementsComparator());
			elements.put(Element.Ca, 3);
			elements.put(Element.Li, 1);
			elements.put(Element.N, 4);
			elements.put(Element.Mg, 8);
			elements.put(Element.O, 40);
			elements.put(Element.F, 16);
			elements.put(Element.C, 4);
			elements.put(Element.H, 2);
			elements.put(Element.Cd, 12);

			Formula formulaExpected = new Formula(elements);
			Formula formula = new Formula(string);
			assertEquals(formulaExpected, formula);

		} catch (Exception e) {
			fail("Well-formuled formula throws an error");
		}

	}

}
