package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LipidTest {

	@Test
	void testLipid() {
		//fail("Not yet implemented");
		
	}
	
	@Test
	void testLipidConstructorNull() {
		Lipid expectedResult = null;
		try {
			Lipid result = new Lipid(null,null);
			fail("A new Lipid with null values does not return a NullPointerException");
		}
		catch (NullPointerException npe){
			assertTrue(Boolean.TRUE);
		}	
	}
	
	

}
