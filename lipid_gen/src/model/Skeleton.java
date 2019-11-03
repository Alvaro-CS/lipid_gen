package model;

import utilities.Ske_type_formula;

public class Skeleton {

	private final Ske_type ske_type;
	private final Formula formula;
	private final Double mass;

	public Skeleton(Ske_type ske_type) {
		this.formula = Ske_type_formula.MAPFORMULA.get(ske_type);
		mass = null;// TODO necesito masa? método creado en clase lípidos
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Skeleton other = (Skeleton) obj;
		if (formula == null) {
			if (other.formula != null)
				return false;
		} else if (!formula.equals(other.formula))
			return false;
		if (mass == null) {
			if (other.mass != null)
				return false;
		} else if (!mass.equals(other.mass))
			return false;
		if (ske_type != other.ske_type)
			return false;
		return true;
	}

}
