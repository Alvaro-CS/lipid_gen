package model;

import java.util.HashMap;
import java.util.Map;

public class Formula {
	public Map<Element, Integer> MAPFORMULA = new HashMap<Element, Integer>();

	// TODO String name convertion
	/**
	 * <strong>Constructor for FA</strong> Creates the formula of a Fatty Acid
	 * 
	 * @param Carbon_atoms
	 * @param Double_bonds
	 * 
	 */
	public Formula(int C, int double_bonds) {
		MAPFORMULA.put(Element.C, C);
		MAPFORMULA.put(Element.H, 2 * C - (double_bonds * 2));
		MAPFORMULA.put(Element.O, 2);

	}

	public Map<Element, Integer> getMAPFORMULA() {
		return MAPFORMULA;
	}

	public void setMAPFORMULA(Map<Element, Integer> mAPFORMULA) {
		MAPFORMULA = mAPFORMULA;
	}
	/*
	 * public String getName() { return name; } public void setName(String name) {
	 * this.name = name; }
	 */
}
