package model;

import utilities.Periodic_table;

public class Chemical_element {
	protected Formula formula;
	protected Double mass;

	public Double getMass() {
		mass = 0d;
		for (Element e : formula.getElements()) {
			for (Element e_table : Periodic_table.MAPELEMENTS.keySet()) {
				if (e.equals(e_table)) {
					mass = mass + formula.getElementQuantity(e) * Periodic_table.MAPELEMENTS.get(e);
				}

			}
		}
		return mass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((formula == null) ? 0 : formula.hashCode());
		result = prime * result + ((mass == null) ? 0 : mass.hashCode());
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
		Chemical_element other = (Chemical_element) obj;
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
		return true;
	}

}
