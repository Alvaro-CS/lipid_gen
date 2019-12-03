package persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Fatty_acid;
import model.Lipid;
import simulation.Simulation;

public class DataSaver {
	public static void main(String[] args) {
		Simulation s = new Simulation();
		SQLManager sql = new SQLManager();
		System.out.println("Type where do you want to get the user and password from:");
		Scanner teclado = new Scanner(System.in);
		String archivo = teclado.nextLine();
		teclado.close();
		sql.connect(archivo);
		ArrayList<Fatty_acid> FAs = s.FAcreator();
		for (Fatty_acid f : FAs) {
			sql.insertFA(f);
		}
		ArrayList<Lipid> LIPIDs;
		try {
			LIPIDs = s.LIPIDcreator();

			for (Lipid l : LIPIDs) {
				int compound_id = sql.insertLipid(l);// it inserts the lipid and gets the id it has been set
				sql.insertLipid_clas(l, compound_id);
				List<Fatty_acid> lipid_fas = l.getFAs();
				if (lipid_fas.size() != 1) {
					Map<Fatty_acid, Integer> mapFAs = getFaCount(lipid_fas);
					for (Fatty_acid fa : mapFAs.keySet()) {
						int chain_id = sql.getFAid(fa);
						sql.connectChainsLipid(mapFAs.get(fa), compound_id, chain_id);
					}
				} else {
					int chain_id = sql.getFAid(lipid_fas.get(0));
					sql.connectChainsLipid(1, compound_id, chain_id);

				}

			}
			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
		sql.disconnect();
	}

	public static Map<Fatty_acid, Integer> getFaCount(List<Fatty_acid> FAs) {
		Map<Fatty_acid, Integer> FaCounter = new HashMap<>();
		for (Fatty_acid fa : FAs) {
			Integer val = FaCounter.get(fa);
			if (FaCounter.get(fa) == null) {
				FaCounter.put(fa, 1); // if map does not contains key, its value is 1
			} else {
				val++;
				FaCounter.put(fa, val); // we increment the counter if map already has element
			}
		}

		return FaCounter;
	}

}
