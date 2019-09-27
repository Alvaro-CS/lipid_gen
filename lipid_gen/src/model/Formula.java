package model;

import java.util.HashMap;
import java.util.Map;

public class Formula {
	public Map<Element, Integer> MAPFORMULA = new HashMap<Element, Integer>();
	String name;
/*public Formula (Fatty_acid) {
	
}*/
	public Map<Element, Integer> getMAPFORMULA() {
		return MAPFORMULA;
	}
	public void setMAPFORMULA(Map<Element, Integer> mAPFORMULA) {
		MAPFORMULA = mAPFORMULA;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
