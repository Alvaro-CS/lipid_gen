package simulation;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Fatty_acid;
import model.Lipid;
import model.Skeleton;

class SimulationTest {

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

	void testLipid() throws Exception {
		Simulation s = new Simulation();

		ArrayList<Lipid> LIPIDs = s.LIPIDcreator();
		for (Lipid l : LIPIDs) {
			System.out.println(l.toStringName());
		}

	}

	void testSpeed() throws Exception {
		Simulation s = new Simulation();
		long total_time = 0;
		for (int n = 0; n < 10; n++) {
			long start = System.currentTimeMillis();
			ArrayList<Lipid> LIPIDs = s.LIPIDcreator();
			long end = System.currentTimeMillis();
			long time = end - start;
			System.out.println(time);
			total_time += time;
		}
		total_time = total_time / 10;
		System.out.println("Total=" + total_time); // Aprox 15235ms
		// TODO do another different test for size

	}

	@Test
	void testLipidSize() throws Exception {
		Simulation s = new Simulation();

		ArrayList<Lipid> LIPIDs = s.LIPIDcreator();
		System.out.println("Total size " + LIPIDs.size());
		// Lipids with 1= 10*228+228=2508
		// Lipids with 2=8 Combination with repetition 26.106*8=208.848
		// Lipids with 3=1 Combination with repetition 2.001.460

	}

}
