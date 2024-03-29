package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Exceptions.InvalidFormulaException;
import utilities.ElementsComparator;
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

	private final TreeMap<Element, Integer> mapFormula = new TreeMap<Element, Integer>(new ElementsComparator());

	/**
	 * <strong>Constructor for formula from the map</strong> Creates the formula
	 * from a TreeMap of elements
	 *
	 * @param mapformula the HashMap
	 * @throws Exception
	 */
	public Formula(Map<Element, Integer> formula) {
		this.mapFormula.putAll(formula);
	}

	/**
	 * <strong>Constructor for Formula</strong> Creates the formula from a String.
	 * It checks that the formula exists and it parses all the elements from the
	 * String and sets them inside the Hashmap.
	 * 
	 * @param formula (string)
	 * @throws Exception
	 */
	public Formula(String formula) throws InvalidFormulaException {
		if (isValidFormula(formula)) {
			createMapFormula(formula);
		} else {
			throw new InvalidFormulaException(
					"The formula doesn't have a correct format or there are non-existing elements.");

		}

	}

	/**
	 * <strong>Constructor for Formula</strong> Creates a copy of an object formula.
	 *
	 * @param f1 The object formula we want to make a copy of.
	 * @throws Exception
	 */
	public Formula(Formula f1) {
		this(f1.mapFormula);
	}

	/**
	 * Adds a certain element to the formula map. If the element doesn't exist, it's
	 * created and if it already exists, the amount of new element is added to the
	 * existing one.
	 *
	 * @param e   element
	 * @param num amount of elements
	 * @throws Exception
	 */
	public void addElementToFormula(Element e, int num) throws IllegalArgumentException {
		if (num <= 0) {
			throw new IllegalArgumentException("We can't add a negative amount of elements.");
		} else if (this.mapFormula.containsKey(e)) {
			this.mapFormula.put(e, num + this.mapFormula.get(e));
		} else {
			this.mapFormula.put(e, num);
		}
	}

	/**
	 * Deletes a certain amount of elements from the formula map. If there are no
	 * more elements, the mapping for the element is removed.
	 *
	 * @param e   element
	 * @param num amount of elements
	 * @throws Exception
	 */
	public void remove(Element e, int num) throws IllegalArgumentException {
		if (this.mapFormula.containsKey(e)) {
			if (this.mapFormula.get(e) > num) {
				this.mapFormula.put(e, this.mapFormula.get(e) - num);
			} else if (this.mapFormula.get(e) == num) {
				this.mapFormula.remove(e);
			} else if (this.mapFormula.get(e) < num) {
				throw new IllegalArgumentException("There's not enough elements in the formula for the deletion.");
			}
		} else {
			throw new IllegalArgumentException("The formula doesn't have this element, so we can't delete it.");
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
		char[] formulaChecker = formula.toCharArray();
		Matcher matcher = patternSP.matcher(formula);

		while (matcher.find()) {
			for (int n = matcher.start(); n < matcher.end(); n++) {
				formulaChecker[n] = '\0';

			}
		}
		matcher = patternPG.matcher(formula);
		while (matcher.find()) {
			for (int n = matcher.start(); n < matcher.end(); n++) {
				formulaChecker[n] = '\0';

			}

		}
		for (int n = 0; n < formulaChecker.length; n++) {
			if (formulaChecker[n] != '\0')
				return false;
		}
		ArrayList<String> elementsSP = getFormulaSPData(formula);
		ArrayList<String> elementsPG = getFormulaPGData(formula);
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
		String string_elementsPG = "";
		for (int n = 0; n < elementsPG.size(); n++) {
			string_elementsPG += elementsPG.get(n);
		}
		ArrayList<String> separated_elementsPG = getFormulaPGElements(string_elementsPG);
		for (int n = 0; n < separated_elementsPG.size(); n++) {
			// We obtain the "element" part of the string
			matcher = patternE.matcher(separated_elementsPG.get(n));
			matcher.find();
			String eSP = matcher.group();
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
	public void createMapFormula(String formula) throws IllegalStateException {
		Matcher matcher;
		System.out.println(formula);
		ArrayList<String> elementsSP = getFormulaSPData(formula);
		ArrayList<String> elementsPG = getFormulaPGData(formula);
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mapFormula == null) ? 0 : mapFormula.hashCode());
		return result;
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
		} else if (!obj.toString().equals(other.toString())) {
			return false;
		}
		return true;
	}

}
