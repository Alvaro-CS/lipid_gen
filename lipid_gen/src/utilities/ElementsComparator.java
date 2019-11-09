package utilities;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import model.Element;

public class ElementsComparator implements Comparator<Element> {

	private static final Map<Element, Integer> order = new HashMap<Element, Integer>();
	static {
		order.put(Element.C, 1);
		order.put(Element.H, 2);
		order.put(Element.N, 3);
		order.put(Element.O, 4);
		order.put(Element.P, 5);
		order.put(Element.S, 6);
		order.put(Element.Cl, 7);
		order.put(Element.F, 8);
		order.put(Element.Li, 9);
		order.put(Element.Na, 10);
		order.put(Element.K, 11);
		order.put(Element.Rb, 12);
		order.put(Element.Cs, 13);
		order.put(Element.Fr, 14);
		order.put(Element.Be, 15);
		order.put(Element.Mg, 16);
		order.put(Element.Ca, 17);
		order.put(Element.Sr, 18);
		order.put(Element.Ba, 19);
		order.put(Element.Ra, 20);
		order.put(Element.Sc, 21);
		order.put(Element.Y, 22);
		order.put(Element.Lu, 23);
		order.put(Element.Lr, 24);
		order.put(Element.Ti, 25);
		order.put(Element.Zr, 26);
		order.put(Element.Hf, 27);
		order.put(Element.Rf, 28);
		order.put(Element.V, 29);
		order.put(Element.Nb, 30);
		order.put(Element.Ta, 31);
		order.put(Element.Db, 32);
		order.put(Element.Cr, 33);
		order.put(Element.Mo, 34);
		order.put(Element.W, 35);
		order.put(Element.Sg, 36);
		order.put(Element.Mn, 37);
		order.put(Element.Tc, 38);
		order.put(Element.Re, 39);
		order.put(Element.Bh, 40);
		order.put(Element.Fe, 41);
		order.put(Element.Ru, 42);
		order.put(Element.Os, 43);
		order.put(Element.Hs, 44);
		order.put(Element.Co, 45);
		order.put(Element.Rh, 46);
		order.put(Element.Ir, 47);
		order.put(Element.Mt, 48);
		order.put(Element.Ni, 49);
		order.put(Element.Pd, 50);
		order.put(Element.Pt, 51);
		order.put(Element.Ds, 52);
		order.put(Element.Cu, 53);
		order.put(Element.Ag, 54);
		order.put(Element.Au, 55);
		order.put(Element.Rg, 56);
		order.put(Element.Zn, 57);
		order.put(Element.Cd, 58);
		order.put(Element.Hg, 59);
		order.put(Element.Cn, 60);
		order.put(Element.B, 61);
		order.put(Element.Al, 62);
		order.put(Element.In, 63);
		order.put(Element.Tl, 64);
		order.put(Element.Nh, 65);
		order.put(Element.Si, 66);
		order.put(Element.Ge, 67);
		order.put(Element.Sn, 68);
		order.put(Element.Pb, 69);
		order.put(Element.Fl, 70);
		order.put(Element.As, 71);
		order.put(Element.Sb, 72);
		order.put(Element.Bi, 73);
		order.put(Element.Mc, 74);
		order.put(Element.Se, 75);
		order.put(Element.Po, 76);
		order.put(Element.Lv, 77);
		order.put(Element.Br, 78);
		order.put(Element.I, 79);
		order.put(Element.At, 80);
		order.put(Element.Ts, 81);

	}

	@Override
	public int compare(Element arg0, Element arg1) {
		return (ElementsComparator.order.get(arg0) - ElementsComparator.order.get(arg1));
	}

}
