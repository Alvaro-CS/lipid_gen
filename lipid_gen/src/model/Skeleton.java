package model;

public class Skeleton {

	private final Ske_type ske_type ;
	private final Formula formula;
	private final Double mass;

	public Skeleton(Ske_type ske_type) {
		formula = null;
		mass = null;
		this.ske_type = ske_type;
	}

	public Formula getFormula() {
		return formula;
	}

	public Double getMass() {
		return mass;
	}

}
