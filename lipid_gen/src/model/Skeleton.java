package model;

import utilities.Ske_type_formula;

public class Skeleton {

	private final Ske_type ske_type;
	private final Formula formula;
	private final Double mass;// TODO

	public Skeleton(Ske_type ske_type) {
		this.formula = Ske_type_formula.MAPFORMULA.get(ske_type);
		mass = null;
		this.ske_type = ske_type;
	}

	public Formula getFormula() {
		return formula;
	}

	public Double getMass() {
		return mass;
	}

	public Ske_type getSke_type() {
		return ske_type;
	}

}
