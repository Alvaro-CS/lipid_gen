package model;

import utilities.Ske_type_prop;

public class Skeleton extends Chemical_element {

	private final Ske_type ske_type;

	public Skeleton(Ske_type ske_type) {
		this.formula = Ske_type_prop.MAPSKE.get(ske_type).getFormula();
		mass = getMass();
		this.ske_type = ske_type;
	}

	public Formula getFormula() {
		return formula;
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

	@Override
	public String toString() {
		return "Skeleton [ske_type=" + ske_type + ", formula=" + formula + "]";
	}

}
