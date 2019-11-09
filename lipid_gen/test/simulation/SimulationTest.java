package simulation;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Fatty_acid;
import model.Skeleton;

class SimulationTest {

	@Test
	void test() throws Exception {
		Simulation s = new Simulation();

		ArrayList<Fatty_acid> FAs = s.FAcreator();
		for (Fatty_acid fa : FAs) {
			System.out.println(fa);
		}
		System.out.println("Number of FAs: " + FAs.size());
		ArrayList<Skeleton> SKEs = s.SKEcreator();
		for (Skeleton ske : SKEs) {
			// System.out.println(ske);
		}
		// System.out.println("Number of SKEs: " + SKEs.size());
	}

}
