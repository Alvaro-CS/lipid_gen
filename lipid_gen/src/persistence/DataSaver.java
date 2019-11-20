package persistence;

import java.util.ArrayList;
import java.util.List;

import model.Fatty_acid;
import model.Lipid;
import simulation.Simulation;

public class DataSaver {
	public static void main(String[] args) {
		Simulation s = new Simulation();
		SQLManager sql = new SQLManager();
		sql.connect();
		ArrayList<Fatty_acid> FAs = s.FAcreator();
		for (Fatty_acid f : FAs) {
			sql.insertFA(f);
		}
		ArrayList<Lipid> LIPIDs;
		try {
			LIPIDs = s.LIPIDcreator();
			for (Lipid l : LIPIDs) {
				int compound_id = sql.insertLipid(l);// it inserts the lipid and gets the id it has been set

				List<Fatty_acid> lipid_fas = l.getFAs();
				if (lipid_fas.size() != 0) {// If the lipid isn't a single fa, we need to connect the whole lipid with
											// its FAs
					if (lipid_fas.size() > 1) {

					}
					// For, ir insertando
					// TODO sql.connectChainsLipid(number_chains, compound_id, chain_id);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		sql.disconnect();
	}

}
