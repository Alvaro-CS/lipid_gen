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
	private static final String formulaInsideP = "([A-Z][a-z]*[0-9]*)";
	private static final Pattern patternSP = Pattern.compile(formulaPatternSkipParenthesis);
	private static final Pattern patternPG = Pattern.compile(formulaPatternParenthesisGroups);
	private static final Pattern patternE = Pattern.compile(formulaElement);
	private static final Pattern patternN = Pattern.compile(formulaNumber);
	private static final Pattern patternInsideP = Pattern.compile(formulaInsideP);

	private final Map<Element, Integer> mapFormula;

	/**
	 * <strong>Constructor for FA</strong> Creates the formula of a Fatty Acid
	 *
	 * @param Carbon_atoms
	 * @param Double_bonds
	 * @throws Exception
	 */
	public Formula(Map<Element, Integer> mapFormula) throws Exception {
		this.mapFormula = mapFormula;
	}

	/**
	 * <strong>Constructor for FA</strong> Creates the formula of a Fatty Acid
	 *
	 * @param Carbon_atoms
	 * @param Double_bonds
	 * @throws Exception
	 */
	public Formula(int C, int double_bonds) throws Exception {
		this.mapFormula = new HashMap<Element, Integer>();
		addElementToFormula(Element.C, C);
		addElementToFormula(Element.H, 2 * C - (double_bonds * 2));
		addElementToFormula(Element.O, 2);
	}

	public Formula(String formula) throws Exception {
		if (isValidFormula(formula)) {
			this.mapFormula = new HashMap<Element, Integer>();
			createMapFormula(formula);
		} else {
			throw new Exception("The formula doesn't have a correct format or there are non-existing elements.");

		}

	}

	public Formula(Formula f1) throws Exception {
		this.mapFormula = new HashMap<Element, Integer>();
		for (Element e : f1.getElements()) {
			addElementToFormula(e, f1.getElementQuantity(e));
		}
	}

	public void addElementToFormula(Element e, int num) throws Exception {
		if (num <= 0) {
			throw new Exception("We can't add a negative amount of elements.");
		} else if (this.mapFormula.containsKey(e)) {
			this.mapFormula.put(e, num + this.mapFormula.get(e));
		} else {
			this.mapFormula.put(e, num);
		}
	}

	public void remove(Element e, int num) throws Exception {
		if (this.mapFormula.containsKey(e)) {
			if (this.mapFormula.get(e) > num) {
				this.mapFormula.remove(e, num);
			} else if (this.mapFormula.get(e) == num) {
				this.mapFormula.remove(e);
			} else if (this.mapFormula.get(e) < num) {
				throw new Exception("There's not enough elements in the formula for the deletion.");
			}
		} else {
			throw new Exception("The formula doesn't have this element, so we can't delete it.");
		}

	}

	public Set<Element> getElements() {
		return mapFormula.keySet();
	}

	public int getElementQuantity(Element c) {
		return mapFormula.get(c);
	}

	/**
	 * This method checks if a formula is valid or not. To be valid, it has to
	 * follow the RegEx and the elements inside need to exist.
	 *
	 * @param formula string with a chemical formula
	 * @return true/false depending if the formula is valid or not
	 */
	public static boolean isValidFormula(String formula) {
//		System.out.println("Fórmula a analizar: " + formula);
		char[] formulaChecker = formula.toCharArray();
		// System.out.println(formulaChecker);
		// System.out.println("Grupos encontrados (excluyendo paréntesis):");
		Matcher matcher = patternSP.matcher(formula);

		while (matcher.find()) {
			// System.out.println(matcher.group());
			for (int n = matcher.start(); n < matcher.end(); n++) {
				formulaChecker[n] = '\0';

			}
		}
		// System.out.println("Grupos de paréntesis encontrados:");
		matcher = patternPG.matcher(formula);
		while (matcher.find()) {
			// System.out.println(matcher.group());
			for (int n = matcher.start(); n < matcher.end(); n++) {
				formulaChecker[n] = '\0';

			}

		}
		// System.out.println(formulaChecker);
		for (int n = 0; n < formulaChecker.length; n++) {
			if (formulaChecker[n] != '\0')
				return false;
		}
		ArrayList<String> elementsSP = getFormulaSPData(formula);
		ArrayList<String> elementsPG = getFormulaPGData(formula);
		// System.out.println(elementsSP);
		for (int n = 0; n < elementsSP.size(); n++) {
			// We obtain the "element" part of the string
			matcher = patternE.matcher(elementsSP.get(n));
			matcher.find();
			String eSP = matcher.group();
			try {
				if (Periodic_table.MAPELEMENTS.containsKey(Element.valueOf(eSP)) != true) {
					return false;
				}
			} catch (IllegalArgumentException e) {
				return false;
			}
		}
		// System.out.println(elementsPG);
		String string_elementsPG = "";
		for (int n = 0; n < elementsPG.size(); n++) {
			string_elementsPG += elementsPG.get(n);
		}
		// System.out.println(string_elementsPG);
		ArrayList<String> separated_elementsPG = getFormulaPGElements(string_elementsPG);
		// System.out.println(separated_elementsPG);
		for (int n = 0; n < separated_elementsPG.size(); n++) {
			// We obtain the "element" part of the string
			matcher = patternE.matcher(separated_elementsPG.get(n));
			matcher.find();
			String eSP = matcher.group();
			// System.out.println(eSP);
			try {
				if (Periodic_table.MAPELEMENTS.containsKey(Element.valueOf(eSP)) != true) {
					return false;
				}
			} catch (IllegalArgumentException ex) {
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

	/**
	 * This method gets the data in the formula and using Regular Expressions, it
	 * gets the elements of the formula(string) and adds them into the Hashmap of
	 * the formula.
	 *
	 * @param INPUT string with a chemical formula
	 * @return void function. It adds the elements to the Hashmap "mapformula".
	 */
	public void createMapFormula(String formula) throws Exception {
		Matcher matcher;
		System.out.println(formula);
		ArrayList<String> elementsSP = getFormulaSPData(formula);
		ArrayList<String> elementsPG = getFormulaPGData(formula);
		System.out.println(elementsSP);
		System.out.println(elementsPG);
		for (int n = 0; n < elementsSP.size(); n++) {
			matcher = patternE.matcher(elementsSP.get(n));
			matcher.find();
			String eSP = matcher.group();
			Integer nSP;
			try {
				matcher = patternN.matcher(elementsSP.get(n));
				matcher.find();
				nSP = Integer.parseInt(matcher.group());
			} catch (IllegalStateException ex) {// If the element doesn't have a number with it, it's assumed to be 1.
				nSP = 1;
			}

			addElementToFormula(Element.valueOf(eSP), nSP);
			System.out.println("Metemos elemento " + eSP + " con " + nSP);

		} // finished adding non-parentheses elements.
		for (int n = 0; n < elementsPG.size(); n++) {
			ArrayList<String> eInsideP = new ArrayList<String>();

			int multiplier = 0;
			matcher = patternInsideP.matcher(elementsPG.get(n));
			while (matcher.find()) {
				eInsideP.add(matcher.group());
			}

			char lastC = elementsPG.get(n).charAt(elementsPG.get(n).length() - 1);
			if (Character.isDigit(lastC)) {
				multiplier = Character.getNumericValue(lastC);// we get the last character of
				// the groups, that would be a number.
			} else {
				multiplier = 1;
			}

			for (int i = 0; i < eInsideP.size(); i++) {
				matcher = patternE.matcher(eInsideP.get(i));// TODO do a method, used twice.
				matcher.find();
				String eSP = matcher.group();
				Integer nSP;
				try {
					matcher = patternN.matcher(eInsideP.get(i));
					matcher.find();
					nSP = Integer.parseInt(matcher.group());
				} catch (IllegalStateException ex) {// If the element doesn't have a number with it, it's assumed to be
													// 1.
					nSP = 1;
				}

				addElementToFormula(Element.valueOf(eSP), nSP * multiplier);
				System.out.println("Metemos elemento " + eSP + " con " + nSP * multiplier);

			}
		}

	}

	@Override
	public String toString() {
		String formula = "";
		for (Element e : getElements()) {
			formula += e.toString() + getElementQuantity(e);

		}
		return formula;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Formula other = (Formula) obj;
		if (mapFormula == null) {
			if (other.mapFormula != null)
				return false;
		} else if (!mapFormula.equals(other.mapFormula))
			return false;
		return true;
	}

}
