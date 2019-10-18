package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formula {
	private static final String formulaPatternSkipParenthesis = "(?<!\\()[A-Z]([a-z]?([1-9][0-9]*)*)?(?![A-Za-z0-9]*[\\)])";
	private static final String formulaPatternParenthesisGroups = "[(]([A-Z]([a-z]?([1-9][0-9]*)*)?)*[)]([1-9][0-9]*)*";
	private static final Pattern patternSP = Pattern.compile(formulaPatternSkipParenthesis);
	private static final Pattern patternPG = Pattern.compile(formulaPatternParenthesisGroups);

	private final Map<Element, Integer> mapformula = new HashMap<Element, Integer>();

	// TODO String name convertion
	/**
	 * <strong>Constructor for FA</strong> Creates the formula of a Fatty Acid
	 *
	 * @param Carbon_atoms
	 * @param Double_bonds
	 * @throws Exception
	 */
	public Formula(int C, int double_bonds) throws Exception {
		add(Element.C, C);
		add(Element.H, 2 * C - (double_bonds * 2));
		add(Element.O, 2);
	}

	public Formula(String formula) {
		// Put all methods here
	}

	public Formula(Formula f1) throws Exception {
		for (Element e : f1.getElements()) {
			add(e, f1.getElementQuantity(e));
		}
	}

	public void add(Element e, int num) throws Exception {
		if (num <= 0) {
			throw new Exception();
		}
		if (mapformula.containsKey(e)) {
			mapformula.put(e, num + mapformula.get(e));
		} else {
			mapformula.put(e, num);
		}
	}

	public void remove(Element e, int num) {
		// Comprobar si elemento en MAPFORMULA
		// Comprobar si suficientes elementos para eliminar
		// Si elemento finalmente queda a 0, eliminar la clave del mapa.
		// TODO Implementar este m�todo
	}

	public Set<Element> getElements() {
		return mapformula.keySet();
	}

	public int getElementQuantity(Element c) {
		return mapformula.get(c);
	}

	public static boolean isValidFormula(String formula) {
		System.out.println("F�rmula a analizar: " + formula);
		// TODO also check if element exists
		System.out.println("Grupos encontrados (excluyendo par�ntesis):");
		Matcher matcher = patternSP.matcher(formula);
		while (matcher.find()) {
			System.out.println(matcher.group());
		}

		System.out.println("Grupos de par�ntesis encontrados:");
		matcher = patternPG.matcher(formula);
		while (matcher.find()) {
			System.out.println(matcher.group());
		}

		// Habr� que ver si con los grupos detectados se cubre toda la expresi�n
		// regular. Ojo que habr� que tener en cuenta tambi�n a los que est�n entre
		// par�ntesis. Ambos deben cubrir todo el string de entrada.
		return true;
	}

	/**
	 * This method gets the data of the formula and creates a list with the
	 * elements.
	 *
	 * @param INPUT string with a chemical formula
	 * @return A list with the elements
	 */
	public static ArrayList<String> getFormulaData(String formula) {// TODO (CH)2 no coge el 2???
		Matcher matcher = patternSP.matcher(formula);
		ArrayList<String> result = new ArrayList<String>();

		while (matcher.find()) {
			result.add(matcher.group());
		}

		return result;
	}

	public void createMapFormula(ArrayList<String> result_list) {// TODO Parantesis multi. Repeticion de elementos.
																	// Varios numeros seguidos...
		String[] result = (String[]) result_list.toArray();
		for (int n = 0; n < result.length; n++) {
			int counter = 0;
			if (result[n].chars().allMatch(Character::isAlphabetic)) {
				mapformula.put(Element.valueOf(result[n]), 1);
			}
			if (result[n].chars().allMatch(Character::isDigit)) {

				mapformula.put(Element.valueOf(result[n - 1]), Integer.parseInt(result[n]));

			}

		}

	}

}
