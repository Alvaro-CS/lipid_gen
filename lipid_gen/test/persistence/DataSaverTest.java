package persistence;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

import Exceptions.Fatty_acidCreationException;
import Exceptions.InvalidFASizeException;
import model.Fatty_acid;
import model.Lipid;
import model.Ske_type;
import model.Skeleton;

class DataSaverTest {
//TODO temporal
	@Test
	void testHashMap() throws Fatty_acidCreationException, NullPointerException, InvalidFASizeException {
		ArrayList<Fatty_acid> FAs = new ArrayList<Fatty_acid>();
		FAs.add(new Fatty_acid(10, 2));
		FAs.add(new Fatty_acid(10, 2));
		FAs.add(new Fatty_acid(10, 1));
		Skeleton ske = new Skeleton(Ske_type.TG);
		Lipid l = new Lipid(ske, FAs);
		Map<Fatty_acid, Integer> mapFAs = DataSaver.getFaCount(l.getFAs());
		for (Fatty_acid fa : mapFAs.keySet()) {
			System.out.println(fa + ":" + mapFAs.get(fa));
		}
	}

}
