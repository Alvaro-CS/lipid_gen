package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formula {
	public Map<Element, Integer> MAPFORMULA = new HashMap<Element, Integer>();

	// TODO String name convertion
	/**
	 * <strong>Constructor for FA</strong> Creates the formula of a Fatty Acid
	 * 
	 * @param Carbon_atoms
	 * @param Double_bonds
	 * 
	 */
	public Formula(int C, int double_bonds) {
		MAPFORMULA.put(Element.C, C);
		MAPFORMULA.put(Element.H, 2 * C - (double_bonds * 2));
		MAPFORMULA.put(Element.O, 2);
	}

	public Formula(String formula) {
//Put all methods here
	}

	public String[] getFormulaData(String INPUT) {// TODO primer numero que acompa�a no puede ser 0. Anterior incorpora
													// numeros de 2 cifras solo
		Matcher matcher = Pattern.compile("[A-Z]([a-z]?[0-9]*)?|[(]([A-Z]([a-z]|[0-9]*)?)*[)][0-9]*").matcher(INPUT);
		ArrayList<String> result = new ArrayList<String>();
		if (matcher.find()) {
			result.add(matcher.group());

			while (matcher.find()) {
				result.add(matcher.group());
			}
		}

		return (String[]) result.toArray();
	}

	public void createMapFormula(String[] result) {// TODO Parantesis multi. Repeticion de elementos. Varios numeros seguidos...
		// TODO Excepciones: elemento no valido
		
		for (int n = 0; n < result.length; n++) {
			int counter=0;
			if (result[n].chars().allMatch(Character::isAlphabetic)) {
				MAPFORMULA.put(Element.valueOf(result[n]), 1);
			}
			if (result[n].chars().allMatch(Character::isDigit)) {

				MAPFORMULA.put(Element.valueOf(result[n - 1]), Integer.parseInt(result[n]) - 1);

			}

		}

	}

	public Map<Element, Integer> getMAPFORMULA() {
		return MAPFORMULA;
	}

	public void setMAPFORMULA(Map<Element, Integer> mAPFORMULA) {
		MAPFORMULA = mAPFORMULA;
	}

}
