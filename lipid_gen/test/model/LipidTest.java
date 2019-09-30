package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exceptions.Fatty_acidCreationException;

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
	@Test
	void testLipid8Mass() throws Fatty_acidCreationException {
		
		Fatty_acid fa=new Fatty_acid(8, 0);
		Lipid li=new Lipid(fa);
		Double mass=li.getMass();
		if(mass==144.11503d)assertTrue(Boolean.TRUE); else fail("Mass doesn't match");
	}
	
	@Test
	void testLipidDBMass() throws Fatty_acidCreationException {
		
		Fatty_acid fa=new Fatty_acid(15, 4);
		Lipid li=new Lipid(fa);
		Double mass=li.getMass();
		if(mass==234.16198d)assertTrue(Boolean.TRUE); else fail("Mass doesn't match");
	}
	
	

}
