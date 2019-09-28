package model;

public class Fatty_acid {
/*int ncarbon;
int double_bonds; Is it necessary?*/
Formula formula;
public Fatty_acid(int C,int double_bonds) {
	
	this.formula=new Formula(C, double_bonds);
	
}

	public Formula getFormula() {
		return formula;
	}
	public void setFormula(Formula formula) {
		this.formula = formula;
	}
	
}
