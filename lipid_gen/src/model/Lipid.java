package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.javafx.css.CalculatedValue;


public class Lipid {
	// Define attributes
	private final Double mass;
	Formula formula;
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

		
		 formula = calculateLipidFormula(skeleton, FAs);
		mass=0d;
		name="";
		length=0;
		doubleBonds=0;
		abbvName="";
		// Method to calculate the mass
		// mass = calculateMass(Formula);
		// name = calculateName(Skeleton, FAs);
		// length = calculateLength(FAs);
		// doubleBonds = calculateDoubleBonds(FAs);
		
	
		
	}
		public Lipid(Fatty_acid fa) throws NullPointerException {
			if ( fa == null)
			{
				throw new NullPointerException();
			}
			
		
		// formula = calculateLipidFormula(skeleton, FAs)
		mass=0d;
		name="";
		length=0;
		doubleBonds=0;
		abbvName="";
		// Method to calculate the mass
		// mass = calculateMass(Formula);
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
	// Define other methods
		public Formula calculateLipidFormula(Skeleton ske, List<Fatty_acid> FAs) {
		Map<Element, Integer> ske_formula =ske.getFormula().getMAPFORMULA();
		//TODO hay que coger la formula de todos los FAs
		Map<Element, Integer> FA_formula =ske.getFormula().getMAPFORMULA();
			return formula;
			
		}

}
