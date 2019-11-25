package simulation;

import java.util.ArrayList;

import Exceptions.InvalidFASizeException;
import model.Fatty_acid;
import model.Lipid;
import model.Ske_type;
import model.Skeleton;
import utilities.Ske_type_prop;

public class Simulation {
	public Simulation() {
	}

//TODO calculate time in each for
	public ArrayList<Lipid> LIPIDcreator() throws NullPointerException, InvalidFASizeException {
		ArrayList<Lipid> LIPIDs = new ArrayList<Lipid>();
		ArrayList<Fatty_acid> FAs = FAcreator();
		ArrayList<Skeleton> SKEs = SKEcreator();
		for (Fatty_acid fa : FAs) {// It adds to the list all lipids that consist on a single Fatty Acid
			Lipid lipid = new Lipid(fa);
			LIPIDs.add(lipid);
		}
		for (Skeleton ske : SKEs) {
			if (Ske_type_prop.MAPSKE.get(Ske_type.valueOf(ske.getSke_type().toString())).getMinFAs() == 1) {
				for (int i = 0; i < FAs.size(); i++) {
					ArrayList<Fatty_acid> fas = new ArrayList<Fatty_acid>();
					fas.add(FAs.get(i));
					Lipid lipid = new Lipid(ske, fas);
					LIPIDs.add(lipid);
				}
			}
			if (Ske_type_prop.MAPSKE.get(Ske_type.valueOf(ske.getSke_type().toString())).getMaxFAs() == 2) {
				for (int i = 0; i < FAs.size(); i++) {
					for (int j = i; j < FAs.size(); j++) {
						ArrayList<Fatty_acid> fas = new ArrayList<Fatty_acid>();
						fas.add(FAs.get(i));
						fas.add(FAs.get(j));
						Lipid lipid = new Lipid(ske, fas);
						LIPIDs.add(lipid);
					}
				}
			}
			if (Ske_type_prop.MAPSKE.get(Ske_type.valueOf(ske.getSke_type().toString())).getMaxFAs() == 3) {
				for (int i = 0; i < FAs.size(); i++) {
					for (int j = i; j < FAs.size(); j++) {
						for (int k = j; k < FAs.size(); k++) {

							ArrayList<Fatty_acid> fas = new ArrayList<Fatty_acid>();
							fas.add(FAs.get(i));
							fas.add(FAs.get(j));
							fas.add(FAs.get(k));
							Lipid lipid = new Lipid(ske, fas);
							LIPIDs.add(lipid);
						}

					}
				}
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
