package utilities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Element;

public class Periodic_table {
	
	

	public static final Map<Element, Double> MAPELEMENTS = new HashMap<Element, Double>();

    static {

        MAPELEMENTS.put(Element.H, 1.007825d);
        MAPELEMENTS.put(Element.He, 4.002603d);
        MAPELEMENTS.put(Element.Li, 7.016005d);
        MAPELEMENTS.put(Element.Be, 9.012183d);
        MAPELEMENTS.put(Element.B, 11.009305d);
        MAPELEMENTS.put(Element.C, 12d);
        MAPELEMENTS.put(Element.N, 14.003074d);
        MAPELEMENTS.put(Element.O, 15.994915d);
        MAPELEMENTS.put(Element.F, 18.998403d);
        MAPELEMENTS.put(Element.Ne, 19.992439d);
        MAPELEMENTS.put(Element.Na, 22.989770d);
        MAPELEMENTS.put(Element.Mg, 23.985045d);
        MAPELEMENTS.put(Element.Al, 26.981541d);
        MAPELEMENTS.put(Element.Si, 27.976928d);
        MAPELEMENTS.put(Element.P, 30.973763d);
        MAPELEMENTS.put(Element.S, 31.972072d);
        MAPELEMENTS.put(Element.Cl, 34.968853d);
        MAPELEMENTS.put(Element.Ar, 39.962383d);
        MAPELEMENTS.put(Element.K, 38.963708d);
        MAPELEMENTS.put(Element.Ca, 39.962591d);
        MAPELEMENTS.put(Element.Sc, 44.955914d);
        MAPELEMENTS.put(Element.Ti, 47.947947d);
        MAPELEMENTS.put(Element.V, 50.943963d);
        MAPELEMENTS.put(Element.Cr, 51.940510d);
        MAPELEMENTS.put(Element.Mn, 54.938046d);
        MAPELEMENTS.put(Element.Fe, 55.934939d);
        MAPELEMENTS.put(Element.Ni, 57.935347d);
        MAPELEMENTS.put(Element.Co, 58.933198d);
        MAPELEMENTS.put(Element.Cu, 62.929599d);
        MAPELEMENTS.put(Element.Zn, 63.929145d);
        MAPELEMENTS.put(Element.Ga, 68.925581d);
        MAPELEMENTS.put(Element.Ge, 73.921179d);
        MAPELEMENTS.put(Element.As, 74.921596d);
        MAPELEMENTS.put(Element.Se, 79.916521d);
        MAPELEMENTS.put(Element.Br, 78.918336d);
        MAPELEMENTS.put(Element.Kr, 83.911506d);
        MAPELEMENTS.put(Element.Rb, 84.911800d);
        MAPELEMENTS.put(Element.Sr, 87.905625d);
        MAPELEMENTS.put(Element.Y, 88.905856d);
        MAPELEMENTS.put(Element.Zr, 89.904708d);
        MAPELEMENTS.put(Element.Nb, 92.906378d);
        MAPELEMENTS.put(Element.Mo, 97.905405d);
        MAPELEMENTS.put(Element.Tc, 98.0d); 
        MAPELEMENTS.put(Element.Ru, 101.904348d);
        MAPELEMENTS.put(Element.Rh, 102.905503d);
        MAPELEMENTS.put(Element.Pd, 105.903475d);
        MAPELEMENTS.put(Element.Ag, 106.905095d);
        MAPELEMENTS.put(Element.Cd, 113.903361d);
        MAPELEMENTS.put(Element.In, 114.903875d);
        MAPELEMENTS.put(Element.Sn, 118.710d);
        MAPELEMENTS.put(Element.Sb, 120.903824d);
        MAPELEMENTS.put(Element.Te, 129.906229d);
        MAPELEMENTS.put(Element.I, 126.904477d);
        MAPELEMENTS.put(Element.Xe, 131.293d);
        MAPELEMENTS.put(Element.Cs, 132.905433d);
        MAPELEMENTS.put(Element.Ba, 137.905236d);
        MAPELEMENTS.put(Element.La, 138.907114d);
        MAPELEMENTS.put(Element.Ce, 139.905442d);
        MAPELEMENTS.put(Element.Pr, 140.907657d);
        MAPELEMENTS.put(Element.Nd, 141.907731d);
        MAPELEMENTS.put(Element.Pm, 145.0d);
        MAPELEMENTS.put(Element.Sm, 151.919741d);
        MAPELEMENTS.put(Element.Eu, 152.921243d);
        MAPELEMENTS.put(Element.Gd, 157.924111d);
        MAPELEMENTS.put(Element.Tb, 158.92535d);
        MAPELEMENTS.put(Element.Dy, 163.929183d);
        MAPELEMENTS.put(Element.Ho, 164.930332d);
        MAPELEMENTS.put(Element.Er, 165.930305d);
        MAPELEMENTS.put(Element.Tm, 168.934225d);
        MAPELEMENTS.put(Element.Yb, 173.938873d);
        MAPELEMENTS.put(Element.Lu, 174.940785d);
        MAPELEMENTS.put(Element.Hf, 179.946561d);
        MAPELEMENTS.put(Element.Ta, 180.948014d);
        MAPELEMENTS.put(Element.W, 183.950953d);
        MAPELEMENTS.put(Element.Re, 186.955765d);
        MAPELEMENTS.put(Element.Os, 191.961487d);
        MAPELEMENTS.put(Element.Ir, 192.962942d);
        MAPELEMENTS.put(Element.Pt, 194.964785d);
        MAPELEMENTS.put(Element.Au, 196.966560d);
        MAPELEMENTS.put(Element.Hg, 201.970632d);
        MAPELEMENTS.put(Element.Tl, 204.974410d);
        MAPELEMENTS.put(Element.Pb, 207.976641d);
        MAPELEMENTS.put(Element.Bi, 208.980388d);
        MAPELEMENTS.put(Element.Po, 209.0d);
        MAPELEMENTS.put(Element.At, 210.0d);
        MAPELEMENTS.put(Element.Rn, 222.0d);
        MAPELEMENTS.put(Element.Fr, 223.0d);
        MAPELEMENTS.put(Element.Ra, 226.0d);
        MAPELEMENTS.put(Element.Ac, 227.0d);
        MAPELEMENTS.put(Element.Th, 232.038054d);
        MAPELEMENTS.put(Element.Pa, 231.03588d);
        MAPELEMENTS.put(Element.U, 238.050786d);
        MAPELEMENTS.put(Element.Np, 237.0d);
        MAPELEMENTS.put(Element.Pu, 244.0d);
        MAPELEMENTS.put(Element.Am, 243.0d);
        MAPELEMENTS.put(Element.Cm, 247.0d);
        MAPELEMENTS.put(Element.Bk, 247.0d);
        MAPELEMENTS.put(Element.Cf, 251.0d);
        MAPELEMENTS.put(Element.Es, 252.0d);
        MAPELEMENTS.put(Element.Fm, 257.0d);
        MAPELEMENTS.put(Element.Md, 258.0d);
        MAPELEMENTS.put(Element.No, 259.0d);
        MAPELEMENTS.put(Element.Lr, 266.0d);
        MAPELEMENTS.put(Element.Rf, 267.0d);
        MAPELEMENTS.put(Element.Db, 268.0d);
        MAPELEMENTS.put(Element.Sg, 269.0d);
        MAPELEMENTS.put(Element.Bh, 270.0d);
        MAPELEMENTS.put(Element.Hs, 269.0d);
        MAPELEMENTS.put(Element.Mt, 278.0d);
        MAPELEMENTS.put(Element.Ds, 281.0d);
        MAPELEMENTS.put(Element.Rg, 282.0d);
        MAPELEMENTS.put(Element.Cn, 285.0d);
        MAPELEMENTS.put(Element.Uut, 286.0d);
        MAPELEMENTS.put(Element.Fl, 289.0d);
        MAPELEMENTS.put(Element.Uup, 289.0d);
        MAPELEMENTS.put(Element.Lv, 293.0d);
        MAPELEMENTS.put(Element.Uus, 294.0d);
        MAPELEMENTS.put(Element.Uuo, 294.0d);
        
    }

}