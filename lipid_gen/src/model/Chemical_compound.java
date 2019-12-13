package model;

import utilities.Periodic_table;

public class Chemical_compound {
	protected Formula formula;
	protected double mass;

	/**
	 * This method takes the hash-map of the elements of the FFA/Skeleton/Lipid and
	 * then adds the masses of all atom types by multiplying the number of atoms
	 * with it's correspondent isotopic mass
	 * 
	 * @param formula of the molecule
	 * @return Returns the mass of the molecule.
	 */
	public double getMass() {
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
		Chemical_compound other = (Chemical_compound) obj;
		if (formula == null) {
			if (other.formula != null)
				return false;
		} else if (!formula.equals(other.formula))
			return false;

		return true;
	}// TODO equals

}
