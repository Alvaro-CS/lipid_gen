package utilities;

import java.util.HashMap;
import java.util.Map;

import model.Ske_type;

public class Ske_type_FAs {
	public static final Map<Ske_type, Integer> MAPFAS = new HashMap<Ske_type, Integer>();

	static {

		MAPFAS.put(Ske_type.CE, 2);
		MAPFAS.put(Ske_type.CER, 1);
		MAPFAS.put(Ske_type.DG, 2);
		MAPFAS.put(Ske_type.MG, 1);
		MAPFAS.put(Ske_type.PA, 2);
		MAPFAS.put(Ske_type.PC, 2);
		MAPFAS.put(Ske_type.PE, 2);
		MAPFAS.put(Ske_type.PI, 2);
		MAPFAS.put(Ske_type.PG, 2);
		MAPFAS.put(Ske_type.PS, 2);
		MAPFAS.put(Ske_type.SM, 1);
		MAPFAS.put(Ske_type.TG, 3);

	}
}
