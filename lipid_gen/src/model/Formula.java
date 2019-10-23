package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utilities.Periodic_table;

public class Formula {
	private static final String formulaPatternSkipParenthesis = "(?<!\\()[A-Z]([a-z]?([1-9][0-9]*)*)?(?![A-Za-z0-9]*[\\)])";
	private static final String formulaPatternParenthesisGroups = "[(]([A-Z]([a-z]?([1-9][0-9]*)*)?)*[)]([1-9][0-9]*)*";
	private static final String formulaElement = "[A-Z][a-z]*";
	private static final String formulaNumber = "[1-9][0-9]*";
	private static final Pattern patternSP = Pattern.compile(formulaPatternSkipParenthesis);
	private static final Pattern patternPG = Pattern.compile(formulaPatternParenthesisGroups);
	private static final Pattern patternE = Pattern.compile(formulaElement);
	private static final Pattern patternN = Pattern.compile(formulaNumber);

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
		// TODO Implementar este método
	}

	public Set<Element> getElements() {
		return mapformula.keySet();
	}

	public int getElementQuantity(Element c) {
		return mapformula.get(c);
	}

	/**
	 * This method checks if a formula is valid or not. To be valid, it has to
	 * follow the RegEx and the elements inside need to exist.
	 *
	 * @param formula string with a chemical formula
	 * @return true/false depending if the formula is valid or not
	 */
	public static boolean isValidFormula(String formula) {
		// TODO comment souts
		System.out.println("Fórmula a analizar: " + formula);
		char[] formulaChecker = formula.toCharArray();
		System.out.println(formulaChecker);
		System.out.println("Grupos encontrados (excluyendo paréntesis):");
		Matcher matcher = patternSP.matcher(formula);

		while (matcher.find()) {
			System.out.println(matcher.group());

			System.out.println(matcher.start());
			System.out.println(matcher.end());
			for (int n = matcher.start(); n < matcher.end(); n++) {
				formulaChecker[n] = '\0';

			}
		}
		System.out.println("Grupos de paréntesis encontrados:");
		matcher = patternPG.matcher(formula);
		while (matcher.find()) {
			System.out.println(matcher.group());
			System.out.println(matcher.start());
			System.out.println(matcher.end());
			for (int n = matcher.start(); n < matcher.end(); n++) {
				formulaChecker[n] = '\0';

			}

		}
		System.out.println(formulaChecker);
		for (int n = 0; n < formulaChecker.length; n++) {
			if (formulaChecker[n] != '\0')
				return false;
		}
		ArrayList<String> elementsSP = getFormulaSPData(formula);
		ArrayList<String> elementsPG = getFormulaPGData(formula);
		System.out.println(elementsSP);
		for (int n = 0; n < elementsSP.size(); n++) {
			// We obtain the "element" part of the string
			matcher = patternE.matcher(elementsSP.get(n));
			matcher.find();
			String eSP = matcher.group();
			if (Periodic_table.MAPELEMENTS.containsKey(Element.valueOf(eSP)) != true) {// TODO Instead of
																						// IllegalArgumentError, check
																						// condition?
				return false;
			}
		}
		System.out.println(elementsPG);
		String string_elementsPG = "";
		for (int n = 0; n < elementsPG.size(); n++) {
			string_elementsPG += elementsPG.get(n);
		}
		System.out.println(string_elementsPG);
		ArrayList<String> separated_elementsPG = getFormulaPGElements(string_elementsPG);
		System.out.println(separated_elementsPG);
		for (int n = 0; n < separated_elementsPG.size(); n++) {
			// We obtain the "element" part of the string
			matcher = patternE.matcher(separated_elementsPG.get(n));
			matcher.find();
			String eSP = matcher.group();
			System.out.println(eSP);
			if (Periodic_table.MAPELEMENTS.containsKey(Element.valueOf(eSP)) != true) {// TODO Instead of
																						// IllegalArgumentError, check
																						// condition?
				return false;
			}
		}

		return true;
	}

	/**
	 * This method gets the data without "()" of the formula and creates a list with
	 * the elements with its amount.
	 *
	 * @param INPUT string with a chemical formula
	 * @return a list with the elements outside parentheses
	 */
	public static ArrayList<String> getFormulaSPData(String formula) {
		Matcher matcher = patternSP.matcher(formula);
		ArrayList<String> result = new ArrayList<String>();

		while (matcher.find()) {
			result.add(matcher.group());
		}
		return result;
	}

	/**
	 * This method gets the data inside "()" of the formula and creates a list with
	 * the elements and its amount.
	 *
	 * @param INPUT string with a chemical formula
	 * @return a list with the elements and its amount inside parentheses
	 */
	public static ArrayList<String> getFormulaPGData(String formula) {
		Matcher matcher = patternPG.matcher(formula);
		ArrayList<String> result = new ArrayList<String>();

		while (matcher.find()) {
			result.add(matcher.group());
		}
		return result;
	}

	/**
	 * This method gets the data from the parentheses of a Formula and creates a
	 * list with only the elements inside them.
	 *
	 * @param INPUT string with a chemical formula
	 * @return a list with the elements inside parentheses
	 */
	public static ArrayList<String> getFormulaPGElements(String formula) {
		Matcher matcher = patternE.matcher(formula);
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
