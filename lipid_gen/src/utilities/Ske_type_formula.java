package utilities;

import java.util.HashMap;
import java.util.Map;

import model.Formula;
import model.Ske_type;

public class Ske_type_formula {
	public static final Map<Ske_type, Formula> MAPFORMULA = new HashMap<Ske_type, Formula>();

	static {

		try {
			MAPFORMULA.put(Ske_type.CE, new Formula("C28H46O2"));
			MAPFORMULA.put(Ske_type.CER, new Formula("C19H37NO3"));
			MAPFORMULA.put(Ske_type.DG, new Formula("C5H8O5"));
			MAPFORMULA.put(Ske_type.MG, new Formula("C4H8O4"));
			MAPFORMULA.put(Ske_type.PA, new Formula("C5H9O8P"));
			MAPFORMULA.put(Ske_type.PC, new Formula("C10H20NO8P"));
			MAPFORMULA.put(Ske_type.PE, new Formula("C7H14NO8P"));
			MAPFORMULA.put(Ske_type.PI, new Formula("C11H19O13P"));
			MAPFORMULA.put(Ske_type.PG, new Formula("C8H15O10P"));
			MAPFORMULA.put(Ske_type.PS, new Formula("C8H14NO10P"));
			MAPFORMULA.put(Ske_type.SM, new Formula("C24H49N2O6P"));
			MAPFORMULA.put(Ske_type.TG, new Formula("C6H8O6"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
