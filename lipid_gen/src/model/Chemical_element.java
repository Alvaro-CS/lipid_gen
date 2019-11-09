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
}
