package model;

import java.util.LinkedList;
import java.util.List;

import utilities.Periodic_table;
import utilities.Ske_type_prop;

public class Lipid extends Chemical_element {
	private final Skeleton skeleton;
	private final List<Fatty_acid> FAs;
	private final String name;
	private final String abbvName;
	private final int length;
	private final int doubleBonds;

	/**
	 * Lipid constructor from a skeleton and a list of Fatty Acids that match with
	 * the skeleton type.
	 * 
	 * @param skeleton
	 * @param FAs      the list of Fatty Acids
	 * @throws Exception
	 */
	public Lipid(Skeleton skeleton, List<Fatty_acid> FAs) throws Exception {
		if (skeleton == null || FAs == null) {
			throw new NullPointerException();
		}

		formula = calculateLipidFormula(skeleton, FAs);
		doubleBonds = calculateDoubleBonds(FAs);
		length = calculateLength(FAs);
		abbvName = calculateAbbvName(skeleton, FAs);
		mass = getMass();
		name = calculateName(skeleton, FAs);
		this.skeleton = skeleton;
		if (FAs.size() > Ske_type_prop.MAPSKE.get(skeleton.getSke_type()).maxFAs
				|| FAs.size() < Ske_type_prop.MAPSKE.get(skeleton.getSke_type()).minFAs) {

			throw new Exception("The number of Fatty Acids is not the appropiate for this skeleton.");
		}
		this.FAs = FAs;

	}

	/**
	 * Lipid constructor a single Fatty Acid
	 * 
	 * @param fa the Fatty Acid
	 * @throws Exception
	 */
	public Lipid(Fatty_acid fa) throws NullPointerException {
		if (fa == null) {
			throw new NullPointerException();
		}

		formula = fa.getFormula();
		abbvName = calculateName(fa);
		name = calculateName(fa);
		mass = calculateMass(formula);
		length = fa.getC();
		doubleBonds = fa.getDouble_bonds();
		this.FAs = new LinkedList<Fatty_acid>();
		this.FAs.add(fa);
		this.skeleton = null;

	}

	public Formula getFormula() {
		return formula;
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

	public Skeleton getSkeleton() {
		return skeleton;
	}

	public List<Fatty_acid> getFAs() {
		return FAs;
	}

	/**
	 * This method takes the hash-maps of the skeleton and the list of Fatty Acids,
	 * and then adds into a common map all the elements. It also eliminates 2
	 * hydrogen atoms/Fatty Acid
	 * 
	 * @return The formula of the lipid.
	 * @throws Exception
	 */
	public static Formula calculateLipidFormula(Skeleton ske, List<Fatty_acid> FAs) throws Exception {
		Formula lipid_formula = new Formula(ske.getFormula());
		for (Fatty_acid fa : FAs) {
			Formula FA_formula = fa.getFormula();

			for (Element e : FA_formula.getElements()) {
				lipid_formula.addElementToFormula(e, FA_formula.getElementQuantity(e));
			}
			lipid_formula.remove(Element.H, 2);
			/*
			 * No, because we want to reuse the FAs. ske.getFormula().remove(Element.H, 1);
			 * fa.getFormula().remove(Element.H, 1);
			 * 
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
	 * This method creates the name of the lipid (a Fatty Acid). It creates a list
	 * with the FA and calls the generic method for doing so.
	 * 
	 * @param FA The future lipid (Fatty Acid)
	 * @return The name of the lipid.
	 */
	public static String calculateName(Fatty_acid FA) {
		List<Fatty_acid> FAs = new LinkedList<Fatty_acid>();
		FAs.add(FA);
		return calculateName(null, FAs);
	}

	/**
	 * This method creates the name of the lipid by concatenating the name of the
	 * skeleton, followed by the length of each Fatty Acid with it's double bonds as
	 * follows:<br>
	 * Skeleton_name(Length FA1:double bonds FA1/Length FA2:double bonds
	 * FA2/.../Length FA(N):double bonds FA(N)) <br>
	 * If the lipid is just a Fatty Acid, the name is just the number of carbons
	 * with double bonds.
	 * 
	 * @param ske The skeleton of the lipid.
	 * @param FAs The list of FAs of the lipid.
	 * @return The name of the lipid.
	 */
	public static String calculateName(Skeleton ske, List<Fatty_acid> FAs) {
		String lipid_name;
		if (ske == null) {
			lipid_name = "FA(" + FAs.get(0).getC() + ":" + FAs.get(0).getDouble_bonds() + ")";

		} else {
			lipid_name = ske.getSke_type().toString() + "(";

			int length, d_bonds;
			for (int n = 0; n < FAs.size(); n++) {
				length = FAs.get(n).getC();
				d_bonds = FAs.get(n).getDouble_bonds();
				lipid_name += length + ":" + d_bonds;
				if (n < FAs.size() - 1)
					lipid_name += "/";
			}
			lipid_name += ")";
		}
		return lipid_name;
	}

	/**
	 * This method creates the abbreviate name of the lipid by indicating the number
	 * of carbon atoms of the Fatty Acids and double bonds as follows:<br>
	 * Skeleton_name(Sum of carbon atoms of all Fatty Acids : Sum of double bonds of
	 * all Fatty Acids)
	 * 
	 * @param ske The skeleton of the lipid.
	 * @param FAs The list of FAs of the lipid.
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lipid other = (Lipid) obj;
		if (FAs == null) {
			if (other.FAs != null)
				return false;
		} else if (!FAs.equals(other.FAs))
			return false;
		if (abbvName == null) {
			if (other.abbvName != null)
				return false;
		} else if (!abbvName.equals(other.abbvName))
			return false;
		if (doubleBonds != other.doubleBonds)
			return false;
		if (formula == null) {
			if (other.formula != null)
				return false;
		} else if (!formula.equals(other.formula))
			return false;
		if (length != other.length)
			return false;
		if (mass == null) {
			if (other.mass != null)
				return false;
		} else if (!mass.equals(other.mass))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (skeleton == null) {
			if (other.skeleton != null)
				return false;
		} else if (!skeleton.equals(other.skeleton))
			return false;
		return true;
	}

}
