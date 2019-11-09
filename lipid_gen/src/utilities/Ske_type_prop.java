package utilities;

import java.util.HashMap;
import java.util.Map;

import model.Characteristics;
import model.Formula;
import model.Ske_type;

public class Ske_type_prop {
	public static final Map<Ske_type, Characteristics> MAPSKE = new HashMap<Ske_type, Characteristics>();

	static {
		try {
			MAPSKE.put(Ske_type.CE, new Characteristics(new Formula("C28H46O2"), 1, 2));
			MAPSKE.put(Ske_type.CER, new Characteristics(new Formula("C19H37NO3"), 1));
			MAPSKE.put(Ske_type.DG, new Characteristics(new Formula("C5H8O5"), 2));
			MAPSKE.put(Ske_type.MG, new Characteristics(new Formula("C4H8O4"), 1));
			MAPSKE.put(Ske_type.PA, new Characteristics(new Formula("C5H9O8P"), 1, 2));
			MAPSKE.put(Ske_type.PC, new Characteristics(new Formula("C10H20NO8P"), 1, 2));
			MAPSKE.put(Ske_type.PE, new Characteristics(new Formula("C7H14NO8P"), 1, 2));
			MAPSKE.put(Ske_type.PI, new Characteristics(new Formula("C11H19O13P"), 1, 2));
			MAPSKE.put(Ske_type.PG, new Characteristics(new Formula("C8H15O10P"), 1, 2));
			MAPSKE.put(Ske_type.PS, new Characteristics(new Formula("C8H14NO10P"), 1, 2));
			MAPSKE.put(Ske_type.SM, new Characteristics(new Formula("C24H49N2O6P"), 1));
			MAPSKE.put(Ske_type.TG, new Characteristics(new Formula("C6H8O6"), 3));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
