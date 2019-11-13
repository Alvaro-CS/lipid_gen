package model;

public class Characteristics {
	private final Formula formula;
	private final int minFAs;
	private final int maxFAs;

	/**
	 * Constructor of Characteristics for a Ske_type that has a minimum and maximum
	 * FAs accepted.
	 *
	 * @param formula of the skeleton
	 * @param min     minimum number of FAs
	 * @param max     maximum number of FAs
	 */
	public Characteristics(Formula formula, int min, int max) {
		this.formula = formula;
		this.minFAs = min;
		this.maxFAs = max;

	}

	/**
	 * Constructor of Characteristics for a Ske_type that has a precise number of
	 * FAs
	 *
	 * @param formula of the skeleton
	 * @param n       number of FAs
	 */
	public Characteristics(Formula formula, int n) {
		this.formula = formula;
		this.minFAs = n;
		this.maxFAs = n;

	}

	public Formula getFormula() {
		return formula;
	}

	public int getMinFAs() {
		return minFAs;
	}

	public int getMaxFAs() {
		return maxFAs;
	}

}
