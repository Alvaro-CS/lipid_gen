package model;

import Exceptions.Fatty_acidCreationException;

public class Fatty_acid {
	private final Formula formula;
	private int C, double_bonds;// TODO final?

	public Fatty_acid(Integer C, Integer double_bonds) throws Exception {

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
		this.formula = new Formula(C, double_bonds);
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
