package model;

import java.util.List;


public class Lipid {
	// Define attributes
	private final Double mass;
	// Formula formula;
	private final String name;
	private final String abbvName;
	private final int length;
	private final int doubleBonds;
	// Define constructors
	
	public Lipid(Skeleton skeleton, List<Fatty_acid> FAs ) throws NullPointerException {
		if ( skeleton == null || FAs == null)
		{
			throw new NullPointerException();
		}
		// formula = calculateLipidFormula(skeleton, FAs)
		mass=0d;
		// Method to calculate the mass
		// mass = calculateMass(Formula);
		// name = calculateName(Skeleton, FAs);
		// length = calculateLength(FAs);
		// doubleBonds = calculateDoubleBonds(FAs);
		name="";
		length=0;
		doubleBonds=0;
		abbvName="";
		
	}
	
	
	// Define getters and setters
	
	// Define other methods
}
