package model;

import Exceptions.Fatty_acidCreationException;

public class Fatty_acid {
	private final Formula formula;

	public Fatty_acid(Integer C, Integer double_bonds) throws Fatty_acidCreationException, NullPointerException {
		// TODO Excepcion doublebonds-c,
		// limite carbonos

		if (double_bonds > C - 1)
			throw new Fatty_acidCreationException(
					"Double bonds of the molecule must be at least lower than the number of Carbon atoms-1.");
		if (double_bonds < 0 || C < 0)
			throw new Fatty_acidCreationException("Dbhjgbjhbjh");
		if (C == null || double_bonds == null) {
			throw new NullPointerException();
		}
		this.formula = new Formula(C, double_bonds);
	}

	public Formula getFormula() {
		return formula;
	}

}
