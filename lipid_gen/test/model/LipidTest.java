package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import utilities.Periodic_table;

class LipidTest {

	@Test
	void testLipid() throws Exception {
		List<Fatty_acid> FAs = new ArrayList<Fatty_acid>();
		FAs.add(new Fatty_acid(20, 2));
		FAs.add(new Fatty_acid(30, 5));
		FAs.add(new Fatty_acid(36, 6));

		Skeleton skeleton = new Skeleton(Ske_type.TG);
		Lipid lipid = new Lipid(skeleton, FAs);
//TODO check this
		System.out.println(lipid.getAbbvName());
		System.out.println(lipid.getDoubleBonds());
		System.out.println(lipid.getLength());
		System.out.println(lipid.getName());
		double FAs_mass = lipid.calculateMass(FAs.get(0).getFormula()) + lipid.calculateMass(FAs.get(1).getFormula())
				+ lipid.calculateMass(FAs.get(2).getFormula());
		double ske_mass = lipid.calculateMass(skeleton.getFormula());
		double mass = FAs_mass + ske_mass;
		System.out.println(mass - Periodic_table.MAPELEMENTS.get(Element.H) * 6);
		System.out.println(lipid.getMass());
		System.out.println(lipid.getFormula());
		assertTrue(true);
	}

	@Test
	void testLipidFalseSke() throws Exception {
		List<Fatty_acid> FAs = new ArrayList<Fatty_acid>();
		FAs.add(new Fatty_acid(20, 2));
		FAs.add(new Fatty_acid(30, 5));
		FAs.add(new Fatty_acid(36, 6));

		Skeleton skeleton = new Skeleton(Ske_type.MG);
		try {
			Lipid lipid = new Lipid(skeleton, FAs);
			fail("DG need 2 FAs, not 3.");
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	void testLipidConstructorNull() throws Exception {
		try {
			Lipid result = new Lipid(null, null);
			fail("A new Lipid with null values does not return a NullPointerException");
		} catch (NullPointerException npe) {
			assertTrue(Boolean.TRUE);
		}
	}

	@Test
	void testLipidFA8Mass() throws Exception {

		Fatty_acid fa = new Fatty_acid(8, 0);
		Lipid li = new Lipid(fa);
		Double mass = li.getMass();
		if (mass == 144.11503d)
			assertTrue(Boolean.TRUE);
		else
			fail("Mass doesn't match");
	}

	@Test
	void testLipidFADBMass() throws Exception {

		Fatty_acid fa = new Fatty_acid(15, 4);
		Lipid li = new Lipid(fa);
		Double mass = li.getMass();
		if (mass == 234.16198d)
			assertTrue(Boolean.TRUE);
		else
			fail("Mass doesn't match");
	}

}
