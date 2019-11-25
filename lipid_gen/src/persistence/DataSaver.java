package persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Fatty_acid;
import model.Lipid;
import simulation.Simulation;

//TODO rendimiento
public class DataSaver {
	public static void main(String[] args) {
		Simulation s = new Simulation();
		SQLManager sql = new SQLManager();
		sql.connect();
		sql.create();
		Scanner scan = new Scanner(System.in);
		String algo = scan.next();
		ArrayList<Fatty_acid> FAs = s.FAcreator();
		for (Fatty_acid f : FAs) {
			sql.insertFA(f);
		}
		ArrayList<Lipid> LIPIDs;
		try {
			LIPIDs = s.LIPIDcreator();

			for (Lipid l : LIPIDs) {
				int compound_id = sql.insertLipid(l);// it inserts the lipid and gets the id it has been set
				sql.insertLipid_clas(l);
				List<Fatty_acid> lipid_fas = l.getFAs();
				for (Fatty_acid fa : lipid_fas) {
					int chain_id = sql.getFAid(fa);
					int number_chains = 1;
					if (sql.checkLipidFAConnection(compound_id, chain_id)) {
						number_chains = sql.getNumber_chains(compound_id, chain_id) + 1;
						sql.updateNumber_chains(compound_id, chain_id, number_chains);

					} else {
						sql.connectChainsLipid(number_chains, compound_id, chain_id);
					}
				}

			}
			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
		sql.disconnect();
	}

}
