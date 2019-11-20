package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Exceptions.InvalidFASizeException;
import utilities.Periodic_table;

class LipidTest {

	@Test
	void testLipidMass() throws Exception {
		List<Fatty_acid> FAs = new ArrayList<Fatty_acid>();
		FAs.add(new Fatty_acid(20, 2));
		FAs.add(new Fatty_acid(30, 5));
		FAs.add(new Fatty_acid(36, 6));

		Skeleton skeleton = new Skeleton(Ske_type.TG);
		Lipid lipid = new Lipid(skeleton, FAs);
		double FAs_mass = FAs.get(0).getMass() + FAs.get(1).getMass() + FAs.get(2).getMass();
		double ske_mass = skeleton.getMass();
		double mass = FAs_mass + ske_mass;
		mass = mass - Periodic_table.MAPELEMENTS.get(Element.H) * 6;
		if (mass == lipid.getMass()) {
			assertTrue(true);
		} else {
			fail("Mass doesn't match.");
		}
	}

	@Test
	void testLipidDoubleBonds() throws Exception {
		List<Fatty_acid> FAs = new ArrayList<Fatty_acid>();
		FAs.add(new Fatty_acid(20, 2));
		FAs.add(new Fatty_acid(10, 2));

		Skeleton skeleton = new Skeleton(Ske_type.CE);
		Lipid lipid = new Lipid(skeleton, FAs);
		int db = FAs.get(0).getDouble_bonds() + FAs.get(1).getDouble_bonds();
		if (db == lipid.getDoubleBonds()) {
			assertTrue(true);
		} else {
			fail("Double bonds don't match.");
		}
	}

	@Test
	void testLipidLength() throws Exception {
		List<Fatty_acid> FAs = new ArrayList<Fatty_acid>();
		FAs.add(new Fatty_acid(20, 2));
		FAs.add(new Fatty_acid(10, 2));

		Skeleton skeleton = new Skeleton(Ske_type.CE);
		Lipid lipid = new Lipid(skeleton, FAs);
		int length = FAs.get(0).getC() + FAs.get(1).getC();
		if (length == lipid.getLength()) {
			assertTrue(true);
		} else {
			fail("Length doesn't match.");
		}
	}

	@Test
	void testLipidFalseSke() throws Exception {
		List<Fatty_acid> FAs = new ArrayList<Fatty_acid>();
		FAs.add(new Fatty_acid(20, 2));
		FAs.add(new Fatty_acid(30, 5));
		FAs.add(new Fatty_acid(36, 6));

		Skeleton skeleton = new Skeleton(Ske_type.DG);
		try {
			Lipid lipid = new Lipid(skeleton, FAs);
			fail("DG need 2 FAs, not 3.");
		} catch (InvalidFASizeException e) {
			assertTrue(true);
		}
	}

	@Test
	void testFalseSke2() throws Exception {
		List<Fatty_acid> FAs = new ArrayList<Fatty_acid>();
		FAs.add(new Fatty_acid(20, 2));

		Skeleton skeleton = new Skeleton(Ske_type.PA);
		try {
			Lipid lipid = new Lipid(skeleton, FAs);
			assertTrue(true);

		} catch (InvalidFASizeException e) {
			fail("PA can have 1 or 2 FAs");

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

		Fatty_acid fa = new Fatty_acid(9, 0);
		Lipid li = new Lipid(fa);
		Double mass = li.getMass();
		if (mass == 158.13068d)
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
