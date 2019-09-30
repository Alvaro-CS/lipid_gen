package model;

import Exceptions.Fatty_acidCreationException;

public class Fatty_acid {
/*int ncarbon;
int double_bonds; Is it necessary?*/
Formula formula;
public Fatty_acid(int C,int double_bonds) throws Fatty_acidCreationException {//TODO Excepcion doublebonds-c, limite carbonos
	
	if(double_bonds > C - 1)throw new Fatty_acidCreationException("Double bonds of the molecule must be at least lower than the number of Carbon atoms-1.");
	this.formula=new Formula(C, double_bonds);
}

	public Formula getFormula() {
		return formula;
	}
	public void setFormula(Formula formula) {
		this.formula = formula;
	}
	
}
