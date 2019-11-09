package simulation;

import java.util.ArrayList;

import model.Fatty_acid;
import model.Lipid;
import model.Ske_type;
import model.Skeleton;

public class Simulation {
	public Simulation() {
	}

	public ArrayList<Lipid> LIPIDcreator() {
		ArrayList<Lipid> LIPIDs = new ArrayList<Lipid>();
		ArrayList<Fatty_acid> FAs = FAcreator();
		ArrayList<Skeleton> SKEs = SKEcreator();
		for (Skeleton ske : SKEs) {
			for (Fatty_acid fa : FAs) {

			}
		}

		return LIPIDs;
	}

	public ArrayList<Fatty_acid> FAcreator() {
		ArrayList<Fatty_acid> FAs = new ArrayList<Fatty_acid>();

		for (int c = 3; c < 37; c++) {
			for (int db = 0; db < 7; db++) {
				try {
					Fatty_acid fa = new Fatty_acid(c, db);
					FAs.add(fa);
				} catch (Exception e) {
					continue;
				}

			}
		}

		return FAs;
	}

	public ArrayList<Skeleton> SKEcreator() {
		ArrayList<Skeleton> SKEs = new ArrayList<Skeleton>();
		for (Ske_type ske_type : Ske_type.values()) {
			Skeleton ske = new Skeleton(ske_type);
			SKEs.add(ske);
		}
		return SKEs;
	}
}
