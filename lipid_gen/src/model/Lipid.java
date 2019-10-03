package model;

import java.util.List;
import java.util.Map;

import utilities.Periodic_table;

public class Lipid {
	private final Double mass;
	Formula formula;
	private final String name;
	private final String abbvName;
	private final int length;
	private final int doubleBonds;

	public Lipid(Skeleton skeleton, List<Fatty_acid> FAs) throws NullPointerException {
		if (skeleton == null || FAs == null) {
			throw new NullPointerException();
		}

		formula = calculateLipidFormula(skeleton, FAs);
		name = "";
		length = 0;
		doubleBonds = 0;
		abbvName = "";
		mass = calculateMass(formula);
		// name = calculateName(skeleton, FAs);
		// length = calculateLength(FAs);
		// doubleBonds = calculateDoubleBonds(FAs);

	}

	public Lipid(Fatty_acid fa) throws NullPointerException {
		if (fa == null) {
			throw new NullPointerException();
		}

		formula = fa.getFormula();
		name = "";
		length = 0;
		doubleBonds = 0;
		abbvName = "";

		mass = calculateMass(formula);
		// name = calculateName(Skeleton, FAs);
		// length = calculateLength(FAs);
		// doubleBonds = calculateDoubleBonds(FAs);

	}

	// Define getters and setters
	public Formula getFormula() {
		return formula;
	}

	public void setFormula(Formula formula) {
		this.formula = formula;
	}

	public Double getMass() {
		return mass;
	}

	public String getName() {
		return name;
	}

	public String getAbbvName() {
		return abbvName;
	}

	public int getLength() {
		return length;
	}

	public int getDoubleBonds() {
		return doubleBonds;
	}

	/**
	 * <strong>calculateLipidFormula</strong>
	 * 
	 * This method takes the hash-maps of the skeleton and the list of Fatty Acids,
	 * and then adds into a common map all the elements. It also eliminates 2
	 * hydrogen atoms/Fatty Acid
	 * 
	 * @return Returns the formula of the lipid.
	 */
	public Formula calculateLipidFormula(Skeleton ske, List<Fatty_acid> FAs) {
		Formula ske_formula = ske.getFormula();
		for (int n = 0; n < FAs.size(); n++) {
			Formula FA_formula = FAs.get(n).getFormula();

			for (Map.Entry<Element, Integer> entry_ske : ske_formula.getMAPFORMULA().entrySet()) {
				for (Map.Entry<Element, Integer> entry_FA : FA_formula.getMAPFORMULA().entrySet()) {
					if (entry_ske.getKey().equals(Element.H)) {
						entry_ske.setValue(entry_ske.getValue() - 2);
					}
					if (entry_ske.getKey() == entry_FA.getKey()) {
						entry_ske.setValue(entry_ske.getValue() + entry_FA.getValue());
					}

				}

			}

		}
		return ske_formula;

	}

	/**
	 * <strong>calculateMass</strong>
	 * 
	 * This method takes the hash-map of the elements of the lipid and then adds the
	 * masses of all atom types by multiplying the number of atoms with it's
	 * correspondent isotopic mass
	 * 
	 * @param formula of the lipid
	 * @return Returns the mass of the lipid.
	 */
	public Double calculateMass(Formula formula) {
		Double lipid_mass = 0d;
		for (Map.Entry<Element, Integer> entry_lip : formula.getMAPFORMULA().entrySet()) {
			for (Map.Entry<Element, Double> entry_PT : Periodic_table.MAPELEMENTS.entrySet()) {
				if (entry_lip.getKey().equals(entry_PT.getKey())) {
					lipid_mass = lipid_mass + entry_lip.getValue() * entry_PT.getValue();
				}

			}
		}
		return lipid_mass;

	}
	/*
	 * public String calculateName(Skeleton ske,List<Fatty_acid> FAs) {
	 * name=ske.getske_type; int length; for(int n=0;n<FAs.size();n++) length=
	 * name=name+"("+)"; {} return name; }
	 */

}
