package model;

import java.util.Map;
import java.util.TreeMap;

import Exceptions.Fatty_acidCreationException;

public class Fatty_acid extends Chemical_compound {

	private int C;
	private int double_bonds;

	/**
	 * Constructor of a Fatty Acid from the number of double bonds and carbon atoms.
	 *
	 * @param C            the number of carbon atoms
	 * @param double_bonds
	 */

	public Fatty_acid(Integer C, Integer double_bonds) throws Fatty_acidCreationException {

		if (double_bonds > C - 1)
			throw new Fatty_acidCreationException(
					"Double bonds of the molecule must be at least lower than the number of Carbon atoms-1.");
		if (double_bonds < 0 || C < 3)
			throw new Fatty_acidCreationException(
					"The fatty acid can't have a negative number of double bonds or less than 3 carbon atoms.");
		if (C > 36)
			throw new Fatty_acidCreationException("The fatty acid can't have more than 36 carbon atoms.");
		if (double_bonds > 6)
			throw new Fatty_acidCreationException("The fatty acid can't have more than 6 double bonds.");
		if (C == null || double_bonds == null) {
			throw new NullPointerException();
		}
		this.C = C;
		this.double_bonds = double_bonds;

		Map<Element, Integer> elementsFA = new TreeMap<Element, Integer>();
		elementsFA.put(Element.C, C);
		elementsFA.put(Element.H, 2 * C - (double_bonds * 2));
		elementsFA.put(Element.O, 2);

		this.formula = new Formula(elementsFA);
		this.mass = getMass();
	}

	public Formula getFormula() {
		return formula;
	}

	public int getC() {
		return C;
	}

	public int getDouble_bonds() {
		return double_bonds;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fatty_acid other = (Fatty_acid) obj;
		if (C != other.C)
			return false;
		if (double_bonds != other.double_bonds)
			return false;
		if (formula == null) {
			if (other.formula != null)
				return false;
		} else if (!formula.equals(other.formula))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fatty_acid [formula=" + formula + ", C=" + C + ", double_bonds=" + double_bonds + "]";
	}

}
