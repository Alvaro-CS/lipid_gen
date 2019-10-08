package model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import utilities.Periodic_table;

//TODO @param negrita
public class Lipid {
	private final Double mass;
	private final Formula formula;
	/*
	 * private Skeleton skeleton; private List<Fatty_acid> FAs;
	 */ // TODO why if 1-n, n-m, don't we need to add this as atributes. Is the formula
	// the only important attached class?
	private final String name;
	private final String abbvName;
	private final int length;
	private final int doubleBonds;

	public Lipid(Skeleton skeleton, List<Fatty_acid> FAs) throws Exception {
		if (skeleton == null || FAs == null) {
			throw new NullPointerException();
		}

		formula = calculateLipidFormula(skeleton, FAs);
		abbvName = calculateAbbvName(skeleton, FAs);
		mass = calculateMass(formula);
		name = calculateName(skeleton, FAs);
		length = calculateLength(FAs);
		doubleBonds = calculateDoubleBonds(FAs);

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
	 * This method takes the hash-maps of the skeleton and the list of Fatty Acids,
	 * and then adds into a common map all the elements. It also eliminates 2
	 * hydrogen atoms/Fatty Acid
	 * 
	 * @return Returns the formula of the lipid.
	 * @throws Exception
	 */
	public static Formula calculateLipidFormula(Skeleton ske, List<Fatty_acid> FAs) throws Exception {
		Formula lipid_formula = new Formula(ske.getFormula());

		for (Fatty_acid fa : FAs) {
			Formula FA_formula = fa.getFormula();

			for (Element e : FA_formula.getElements()) {
				lipid_formula.add(e, FA_formula.getElementQuantity(e));
			}
			lipid_formula.remove(Element.H, 2);
			ske.getFormula().remove(Element.H, 1);
			fa.getFormula().remove(Element.H, 1);// TODO comentar a Alberto

			/*
			 * for (Map.Entry<Element, Integer> entry_ske :
			 * lipid_formula.getMAPFORMULA().entrySet()) { for (Map.Entry<Element, Integer>
			 * entry_FA : FA_formula.getMAPFORMULA().entrySet()) { if
			 * (entry_ske.getKey().equals(Element.H)) {
			 * entry_ske.setValue(entry_ske.getValue() - 2); } if (entry_ske.getKey() ==
			 * entry_FA.getKey()) { entry_ske.setValue(entry_ske.getValue() +
			 * entry_FA.getValue()); }
			 * 
			 * }
			 * 
			 * }
			 */

		}
		return lipid_formula;

	}

	/**
	 * This method takes the hash-map of the elements of the FFA/Skeleton/Lipid and
	 * then adds the masses of all atom types by multiplying the number of atoms
	 * with it's correspondent isotopic mass
	 * 
	 * @param formula of the molecule
	 * @return Returns the mass of the molecule.
	 */
	public static Double calculateMass(Formula formula) {// TODO incorrect class?
		Double molecule_mass = 0d;
		for (Element e : formula.getElements()) {
			for (Element e_table : Periodic_table.MAPELEMENTS.keySet()) {
				if (e.equals(e_table)) {
					molecule_mass = molecule_mass + formula.getElementQuantity(e) * Periodic_table.MAPELEMENTS.get(e);
				}

			}
		}
		return molecule_mass;

	}

	/**
	 * This method creates the name of the lipid by concatenating the name of the
	 * skeleton, followed by the length of each Fatty Acid with it's double bonds as
	 * follows:<br>
	 * Skeleton_name(Length FA1:double bonds FA1/Length FA2:double bonds
	 * FA2/.../Length FA(N):double bonds FA(N))
	 * 
	 * @param The skeleton and the list of FAs of the lipid
	 * @return Returns the name of the lipid.
	 */
	public static String calculateName(Skeleton ske, List<Fatty_acid> FAs) {
		String lipid_name = ske.getSke_type().toString() + "(";
		int length, d_bonds;
		for (int n = 0; n < FAs.size(); n++) {
			length = FAs.get(n).getC();
			d_bonds = FAs.get(n).getC();
			lipid_name += length + ":" + d_bonds;
			if (n < FAs.size() - 1)
				lipid_name += "/";
		}
		lipid_name += ")";
		return lipid_name;
	}

	/**
	 * This method creates the abbreviate name of the lipid by indicating the number
	 * of carbon atoms of the Fatty Acids and double bonds as follows:<br>
	 * Skeleton_name(Sum of carbon atoms of all Fatty Acids : Sum of double bonds of
	 * all Fatty Acids)
	 * 
	 * @param The skeleton and the list of FAs of the lipid.
	 * @return Returns the abbreviate name of the lipid.
	 */

	public static String calculateAbbvName(Skeleton ske, List<Fatty_acid> FAs) {
		int length = calculateLength(FAs);
		int d_bonds = calculateDoubleBonds(FAs);

		String abbv_name = ske.getSke_type().toString() + "(" + length + ":" + d_bonds + ")";

		return abbv_name;
	}

	/**
	 * This method calculates the sum of the length of Fatty Acid chains
	 * 
	 * @param The list of Fatty Acids.
	 * @return Returns the length of Fatty Acid chains.
	 */

	public static int calculateLength(List<Fatty_acid> FAs) {
		int length = 0;
		for (int n = 0; n < FAs.size(); n++) {
			length += FAs.get(n).getC();
		}
		return length;
	}

	/**
	 * This method calculates the total number of double bonds of Fatty Acid chains
	 * 
	 * @param The list of Fatty Acids.
	 * @return Returns the double bonds of Fatty Acid chains.
	 */
	public static int calculateDoubleBonds(List<Fatty_acid> FAs) {
		int d_bonds = 0;
		for (int n = 0; n < FAs.size(); n++) {
			d_bonds += FAs.get(n).getDouble_bonds();
		}
		return d_bonds;
	}
}
