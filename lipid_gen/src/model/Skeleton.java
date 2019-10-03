package model;

public class Skeleton {

	private final Ske_type ske_type ;
	private final Formula formula;
	private final Double mass;

	public Skeleton(Formula formula,Ske_type ske_type) {
		this.formula = formula;
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
