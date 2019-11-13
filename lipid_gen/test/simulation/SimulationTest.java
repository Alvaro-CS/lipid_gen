package simulation;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Fatty_acid;
import model.Lipid;
import model.Skeleton;

class SimulationTest {
//TODO double doesn't work as we want??

	void testFaSke() throws Exception {
		Simulation s = new Simulation();

		ArrayList<Fatty_acid> FAs = s.FAcreator();
		for (Fatty_acid fa : FAs) {
			System.out.println(fa);
		}
		System.out.println("Number of FAs: " + FAs.size());
		ArrayList<Skeleton> SKEs = s.SKEcreator();
		for (Skeleton ske : SKEs) {
			System.out.println(ske);
		}
		System.out.println("Number of SKEs: " + SKEs.size());
	}

	@Test
	void testLipid() throws Exception {
		Simulation s = new Simulation();

		ArrayList<Lipid> LIPIDs = s.LIPIDcreator();
		for (Lipid l : LIPIDs) {
			System.out.println(l.toStringName());
		}
		System.out.println(LIPIDs.size());
		// Lipids with 1= 10*228+228=2508.
	}

}
